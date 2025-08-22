package comportamentais.state.sempadrao;

import java.util.Scanner;

/**
 * Reprodutor de música SEM o padrão State
 * 
 * PROBLEMAS:
 * - Lógica condicional complexa baseada em estados
 * - Violação do Open/Closed Principle
 * - Dificuldade para adicionar novos estados
 * - Código repetitivo e propenso a erros
 * - Difícil manutenção e teste
 */
public class ReprodutorSemState {
    
    // PROBLEMA: Estados representados como constantes
    private static final String PARADO = "PARADO";
    private static final String TOCANDO = "TOCANDO";
    private static final String PAUSADO = "PAUSADO";
    private static final String BLOQUEADO = "BLOQUEADO";
    
    private String estadoAtual;
    private String musicaAtual;
    private int volume;
    private boolean repetirPlaylist;
    
    public ReprodutorSemState() {
        this.estadoAtual = PARADO;
        this.musicaAtual = "";
        this.volume = 50;
        this.repetirPlaylist = false;
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void play() {
        System.out.println("\nComando: PLAY");
        
        // PROBLEMA: Lógica condicional complexa baseada no estado
        if (estadoAtual.equals(PARADO)) {
            if (musicaAtual.isEmpty()) {
                System.out.println("Erro: Nenhuma música selecionada!");
                return;
            }
            estadoAtual = TOCANDO;
            System.out.println("Iniciando reprodução: " + musicaAtual);
            System.out.println("Volume: " + volume);
            
        } else if (estadoAtual.equals(PAUSADO)) {
            estadoAtual = TOCANDO;
            System.out.println("Retomando reprodução: " + musicaAtual);
            
        } else if (estadoAtual.equals(TOCANDO)) {
            System.out.println("Já está tocando: " + musicaAtual);
            
        } else if (estadoAtual.equals(BLOQUEADO)) {
            System.out.println("Reprodutor bloqueado! Desbloqueie primeiro.");
            
        } else {
            // PROBLEMA: Estado inválido não tratado adequadamente
            System.out.println("Estado inválido: " + estadoAtual);
        }
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void pause() {
        System.out.println("\nComando: PAUSE");
        
        if (estadoAtual.equals(TOCANDO)) {
            estadoAtual = PAUSADO;
            System.out.println("Reprodução pausada: " + musicaAtual);
            
        } else if (estadoAtual.equals(PAUSADO)) {
            System.out.println("Já está pausado");
            
        } else if (estadoAtual.equals(PARADO)) {
            System.out.println("Não há música tocando para pausar");
            
        } else if (estadoAtual.equals(BLOQUEADO)) {
            System.out.println("Reprodutor bloqueado! Desbloqueie primeiro.");
            
        } else {
            System.out.println("Estado inválido: " + estadoAtual);
        }
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void stop() {
        System.out.println("\nComando: STOP");
        
        if (estadoAtual.equals(TOCANDO) || estadoAtual.equals(PAUSADO)) {
            estadoAtual = PARADO;
            System.out.println("Reprodução parada");
            
        } else if (estadoAtual.equals(PARADO)) {
            System.out.println("Já está parado");
            
        } else if (estadoAtual.equals(BLOQUEADO)) {
            System.out.println("Reprodutor bloqueado! Desbloqueie primeiro.");
            
        } else {
            System.out.println("Estado inválido: " + estadoAtual);
        }
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void proximaMusica() {
        System.out.println("\nComando: PRÓXIMA MÚSICA");
        
        if (estadoAtual.equals(TOCANDO)) {
            // Simula mudança de música
            musicaAtual = "Música " + (int)(Math.random() * 100);
            System.out.println("Tocando próxima música: " + musicaAtual);
            
        } else if (estadoAtual.equals(PAUSADO)) {
            musicaAtual = "Música " + (int)(Math.random() * 100);
            System.out.println("Próxima música selecionada: " + musicaAtual);
            System.out.println("Pressione play para tocar");
            
        } else if (estadoAtual.equals(PARADO)) {
            musicaAtual = "Música " + (int)(Math.random() * 100);
            System.out.println("Música selecionada: " + musicaAtual);
            System.out.println("Pressione play para tocar");
            
        } else if (estadoAtual.equals(BLOQUEADO)) {
            System.out.println("Reprodutor bloqueado! Desbloqueie primeiro.");
            
        } else {
            System.out.println("Estado inválido: " + estadoAtual);
        }
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void alterarVolume(int novoVolume) {
        System.out.println("\nComando: ALTERAR VOLUME para " + novoVolume);
        
        if (novoVolume < 0 || novoVolume > 100) {
            System.out.println("Volume deve estar entre 0 e 100");
            return;
        }
        
        if (estadoAtual.equals(TOCANDO)) {
            volume = novoVolume;
            System.out.println("Volume alterado para: " + volume + " (tocando)");
            
        } else if (estadoAtual.equals(PAUSADO)) {
            volume = novoVolume;
            System.out.println("Volume alterado para: " + volume + " (pausado)");
            
        } else if (estadoAtual.equals(PARADO)) {
            volume = novoVolume;
            System.out.println("Volume alterado para: " + volume + " (parado)");
            
        } else if (estadoAtual.equals(BLOQUEADO)) {
            System.out.println("Reprodutor bloqueado! Não é possível alterar volume.");
            
        } else {
            System.out.println("Estado inválido: " + estadoAtual);
        }
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void bloquear() {
        System.out.println("\nComando: BLOQUEAR");
        
        if (!estadoAtual.equals(BLOQUEADO)) {
            estadoAtual = BLOQUEADO;
            System.out.println("Reprodutor bloqueado");
        } else {
            System.out.println("Já está bloqueado");
        }
    }
    
    // PROBLEMA: Método complexo com muitas condicionais
    public void desbloquear() {
        System.out.println("\nComando: DESBLOQUEAR");
        
        if (estadoAtual.equals(BLOQUEADO)) {
            estadoAtual = PARADO;
            System.out.println("Reprodutor desbloqueado");
        } else {
            System.out.println("Não está bloqueado");
        }
    }
    
    // PROBLEMA: Método que expõe estado interno
    public void mostrarEstado() {
        System.out.println("\n=== ESTADO ATUAL ===");
        System.out.println("Estado: " + estadoAtual);
        System.out.println("Música: " + (musicaAtual.isEmpty() ? "Nenhuma" : musicaAtual));
        System.out.println("Volume: " + volume);
        System.out.println("Repetir: " + (repetirPlaylist ? "Sim" : "Não"));
    }
    
    public static void main(String[] args) {
        ReprodutorSemState reprodutor = new ReprodutorSemState();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== REPRODUTOR SEM STATE ===");
        System.out.println("Comandos: play, pause, stop, proxima, volume, bloquear, desbloquear, estado, sair");
        
        // Música inicial
        reprodutor.musicaAtual = "Música Inicial";
        
        while (true) {
            System.out.print("\nComando: ");
            String comando = scanner.nextLine().toLowerCase();
            
            switch (comando) {
                case "play":
                    reprodutor.play();
                    break;
                    
                case "pause":
                    reprodutor.pause();
                    break;
                    
                case "stop":
                    reprodutor.stop();
                    break;
                    
                case "proxima":
                    reprodutor.proximaMusica();
                    break;
                    
                case "volume":
                    System.out.print("Digite o volume (0-100): ");
                    try {
                        int volume = Integer.parseInt(scanner.nextLine());
                        reprodutor.alterarVolume(volume);
                    } catch (NumberFormatException e) {
                        System.out.println("Volume inválido!");
                    }
                    break;
                    
                case "bloquear":
                    reprodutor.bloquear();
                    break;
                    
                case "desbloquear":
                    reprodutor.desbloquear();
                    break;
                    
                case "estado":
                    reprodutor.mostrarEstado();
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
     * 1. COMPLEXIDADE CONDICIONAL: Muitos if/else baseados no estado
     * 
     * 2. VIOLAÇÃO OCP: Adicionar novo estado requer modificar todos os métodos
     * 
     * 3. DUPLICAÇÃO DE CÓDIGO: Verificações de estado repetidas
     * 
     * 4. MANUTENIBILIDADE: Difícil manter e entender a lógica
     * 
     * 5. TESTABILIDADE: Difícil testar todas as combinações de estado
     * 
     * 6. ESCALABILIDADE: Não escala bem com novos estados
     * 
     * 7. ACOPLAMENTO: Lógica de estado misturada com lógica de negócio
     * 
     * 8. ESTADOS INVÁLIDOS: Possibilidade de estados inconsistentes
     * 
     * 9. CÓDIGO REPETITIVO: Mesmas verificações em vários métodos
     * 
     * 10. DIFICULDADE DE EXTENSÃO: Difícil adicionar comportamentos específicos por estado
     */
}