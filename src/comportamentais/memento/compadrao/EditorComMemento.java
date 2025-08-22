package comportamentais.memento.compadrao;

import comportamentais.memento.compadrao.classes.Editor;
import comportamentais.memento.compadrao.classes.HistoricoEditor;
import comportamentais.memento.compadrao.interfaces.Memento;
import java.util.Scanner;

/**
 * Editor de texto COM o padrão Memento
 * 
 * VANTAGENS:
 * - Separação clara de responsabilidades
 * - Editor foca na lógica de negócio
 * - HistoricoEditor gerencia undo/redo
 * - Estado encapsulado nos mementos
 * - Fácil extensão e manutenção
 */
public class EditorComMemento {
    
    public static void main(String[] args) {
        // VANTAGEM: Criação simples e separada
        Editor editor = new Editor();
        HistoricoEditor historico = new HistoricoEditor(5); // Limite de 5 estados
        
        // VANTAGEM: Estado inicial salvo automaticamente
        historico.salvar(editor.criarMemento());
        
        System.out.println("=== EDITOR COM MEMENTO ===");
        System.out.println("Comandos: escrever, fonte, tamanho, substituir, limpar, desfazer, refazer, estado, historico, navegar, demo, sair");
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("\nComando: ");
            String comando = scanner.nextLine().toLowerCase();
            
            switch (comando) {
                case "escrever":
                    System.out.print("Digite o texto: ");
                    String texto = scanner.nextLine();
                    editor.escrever(texto);
                    // VANTAGEM: Salvamento simples do estado
                    historico.salvar(editor.criarMemento());
                    break;
                    
                case "fonte":
                    System.out.print("Digite a nova fonte: ");
                    String fonte = scanner.nextLine();
                    editor.alterarFonte(fonte);
                    historico.salvar(editor.criarMemento());
                    break;
                    
                case "tamanho":
                    System.out.print("Digite o novo tamanho: ");
                    try {
                        int tamanho = Integer.parseInt(scanner.nextLine());
                        editor.alterarTamanhoFonte(tamanho);
                        historico.salvar(editor.criarMemento());
                    } catch (NumberFormatException e) {
                        System.out.println("Tamanho inválido!");
                    }
                    break;
                    
                case "substituir":
                    System.out.print("Digite o novo conteúdo: ");
                    String novoConteudo = scanner.nextLine();
                    editor.substituirConteudo(novoConteudo);
                    historico.salvar(editor.criarMemento());
                    break;
                    
                case "limpar":
                    editor.limparConteudo();
                    historico.salvar(editor.criarMemento());
                    break;
                    
                case "desfazer":
                    // VANTAGEM: Undo simples e robusto
                    Memento estadoAnterior = historico.desfazer();
                    if (estadoAnterior != null) {
                        editor.restaurarMemento(estadoAnterior);
                    }
                    break;
                    
                case "refazer":
                    // VANTAGEM: Redo simples e robusto
                    Memento proximoEstado = historico.refazer();
                    if (proximoEstado != null) {
                        editor.restaurarMemento(proximoEstado);
                    }
                    break;
                    
                case "estado":
                    editor.mostrarEstado();
                    break;
                    
                case "historico":
                    historico.mostrarHistorico();
                    break;
                    
                case "navegar":
                    System.out.print("Digite a posição (1-" + historico.getTamanhoHistorico() + "): ");
                    try {
                        int posicao = Integer.parseInt(scanner.nextLine()) - 1;
                        Memento estadoEspecifico = historico.irParaPosicao(posicao);
                        if (estadoEspecifico != null) {
                            editor.restaurarMemento(estadoEspecifico);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Posição inválida!");
                    }
                    break;
                    
                case "demo":
                    demonstrarFlexibilidade();
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
    
    /**
     * Demonstra a flexibilidade e robustez do padrão Memento
     */
    private static void demonstrarFlexibilidade() {
        System.out.println("\n=== DEMONSTRAÇÃO DE FLEXIBILIDADE ===");
        
        // VANTAGEM: Múltiplos editores com históricos independentes
        Editor editor1 = new Editor();
        Editor editor2 = new Editor();
        HistoricoEditor historico1 = new HistoricoEditor(3);
        HistoricoEditor historico2 = new HistoricoEditor(3);
        
        // Editor 1
        System.out.println("\n--- EDITOR 1 ---");
        historico1.salvar(editor1.criarMemento());
        
        editor1.escrever("Olá");
        historico1.salvar(editor1.criarMemento());
        
        editor1.escrever(" Mundo");
        historico1.salvar(editor1.criarMemento());
        
        editor1.alterarFonte("Times");
        historico1.salvar(editor1.criarMemento());
        
        editor1.mostrarEstado();
        
        // Editor 2
        System.out.println("\n--- EDITOR 2 ---");
        historico2.salvar(editor2.criarMemento());
        
        editor2.escrever("Java");
        historico2.salvar(editor2.criarMemento());
        
        editor2.alterarTamanhoFonte(16);
        historico2.salvar(editor2.criarMemento());
        
        editor2.mostrarEstado();
        
        // VANTAGEM: Operações independentes de undo/redo
        System.out.println("\n--- UNDO/REDO INDEPENDENTES ---");
        
        System.out.println("Desfazendo no Editor 1:");
        Memento estado1 = historico1.desfazer();
        if (estado1 != null) {
            editor1.restaurarMemento(estado1);
            editor1.mostrarEstado();
        }
        
        System.out.println("\nDesfazendo no Editor 2:");
        Memento estado2 = historico2.desfazer();
        if (estado2 != null) {
            editor2.restaurarMemento(estado2);
            editor2.mostrarEstado();
        }
        
        // VANTAGEM: Navegação livre no histórico
        System.out.println("\n--- NAVEGAÇÃO NO HISTÓRICO ---");
        historico1.mostrarHistorico();
        
        System.out.println("\nVoltando para o primeiro estado do Editor 1:");
        Memento primeiroEstado = historico1.irParaPosicao(0);
        if (primeiroEstado != null) {
            editor1.restaurarMemento(primeiroEstado);
            editor1.mostrarEstado();
        }
        
        // VANTAGEM: Transferência de estado entre editores
        System.out.println("\n--- TRANSFERÊNCIA DE ESTADO ---");
        System.out.println("Copiando estado do Editor 2 para Editor 1:");
        Memento estadoEditor2 = editor2.criarMemento();
        editor1.restaurarMemento(estadoEditor2);
        editor1.mostrarEstado();
        
        System.out.println("\n--- DEMONSTRAÇÃO CONCLUÍDA ---");
    }
    
    /*
     * VANTAGENS DO PADRÃO MEMENTO:
     * 
     * 1. ENCAPSULAMENTO: Estado interno protegido
     * 
     * 2. SEPARAÇÃO DE RESPONSABILIDADES: 
     *    - Editor: lógica de negócio
     *    - HistoricoEditor: gerenciamento de estados
     *    - Memento: armazenamento de estado
     * 
     * 3. FLEXIBILIDADE: Múltiplos editores e históricos
     * 
     * 4. ROBUSTEZ: Implementação confiável de undo/redo
     * 
     * 5. EXTENSIBILIDADE: Fácil adicionar novos campos ao estado
     * 
     * 6. REUTILIZAÇÃO: Componentes podem ser reutilizados
     * 
     * 7. TESTABILIDADE: Cada componente pode ser testado isoladamente
     * 
     * 8. CONTROLE DE MEMÓRIA: Limite configurável de histórico
     * 
     * 9. NAVEGAÇÃO: Acesso a qualquer ponto do histórico
     * 
     * 10. TRANSFERÊNCIA: Estados podem ser copiados entre objetos
     */
}