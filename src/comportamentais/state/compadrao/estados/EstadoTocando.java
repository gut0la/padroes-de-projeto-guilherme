package comportamentais.state.compadrao.estados;

import comportamentais.state.compadrao.classes.Reprodutor;
import comportamentais.state.compadrao.interfaces.EstadoReprodutor;

/**
 * Estado Tocando - Implementação concreta do State
 * 
 * Representa o estado quando o reprodutor está tocando música.
 * Define comportamentos específicos para cada ação neste estado.
 * 
 * CARACTERÍSTICAS:
 * - Singleton para economizar memória
 * - Comportamento específico para estado tocando
 * - Gerencia transições para outros estados
 */
public class EstadoTocando implements EstadoReprodutor {
    
    private static EstadoTocando instancia;
    
    private EstadoTocando() {}
    
    public static EstadoTocando getInstancia() {
        if (instancia == null) {
            instancia = new EstadoTocando();
        }
        return instancia;
    }
    
    @Override
    public void play(Reprodutor reprodutor) {
        System.out.println("Já está tocando: " + reprodutor.getMusicaAtual());
        // Estado permanece o mesmo
    }
    
    @Override
    public void pause(Reprodutor reprodutor) {
        System.out.println("Reprodução pausada: " + reprodutor.getMusicaAtual());
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoPausado.getInstancia());
    }
    
    @Override
    public void stop(Reprodutor reprodutor) {
        System.out.println("Reprodução parada");
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoParado.getInstancia());
    }
    
    @Override
    public void proximaMusica(Reprodutor reprodutor) {
        // Simula mudança de música durante reprodução
        String novaMusica = "Música " + (int)(Math.random() * 100);
        reprodutor.setMusicaAtual(novaMusica);
        System.out.println("Tocando próxima música: " + novaMusica);
        // Estado permanece tocando
    }
    
    @Override
    public void alterarVolume(Reprodutor reprodutor, int novoVolume) {
        if (novoVolume < 0 || novoVolume > 100) {
            System.out.println("Volume deve estar entre 0 e 100");
            return;
        }
        
        reprodutor.setVolume(novoVolume);
        System.out.println("Volume alterado para: " + novoVolume + " (tocando)");
        
        // VANTAGEM: Comportamento específico do estado
        if (novoVolume == 0) {
            System.out.println("♪ Música mutada mas ainda tocando ♪");
        } else if (novoVolume > 80) {
            System.out.println("♪♪♪ Volume alto! ♪♪♪");
        } else {
            System.out.println("♪ Tocando com volume " + novoVolume + " ♪");
        }
        // Estado permanece o mesmo
    }
    
    @Override
    public void bloquear(Reprodutor reprodutor) {
        System.out.println("Parando reprodução e bloqueando reprodutor");
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
        return "TOCANDO";
    }
    
    @Override
    public String getDescricaoEstado() {
        return "Reproduzindo música ativamente";
    }
    
    @Override
    public String toString() {
        return getNomeEstado();
    }
}