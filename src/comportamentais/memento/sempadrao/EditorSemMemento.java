package comportamentais.memento.sempadrao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Editor de texto SEM o padrão Memento
 * 
 * PROBLEMAS:
 * - Histórico misturado com lógica do editor
 * - Dificuldade para implementar undo/redo
 * - Violação do Single Responsibility Principle
 * - Acoplamento forte entre estado e controle
 * - Dificuldade para salvar/restaurar estados específicos
 */
public class EditorSemMemento {
    
    private String conteudo;
    private String fonte;
    private int tamanhoFonte;
    
    // PROBLEMA: Histórico misturado com a lógica principal
    private List<String> historicoConteudo;
    private List<String> historicoFonte;
    private List<Integer> historicoTamanho;
    private int posicaoHistorico;
    
    public EditorSemMemento() {
        this.conteudo = "";
        this.fonte = "Arial";
        this.tamanhoFonte = 12;
        
        // PROBLEMA: Inicialização complexa do histórico
        this.historicoConteudo = new ArrayList<>();
        this.historicoFonte = new ArrayList<>();
        this.historicoTamanho = new ArrayList<>();
        this.posicaoHistorico = -1;
        
        salvarEstadoAtual();
    }
    
    public void escrever(String texto) {
        this.conteudo += texto;
        salvarEstadoAtual();
        System.out.println("Texto adicionado: " + texto);
    }
    
    public void alterarFonte(String novaFonte) {
        this.fonte = novaFonte;
        salvarEstadoAtual();
        System.out.println("Fonte alterada para: " + novaFonte);
    }
    
    public void alterarTamanhoFonte(int novoTamanho) {
        this.tamanhoFonte = novoTamanho;
        salvarEstadoAtual();
        System.out.println("Tamanho da fonte alterado para: " + novoTamanho);
    }
    
    public void limparConteudo() {
        this.conteudo = "";
        salvarEstadoAtual();
        System.out.println("Conteúdo limpo");
    }
    
    // PROBLEMA: Lógica de histórico misturada com lógica do editor
    private void salvarEstadoAtual() {
        // PROBLEMA: Código complexo para gerenciar histórico
        if (posicaoHistorico < historicoConteudo.size() - 1) {
            // Remove estados futuros se estamos no meio do histórico
            historicoConteudo = historicoConteudo.subList(0, posicaoHistorico + 1);
            historicoFonte = historicoFonte.subList(0, posicaoHistorico + 1);
            historicoTamanho = historicoTamanho.subList(0, posicaoHistorico + 1);
        }
        
        historicoConteudo.add(conteudo);
        historicoFonte.add(fonte);
        historicoTamanho.add(tamanhoFonte);
        posicaoHistorico++;
        
        // PROBLEMA: Lógica de limpeza de histórico misturada
        if (historicoConteudo.size() > 10) {
            historicoConteudo.remove(0);
            historicoFonte.remove(0);
            historicoTamanho.remove(0);
            posicaoHistorico--;
        }
    }
    
    // PROBLEMA: Implementação complexa e propensa a erros
    public boolean desfazer() {
        if (posicaoHistorico > 0) {
            posicaoHistorico--;
            
            // PROBLEMA: Restauração manual de cada campo
            this.conteudo = historicoConteudo.get(posicaoHistorico);
            this.fonte = historicoFonte.get(posicaoHistorico);
            this.tamanhoFonte = historicoTamanho.get(posicaoHistorico);
            
            System.out.println("Ação desfeita");
            return true;
        }
        
        System.out.println("Não há ações para desfazer");
        return false;
    }
    
    // PROBLEMA: Implementação complexa e propensa a erros
    public boolean refazer() {
        if (posicaoHistorico < historicoConteudo.size() - 1) {
            posicaoHistorico++;
            
            // PROBLEMA: Restauração manual de cada campo
            this.conteudo = historicoConteudo.get(posicaoHistorico);
            this.fonte = historicoFonte.get(posicaoHistorico);
            this.tamanhoFonte = historicoTamanho.get(posicaoHistorico);
            
            System.out.println("Ação refeita");
            return true;
        }
        
        System.out.println("Não há ações para refazer");
        return false;
    }
    
    public void mostrarEstado() {
        System.out.println("\n=== ESTADO ATUAL ===");
        System.out.println("Conteúdo: \"" + conteudo + "\"");
        System.out.println("Fonte: " + fonte);
        System.out.println("Tamanho: " + tamanhoFonte);
        System.out.println("Posição no histórico: " + (posicaoHistorico + 1) + "/" + historicoConteudo.size());
    }
    
    // PROBLEMA: Método para mostrar histórico expõe implementação interna
    public void mostrarHistorico() {
        System.out.println("\n=== HISTÓRICO ===");
        for (int i = 0; i < historicoConteudo.size(); i++) {
            String marcador = (i == posicaoHistorico) ? " <- ATUAL" : "";
            System.out.println((i + 1) + ". Conteúdo: \"" + historicoConteudo.get(i) + 
                             "\", Fonte: " + historicoFonte.get(i) + 
                             ", Tamanho: " + historicoTamanho.get(i) + marcador);
        }
    }
    
    public static void main(String[] args) {
        EditorSemMemento editor = new EditorSemMemento();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== EDITOR SEM MEMENTO ===");
        System.out.println("Comandos: escrever, fonte, tamanho, limpar, desfazer, refazer, estado, historico, sair");
        
        while (true) {
            System.out.print("\nComando: ");
            String comando = scanner.nextLine().toLowerCase();
            
            switch (comando) {
                case "escrever":
                    System.out.print("Digite o texto: ");
                    String texto = scanner.nextLine();
                    editor.escrever(texto);
                    break;
                    
                case "fonte":
                    System.out.print("Digite a nova fonte: ");
                    String fonte = scanner.nextLine();
                    editor.alterarFonte(fonte);
                    break;
                    
                case "tamanho":
                    System.out.print("Digite o novo tamanho: ");
                    try {
                        int tamanho = Integer.parseInt(scanner.nextLine());
                        editor.alterarTamanhoFonte(tamanho);
                    } catch (NumberFormatException e) {
                        System.out.println("Tamanho inválido!");
                    }
                    break;
                    
                case "limpar":
                    editor.limparConteudo();
                    break;
                    
                case "desfazer":
                    editor.desfazer();
                    break;
                    
                case "refazer":
                    editor.refazer();
                    break;
                    
                case "estado":
                    editor.mostrarEstado();
                    break;
                    
                case "historico":
                    editor.mostrarHistorico();
                    break;
                    
                case "sair":
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Comando inválido!");
            }
        }
    }
    
    /*
     * PROBLEMAS DESTA IMPLEMENTAÇÃO:
     * 
     * 1. VIOLAÇÃO SRP: Editor gerencia estado E histórico
     * 
     * 2. ACOPLAMENTO FORTE: Lógica de undo/redo misturada com editor
     * 
     * 3. COMPLEXIDADE: Código complexo para gerenciar histórico
     * 
     * 4. MANUTENIBILIDADE: Difícil adicionar novos campos ao estado
     * 
     * 5. ENCAPSULAMENTO: Estado interno exposto através do histórico
     * 
     * 6. FLEXIBILIDADE: Difícil implementar diferentes estratégias de histórico
     * 
     * 7. TESTABILIDADE: Difícil testar lógica de undo/redo isoladamente
     */
}