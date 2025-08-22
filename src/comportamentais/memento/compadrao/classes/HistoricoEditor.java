package comportamentais.memento.compadrao.classes;

import comportamentais.memento.compadrao.interfaces.Memento;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe HistoricoEditor - Caretaker no padrão Memento
 * 
 * Responsável por:
 * - Gerenciar coleção de mementos
 * - Implementar funcionalidades de undo/redo
 * - Controlar limite de histórico
 * - Não acessar o conteúdo dos mementos
 * 
 * VANTAGENS:
 * - Separação de responsabilidades
 * - Gerenciamento centralizado do histórico
 * - Implementação robusta de undo/redo
 * - Controle de memória
 */
public class HistoricoEditor {
    
    private final List<Memento> historico;
    private int posicaoAtual;
    private final int limiteHistorico;
    
    public HistoricoEditor() {
        this(10); // Limite padrão de 10 estados
    }
    
    public HistoricoEditor(int limiteHistorico) {
        this.historico = new ArrayList<>();
        this.posicaoAtual = -1;
        this.limiteHistorico = Math.max(1, limiteHistorico);
    }
    
    /**
     * Salva um novo estado no histórico
     * 
     * @param memento Memento a ser salvo
     */
    public void salvar(Memento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("Memento não pode ser nulo");
        }
        
        // Remove estados futuros se estamos no meio do histórico
        if (posicaoAtual < historico.size() - 1) {
            historico.subList(posicaoAtual + 1, historico.size()).clear();
        }
        
        // Adiciona novo memento
        historico.add(memento);
        posicaoAtual++;
        
        // Controla limite do histórico
        if (historico.size() > limiteHistorico) {
            historico.remove(0);
            posicaoAtual--;
        }
        
        System.out.println("Estado salvo. Posição: " + (posicaoAtual + 1) + "/" + historico.size());
    }
    
    /**
     * Desfaz a última ação
     * 
     * @return Memento do estado anterior, ou null se não houver
     */
    public Memento desfazer() {
        if (!podeDesfazer()) {
            System.out.println("Não há ações para desfazer");
            return null;
        }
        
        posicaoAtual--;
        Memento memento = historico.get(posicaoAtual);
        System.out.println("Ação desfeita. Posição: " + (posicaoAtual + 1) + "/" + historico.size());
        return memento;
    }
    
    /**
     * Refaz a próxima ação
     * 
     * @return Memento do próximo estado, ou null se não houver
     */
    public Memento refazer() {
        if (!podeRefazer()) {
            System.out.println("Não há ações para refazer");
            return null;
        }
        
        posicaoAtual++;
        Memento memento = historico.get(posicaoAtual);
        System.out.println("Ação refeita. Posição: " + (posicaoAtual + 1) + "/" + historico.size());
        return memento;
    }
    
    /**
     * Verifica se é possível desfazer
     */
    public boolean podeDesfazer() {
        return posicaoAtual > 0;
    }
    
    /**
     * Verifica se é possível refazer
     */
    public boolean podeRefazer() {
        return posicaoAtual < historico.size() - 1;
    }
    
    /**
     * Obtém o estado atual
     */
    public Memento getEstadoAtual() {
        if (historico.isEmpty()) {
            return null;
        }
        return historico.get(posicaoAtual);
    }
    
    /**
     * Vai para um estado específico no histórico
     * 
     * @param posicao Posição no histórico (0-based)
     * @return Memento da posição especificada, ou null se inválida
     */
    public Memento irParaPosicao(int posicao) {
        if (posicao < 0 || posicao >= historico.size()) {
            System.out.println("Posição inválida: " + posicao);
            return null;
        }
        
        posicaoAtual = posicao;
        Memento memento = historico.get(posicaoAtual);
        System.out.println("Navegado para posição: " + (posicaoAtual + 1) + "/" + historico.size());
        return memento;
    }
    
    /**
     * Limpa todo o histórico
     */
    public void limparHistorico() {
        historico.clear();
        posicaoAtual = -1;
        System.out.println("Histórico limpo");
    }
    
    /**
     * Mostra informações sobre o histórico
     */
    public void mostrarHistorico() {
        System.out.println("\n=== HISTÓRICO ===");
        System.out.println("Total de estados: " + historico.size());
        System.out.println("Posição atual: " + (posicaoAtual + 1));
        System.out.println("Pode desfazer: " + podeDesfazer());
        System.out.println("Pode refazer: " + podeRefazer());
        System.out.println("Limite: " + limiteHistorico);
        
        if (!historico.isEmpty()) {
            System.out.println("\nEstados salvos:");
            for (int i = 0; i < historico.size(); i++) {
                String marcador = (i == posicaoAtual) ? " <- ATUAL" : "";
                System.out.println((i + 1) + ". " + historico.get(i) + marcador);
            }
        }
    }
    
    // Métodos de consulta
    public int getTamanhoHistorico() {
        return historico.size();
    }
    
    public int getPosicaoAtual() {
        return posicaoAtual;
    }
    
    public int getLimiteHistorico() {
        return limiteHistorico;
    }
    
    public boolean isHistoricoVazio() {
        return historico.isEmpty();
    }
}