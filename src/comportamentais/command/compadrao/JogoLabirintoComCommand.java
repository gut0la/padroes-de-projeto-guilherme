package comportamentais.command.compadrao;

import comportamentais.command.compadrao.interfaces.Command;
import comportamentais.command.compadrao.classes.*;
import comportamentais.command.compadrao.commands.*;
import java.util.*;
import java.util.concurrent.Future;

/**
 * Demonstração do Padrão Command em um Jogo de Labirinto
 * 
 * PADRÃO COMMAND (COMPORTAMENTAL):
 * 
 * DEFINIÇÃO:
 * O padrão Command encapsula uma solicitação como um objeto, permitindo
 * parametrizar clientes com diferentes solicitações, enfileirar ou registrar
 * solicitações e suportar operações que podem ser desfeitas.
 * 
 * PROBLEMA QUE RESOLVE:
 * - Acoplamento forte entre quem solicita e quem executa operações
 * - Impossibilidade de desfazer operações
 * - Dificuldade para implementar filas, logs e macros
 * - Violação dos princípios SOLID (especialmente SRP e OCP)
 * 
 * COMPONENTES:
 * 1. COMMAND (Interface): Define contrato para execução de comandos
 * 2. CONCRETE COMMAND: Implementa comandos específicos
 * 3. RECEIVER: Objeto que sabe como executar operações
 * 4. INVOKER: Solicita execução sem conhecer implementação
 * 5. CLIENT: Cria comandos e associa com receivers
 * 
 * BENEFÍCIOS:
 * BENEFÍCIOS: Desacoplamento entre solicitante e executor
 * BENEFÍCIOS: Funcionalidade de undo/redo
 * BENEFÍCIOS: Suporte a filas e macros
 * BENEFÍCIOS: Logging e auditoria de operações
 * BENEFÍCIOS: Execução assíncrona
 * BENEFÍCIOS: Composição de comandos complexos
 * BENEFÍCIOS: Extensibilidade (fácil adicionar novos comandos)
 * 
 * QUANDO USAR:
 * - Necessidade de undo/redo
 * - Operações em fila ou batch
 * - Logging de operações
 * - Desacoplamento entre UI e lógica de negócio
 * - Macros e automação
 * - Transações e rollback
 */
public class JogoLabirintoComCommand {
    
    // RECEIVERS - objetos que sabem executar operações
    private static Jogador jogador;
    private static SistemaJogo sistema;
    
    // INVOKER - gerencia execução de comandos
    private static GerenciadorComandos gerenciador;
    
    // Scanner para entrada do usuário
    private static Scanner scanner;
    
    public static void main(String[] args) {
        System.out.println("JOGO DE LABIRINTO - PADRÃO COMMAND");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        inicializarJogo();
        exibirInstrucoes();
        
        // Loop principal do jogo
        boolean jogando = true;
        while (jogando) {
            exibirMenuPrincipal();
            
            String opcao = scanner.nextLine().trim().toLowerCase();
            
            switch (opcao) {
                case "1":
                case "mover":
                case "m":
                    menuMovimento();
                    break;
                    
                case "2":
                case "acao":
                case "a":
                    menuAcoes();
                    break;
                    
                case "3":
                case "undo":
                case "u":
                    gerenciador.desfazerUltimoComando();
                    break;
                    
                case "4":
                case "redo":
                case "r":
                    gerenciador.refazerUltimoComando();
                    break;
                    
                case "5":
                case "macro":
                    executarMacroExemplo();
                    break;
                    
                case "6":
                case "fila":
                case "f":
                    menuFila();
                    break;
                    
                case "7":
                case "historico":
                case "h":
                    gerenciador.exibirHistorico();
                    break;
                    
                case "8":
                case "stats":
                case "s":
                    exibirStatusCompleto();
                    break;
                    
                case "9":
                case "debug":
                case "d":
                    alternarModoDebug();
                    break;
                    
                case "0":
                case "sair":
                case "quit":
                case "q":
                    jogando = false;
                    break;
                    
                default:
                    System.out.println("ERRO: Opção inválida! Digite 'help' para ver as opções.");
                    break;
            }
            
            // Pausa para leitura
            if (jogando) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        finalizarJogo();
    }
    
    /**
     * Inicializa o jogo e seus componentes
     */
    private static void inicializarJogo() {
        System.out.println("\nInicializando jogo...");
        
        // Cria RECEIVERS
        jogador = new Jogador("Herói", 100, 50);
        sistema = new SistemaJogo();
        
        // Cria INVOKER
        gerenciador = new GerenciadorComandos();
        
        scanner = new Scanner(System.in);
        
        System.out.println("SUCESSO: Jogo inicializado com sucesso!");
        
        // Demonstra criação de comandos iniciais
        demonstrarCriacaoComandos();
    }
    
    /**
     * Demonstra como criar e usar comandos
     */
    private static void demonstrarCriacaoComandos() {
        System.out.println("\n📚 DEMONSTRAÇÃO: Criação de Comandos");
        System.out.println("─────────────────────────────────────────────");
        
        // CLIENT cria comandos e associa com receivers
        Command moverNorte = MovimentoCommandFactory.criarComandoMovimento("norte", jogador, sistema);
        Command atacar = AcaoCommandFactory.criarComandoAcao("atacar", jogador, sistema);
        
        System.out.println("SUCESSO: Comandos criados:");
        System.out.println("   - " + moverNorte.getDescricao());
        System.out.println("   - " + atacar.getDescricao());
        
        // Demonstra execução via INVOKER
        System.out.println("\nExecutando comandos via gerenciador...");
        gerenciador.executarComando(moverNorte);
        gerenciador.executarComando(atacar);
        
        System.out.println("\n💡 Observe como o INVOKER executa comandos sem conhecer sua implementação!");
    }
    
    /**
     * Exibe instruções do jogo
     */
    private static void exibirInstrucoes() {
        System.out.println("\n📖 INSTRUÇÕES:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println("Este jogo demonstra o padrão Command através de:");
        System.out.println("• Comandos de movimento (Norte, Sul, Leste, Oeste)");
        System.out.println("• Comandos de ação (Atacar, Magia, Usar itens)");
        System.out.println("• Funcionalidade de Undo/Redo");
        System.out.println("• Filas de comandos e macros");
        System.out.println("• Execução assíncrona");
        System.out.println("• Histórico e auditoria");
        
        System.out.println("\nBENEFÍCIOS DEMONSTRADOS:");
        System.out.println("• Desacoplamento entre UI e lógica");
        System.out.println("• Reversibilidade de operações");
        System.out.println("• Composição e reutilização");
        System.out.println("• Extensibilidade");
    }
    
    /**
     * Exibe menu principal
     */
    private static void exibirMenuPrincipal() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("MENU PRINCIPAL - PADRÃO COMMAND");
        System.out.println("═".repeat(60));
        
        // Status rápido
        System.out.printf("Jogador: %s | Vida: %d | Mana: %d | Posição: (%d,%d) | Pontos: %d pts | Turno: %d\n",
            jogador.getNome(), jogador.getVida(), jogador.getMana(),
            jogador.getX(), jogador.getY(), sistema.getPontuacao(), sistema.getTurno());
        
        System.out.println("\nOPÇÕES:");
        System.out.println("1. Movimento (Norte/Sul/Leste/Oeste)");
        System.out.println("2. Ações (Atacar/Magia/Itens/Salvar)");
        System.out.println("3. Undo (Desfazer último comando)");
        System.out.println("4. Redo (Refazer comando desfeito)");
        System.out.println("5. Macro (Executar sequência de comandos)");
        System.out.println("6. Fila (Gerenciar comandos pendentes)");
        System.out.println("7. Debug (Alternar modo debug)");
        System.out.println("8. Status (Estatísticas completas)");
        System.out.println("0. Sair");
        
        // Indicadores de estado
        if (gerenciador.temComandosParaDesfazer()) {
            System.out.println("\nSUCESSO: Undo disponível");
        }
        if (gerenciador.temComandosParaRefazer()) {
            System.out.println("SUCESSO: Redo disponível");
        }
        if (gerenciador.getTamanhoFilaPendentes() > 0) {
            System.out.println(gerenciador.getTamanhoFilaPendentes() + " comandos na fila");
        }
        
        System.out.print("\nEscolha uma opção: ");
    }
    
    /**
     * Menu de movimento
     */
    private static void menuMovimento() {
        System.out.println("\nMENU DE MOVIMENTO:");
        System.out.println("1. Norte  2. Sul  3. Leste  4. Oeste  5. Voltar");
        System.out.print("Direção: ");
        
        String opcao = scanner.nextLine().trim().toLowerCase();
        
        String direcao = null;
        switch (opcao) {
            case "1": case "norte": case "n": direcao = "norte"; break;
            case "2": case "sul": case "s": direcao = "sul"; break;
            case "3": case "leste": case "l": direcao = "leste"; break;
            case "4": case "oeste": case "o": direcao = "oeste"; break;
            case "5": case "voltar": case "v": return;
            default:
                System.out.println("ERRO: Direção inválida!");
                return;
        }
        
        // CLIENT cria comando e solicita execução via INVOKER
        try {
            Command comando = MovimentoCommandFactory.criarComandoMovimento(direcao, jogador, sistema);
            gerenciador.executarComando(comando);
        } catch (Exception e) {
            System.out.println("ERRO: Erro ao criar comando: " + e.getMessage());
        }
    }
    
    /**
     * Menu de ações
     */
    private static void menuAcoes() {
        System.out.println("\nMENU DE AÇÕES:");
        System.out.println("1. Atacar  2. Magia  3. Poção Cura  4. Poção Mana  5. Salvar  6. Voltar");
        System.out.print("Ação: ");
        
        String opcao = scanner.nextLine().trim().toLowerCase();
        
        String acao = null;
        switch (opcao) {
            case "1": case "atacar": case "a": acao = "atacar"; break;
            case "2": case "magia": case "m": acao = "magia"; break;
            case "3": case "cura": case "c": acao = "cura"; break;
            case "4": case "mana": acao = "mana"; break;
            case "5": case "salvar": case "s": acao = "salvar"; break;
            case "6": case "voltar": case "v": return;
            default:
                System.out.println("ERRO: Ação inválida!");
                return;
        }
        
        // CLIENT cria comando e solicita execução via INVOKER
        try {
            Command comando = AcaoCommandFactory.criarComandoAcao(acao, jogador, sistema);
            gerenciador.executarComando(comando);
        } catch (Exception e) {
            System.out.println("ERRO: Erro ao criar comando: " + e.getMessage());
        }
    }
    
    /**
     * Executa macro de exemplo
     */
    private static void executarMacroExemplo() {
        System.out.println("\nEXECUTANDO MACRO DE EXEMPLO:");
        System.out.println("Sequência: Mover Norte → Atacar → Mover Leste → Usar Cura");
        
        // CLIENT cria lista de comandos
        List<Command> macro = Arrays.asList(
            MovimentoCommandFactory.criarComandoMovimento("norte", jogador, sistema),
            AcaoCommandFactory.criarComandoAcao("atacar", jogador, sistema),
            MovimentoCommandFactory.criarComandoMovimento("leste", jogador, sistema),
            AcaoCommandFactory.criarComandoAcao("cura", jogador, sistema)
        );
        
        // INVOKER executa macro
        int sucessos = gerenciador.executarMacro(macro);
        System.out.println("\n🏁 Macro concluído com " + sucessos + " sucessos!");
    }
    
    /**
     * Menu de gerenciamento de fila
     */
    private static void menuFila() {
        System.out.println("\nMENU DE FILA:");
        System.out.println("1. Executar fila  2. Adicionar comando  3. Limpar fila  4. Voltar");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine().trim();
        
        switch (opcao) {
            case "1":
                adicionarComandoNaFila();
                break;
            case "2":
                gerenciador.executarFilaPendentes();
                break;
            case "3":
                System.out.println("Comandos na fila: " + gerenciador.getTamanhoFilaPendentes());
                break;
            case "4":
                break;
            default:
                System.out.println("ERRO: Opção inválida!");
        }
    }
    
    /**
     * Adiciona comando na fila
     */
    private static void adicionarComandoNaFila() {
        System.out.println("\nADICIONAR COMANDO NA FILA:");
        System.out.println("Tipo: 1. Movimento  2. Ação");
        System.out.print("Tipo: ");
        
        String tipo = scanner.nextLine().trim();
        
        if ("1".equals(tipo)) {
            System.out.print("Direção (norte/sul/leste/oeste): ");
            String direcao = scanner.nextLine().trim().toLowerCase();
            try {
                Command comando = MovimentoCommandFactory.criarComandoMovimento(direcao, jogador, sistema);
                gerenciador.enfileirarComando(comando);
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        } else if ("2".equals(tipo)) {
            System.out.print("Ação (atacar/magia/cura/mana/salvar): ");
            String acao = scanner.nextLine().trim().toLowerCase();
            try {
                Command comando = AcaoCommandFactory.criarComandoAcao(acao, jogador, sistema);
                gerenciador.enfileirarComando(comando);
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        } else {
            System.out.println("ERRO: Tipo inválido!");
        }
    }
    
    /**
     * Exibe status completo
     */
    private static void exibirStatusCompleto() {
        System.out.println("\nSTATUS COMPLETO:");
        System.out.println("═".repeat(50));
        
        // Status do jogador
        jogador.exibirStatus();
        
        // Status do sistema
        sistema.exibirEstatisticas();
        
        // Estatísticas do gerenciador
        gerenciador.exibirEstatisticas();
    }
    
    /**
     * Alterna modo debug
     */
    private static void alternarModoDebug() {
        boolean novoModo = !gerenciador.isModoDebug();
        gerenciador.setModoDebug(novoModo);
    }
    
    /**
     * Finaliza o jogo
     */
    private static void finalizarJogo() {
        System.out.println("\nFINALIZANDO JOGO...");
        System.out.println("═".repeat(50));
        
        // Exibe estatísticas finais
        System.out.println("ESTATÍSTICAS FINAIS:");
        System.out.println("Pontuação final: " + sistema.getPontuacao());
        System.out.println("Turnos jogados: " + sistema.getTurno());
        System.out.println("Comandos executados: " + gerenciador.getTotalComandosExecutados());
        
        // Demonstra benefícios do padrão
        demonstrarBeneficios();
        
        // Finaliza gerenciador
        gerenciador.finalizar();
        
        System.out.println("\n👋 Obrigado por jogar! Padrão Command demonstrado com sucesso!");
    }
    
    /**
     * Demonstra os benefícios do padrão Command
     */
    private static void demonstrarBeneficios() {
        System.out.println("\nBENEFÍCIOS DEMONSTRADOS:");
        System.out.println("─".repeat(40));
        
        System.out.println("DESACOPLAMENTO:");
        System.out.println("   • UI não conhece implementação dos comandos");
        System.out.println("   • Fácil trocar receivers sem afetar interface");
        
        System.out.println("\nUNDO/REDO:");
        System.out.println("   • Comandos mantêm estado para reversão");
        System.out.println("   • Histórico completo de operações");
        
        System.out.println("\nCOMPOSIÇÃO:");
        System.out.println("   • Macros combinam comandos simples");
        System.out.println("   • Filas permitem execução em lote");
        
        System.out.println("\nEXTENSIBILIDADE:");
        System.out.println("   • Novos comandos sem modificar código existente");
        System.out.println("   • Factories facilitam criação");
        
        System.out.println("\nAUDITORIA:");
        System.out.println("   • Histórico completo de ações");
        System.out.println("   • Timestamps e estatísticas");
        
        System.out.println("\n💡 COMPARE COM A VERSÃO SEM PADRÃO:");
        System.out.println("   • Sem undo/redo");
        System.out.println("   • Acoplamento forte UI-lógica");
        System.out.println("   • Difícil implementar filas/macros");
        System.out.println("   • Violação de princípios SOLID");
    }
}