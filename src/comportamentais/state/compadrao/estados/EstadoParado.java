package comportamentais.state.compadrao.estados;

import comportamentais.state.compadrao.classes.Reprodutor;
import comportamentais.state.compadrao.interfaces.EstadoReprodutor;

/**
 * Estado Parado - Implementação concreta do State
 * 
 * Representa o estado quando o reprodutor está parado.
 * Define comportamentos específicos para cada ação neste estado.
 * 
 * CARACTERÍSTICAS:
 * - Singleton para economizar memória
 * - Comportamento específico para estado parado
 * - Gerencia transições para outros estados
 */
public class EstadoParado implements EstadoReprodutor {
    
    private static EstadoParado instancia;
    
    private EstadoParado() {}
    
    public static EstadoParado getInstancia() {
        if (instancia == null) {
            instancia = new EstadoParado();
        }
        return instancia;
    }
    
    @Override
    public void play(Reprodutor reprodutor) {
        if (reprodutor.getMusicaAtual() == null || reprodutor.getMusicaAtual().isEmpty()) {
            System.out.println("Erro: Nenhuma música selecionada!");
            return;
        }
        
        System.out.println("Iniciando reprodução: " + reprodutor.getMusicaAtual());
        System.out.println("Volume: " + reprodutor.getVolume());
        
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoTocando.getInstancia());
    }
    
    @Override
    public void pause(Reprodutor reprodutor) {
        System.out.println("Não há música tocando para pausar");
        // Estado permanece o mesmo
    }
    
    @Override
    public void stop(Reprodutor reprodutor) {
        System.out.println("Já está parado");
        // Estado permanece o mesmo
    }
    
    @Override
    public void proximaMusica(Reprodutor reprodutor) {
        // Simula seleção de próxima música
        String novaMusica = "Música " + (int)(Math.random() * 100);
        reprodutor.setMusicaAtual(novaMusica);
        System.out.println("Música selecionada: " + novaMusica);
        System.out.println("Pressione play para tocar");
        // Estado permanece parado
    }
    
    @Override
    public void alterarVolume(Reprodutor reprodutor, int novoVolume) {
        if (novoVolume < 0 || novoVolume > 100) {
            System.out.println("Volume deve estar entre 0 e 100");
            return;
        }
        
        reprodutor.setVolume(novoVolume);
        System.out.println("Volume alterado para: " + novoVolume + " (parado)");
        // Estado permanece o mesmo
    }
    
    @Override
    public void bloquear(Reprodutor reprodutor) {
        System.out.println("Reprodutor bloqueado");
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoBloqueado.getInstancia());
    }
    
    @Override
    public void desbloquear(Reprodutor reprodutor) {
        System.out.println("Não está bloqueado");
        // Estado permanece o mesmo
    }
    
    @Override
    public String getNomeEstado() {
        return "PARADO";
    }
    
    @Override
    public String getDescricaoEstado() {
        return "Reprodutor parado - Pronto para tocar";
    }
    
    @Override
    public String toString() {
        return getNomeEstado();
    }
}