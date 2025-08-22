package comportamentais.state.compadrao.estados;

import comportamentais.state.compadrao.classes.Reprodutor;
import comportamentais.state.compadrao.interfaces.EstadoReprodutor;

/**
 * Estado Pausado - Implementação concreta do State
 * 
 * Representa o estado quando o reprodutor está pausado.
 * Define comportamentos específicos para cada ação neste estado.
 * 
 * CARACTERÍSTICAS:
 * - Singleton para economizar memória
 * - Comportamento específico para estado pausado
 * - Gerencia transições para outros estados
 */
public class EstadoPausado implements EstadoReprodutor {
    
    private static EstadoPausado instancia;
    
    private EstadoPausado() {}
    
    public static EstadoPausado getInstancia() {
        if (instancia == null) {
            instancia = new EstadoPausado();
        }
        return instancia;
    }
    
    @Override
    public void play(Reprodutor reprodutor) {
        System.out.println("Retomando reprodução: " + reprodutor.getMusicaAtual());
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoTocando.getInstancia());
    }
    
    @Override
    public void pause(Reprodutor reprodutor) {
        System.out.println("Já está pausado");
        // Estado permanece o mesmo
    }
    
    @Override
    public void stop(Reprodutor reprodutor) {
        System.out.println("Reprodução parada");
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoParado.getInstancia());
    }
    
    @Override
    public void proximaMusica(Reprodutor reprodutor) {
        // Simula mudança de música enquanto pausado
        String novaMusica = "Música " + (int)(Math.random() * 100);
        reprodutor.setMusicaAtual(novaMusica);
        System.out.println("Próxima música selecionada: " + novaMusica);
        System.out.println("Pressione play para tocar");
        // Estado permanece pausado
    }
    
    @Override
    public void alterarVolume(Reprodutor reprodutor, int novoVolume) {
        if (novoVolume < 0 || novoVolume > 100) {
            System.out.println("Volume deve estar entre 0 e 100");
            return;
        }
        
        reprodutor.setVolume(novoVolume);
        System.out.println("Volume alterado para: " + novoVolume + " (pausado)");
        
        // VANTAGEM: Comportamento específico do estado
        System.out.println("⏸ Volume ajustado - Música pausada ⏸");
        // Estado permanece o mesmo
    }
    
    @Override
    public void bloquear(Reprodutor reprodutor) {
        System.out.println("Bloqueando reprodutor (estava pausado)");
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
        return "PAUSADO";
    }
    
    @Override
    public String getDescricaoEstado() {
        return "Reprodução pausada - Pronto para retomar";
    }
    
    @Override
    public String toString() {
        return getNomeEstado();
    }
}