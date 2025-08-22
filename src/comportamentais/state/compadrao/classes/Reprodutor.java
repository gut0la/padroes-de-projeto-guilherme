package comportamentais.state.compadrao.classes;

import comportamentais.state.compadrao.estados.EstadoParado;
import comportamentais.state.compadrao.interfaces.EstadoReprodutor;

/**
 * Classe Reprodutor - Context no padrão State
 * 
 * Mantém uma referência para o estado atual e delega
 * todas as operações específicas de estado para o objeto estado.
 * 
 * VANTAGENS:
 * - Foco na lógica de negócio
 * - Delegação de comportamento para estados
 * - Código limpo sem condicionais complexas
 * - Fácil extensão com novos estados
 */
public class Reprodutor {
    
    private EstadoReprodutor estadoAtual;
    private String musicaAtual;
    private int volume;
    private boolean repetirPlaylist;
    
    public Reprodutor() {
        // VANTAGEM: Estado inicial bem definido
        this.estadoAtual = EstadoParado.getInstancia();
        this.musicaAtual = "";
        this.volume = 50;
        this.repetirPlaylist = false;
    }
    
    // Métodos que delegam para o estado atual
    
    public void play() {
        System.out.println("\nComando: PLAY");
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.play(this);
    }
    
    public void pause() {
        System.out.println("\nComando: PAUSE");
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.pause(this);
    }
    
    public void stop() {
        System.out.println("\nComando: STOP");
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.stop(this);
    }
    
    public void proximaMusica() {
        System.out.println("\nComando: PRÓXIMA MÚSICA");
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.proximaMusica(this);
    }
    
    public void alterarVolume(int novoVolume) {
        System.out.println("\nComando: ALTERAR VOLUME para " + novoVolume);
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.alterarVolume(this, novoVolume);
    }
    
    public void bloquear() {
        System.out.println("\nComando: BLOQUEAR");
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.bloquear(this);
    }
    
    public void desbloquear() {
        System.out.println("\nComando: DESBLOQUEAR");
        // VANTAGEM: Delegação simples - sem condicionais
        estadoAtual.desbloquear(this);
    }
    
    // Métodos de gerenciamento de estado
    
    /**
     * Altera o estado atual do reprodutor
     * Chamado pelos próprios estados para fazer transições
     * 
     * @param novoEstado Novo estado a ser definido
     */
    public void setEstado(EstadoReprodutor novoEstado) {
        if (novoEstado != null) {
            this.estadoAtual = novoEstado;
            System.out.println("[Estado alterado para: " + novoEstado.getNomeEstado() + "]");
        }
    }
    
    public EstadoReprodutor getEstado() {
        return estadoAtual;
    }
    
    // Getters e Setters para propriedades do reprodutor
    
    public String getMusicaAtual() {
        return musicaAtual;
    }
    
    public void setMusicaAtual(String musicaAtual) {
        this.musicaAtual = musicaAtual;
    }
    
    public int getVolume() {
        return volume;
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    public boolean isRepetirPlaylist() {
        return repetirPlaylist;
    }
    
    public void setRepetirPlaylist(boolean repetirPlaylist) {
        this.repetirPlaylist = repetirPlaylist;
        System.out.println("Repetir playlist: " + (repetirPlaylist ? "Ativado" : "Desativado"));
    }
    
    // Métodos de utilidade
    
    public void mostrarEstado() {
        System.out.println("\n=== ESTADO ATUAL ===");
        System.out.println("Estado: " + estadoAtual.getNomeEstado());
        System.out.println("Descrição: " + estadoAtual.getDescricaoEstado());
        System.out.println("Música: " + (musicaAtual.isEmpty() ? "Nenhuma" : musicaAtual));
        System.out.println("Volume: " + volume);
        System.out.println("Repetir: " + (repetirPlaylist ? "Sim" : "Não"));
    }
    
    /**
     * Método para demonstrar flexibilidade do padrão
     * Permite verificar se uma ação é válida sem executá-la
     */
    public boolean podeExecutarAcao(String acao) {
        // Em uma implementação mais avançada, cada estado poderia
        // ter um método para verificar se uma ação é válida
        switch (acao.toLowerCase()) {
            case "play":
                return !estadoAtual.getNomeEstado().equals("BLOQUEADO");
            case "pause":
                return estadoAtual.getNomeEstado().equals("TOCANDO");
            case "stop":
                return estadoAtual.getNomeEstado().equals("TOCANDO") || 
                       estadoAtual.getNomeEstado().equals("PAUSADO");
            case "desbloquear":
                return estadoAtual.getNomeEstado().equals("BLOQUEADO");
            default:
                return true;
        }
    }
    
    /**
     * Método para obter ações disponíveis no estado atual
     */
    public String[] getAcoesDisponiveis() {
        String nomeEstado = estadoAtual.getNomeEstado();
        
        switch (nomeEstado) {
            case "PARADO":
                return new String[]{"play", "proxima", "volume", "bloquear"};
            case "TOCANDO":
                return new String[]{"pause", "stop", "proxima", "volume", "bloquear"};
            case "PAUSADO":
                return new String[]{"play", "stop", "proxima", "volume", "bloquear"};
            case "BLOQUEADO":
                return new String[]{"desbloquear"};
            default:
                return new String[]{};
        }
    }
    
    @Override
    public String toString() {
        return "Reprodutor[estado=" + estadoAtual.getNomeEstado() + 
               ", musica=" + musicaAtual + 
               ", volume=" + volume + "]"; 
    }
}