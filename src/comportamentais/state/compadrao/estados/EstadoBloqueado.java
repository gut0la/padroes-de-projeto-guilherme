package comportamentais.state.compadrao.estados;

import comportamentais.state.compadrao.classes.Reprodutor;
import comportamentais.state.compadrao.interfaces.EstadoReprodutor;

/**
 * Estado Bloqueado - Implementação concreta do State
 * 
 * Representa o estado quando o reprodutor está bloqueado.
 * Define comportamentos específicos para cada ação neste estado.
 * 
 * CARACTERÍSTICAS:
 * - Singleton para economizar memória
 * - Comportamento restritivo para estado bloqueado
 * - Apenas permite desbloqueio
 */
public class EstadoBloqueado implements EstadoReprodutor {
    
    private static EstadoBloqueado instancia;
    
    private EstadoBloqueado() {}
    
    public static EstadoBloqueado getInstancia() {
        if (instancia == null) {
            instancia = new EstadoBloqueado();
        }
        return instancia;
    }
    
    @Override
    public void play(Reprodutor reprodutor) {
        System.out.println("🔒 Reprodutor bloqueado! Desbloqueie primeiro.");
        // Estado permanece bloqueado
    }
    
    @Override
    public void pause(Reprodutor reprodutor) {
        System.out.println("🔒 Reprodutor bloqueado! Desbloqueie primeiro.");
        // Estado permanece bloqueado
    }
    
    @Override
    public void stop(Reprodutor reprodutor) {
        System.out.println("🔒 Reprodutor bloqueado! Desbloqueie primeiro.");
        // Estado permanece bloqueado
    }
    
    @Override
    public void proximaMusica(Reprodutor reprodutor) {
        System.out.println("🔒 Reprodutor bloqueado! Desbloqueie primeiro.");
        // Estado permanece bloqueado
    }
    
    @Override
    public void alterarVolume(Reprodutor reprodutor, int novoVolume) {
        System.out.println("🔒 Reprodutor bloqueado! Não é possível alterar volume.");
        // VANTAGEM: Comportamento específico - não permite alteração
        // Estado permanece bloqueado
    }
    
    @Override
    public void bloquear(Reprodutor reprodutor) {
        System.out.println("🔒 Já está bloqueado");
        // Estado permanece bloqueado
    }
    
    @Override
    public void desbloquear(Reprodutor reprodutor) {
        System.out.println("🔓 Reprodutor desbloqueado");
        // VANTAGEM: Transição de estado encapsulada
        reprodutor.setEstado(EstadoParado.getInstancia());
    }
    
    @Override
    public String getNomeEstado() {
        return "BLOQUEADO";
    }
    
    @Override
    public String getDescricaoEstado() {
        return "Reprodutor bloqueado - Apenas desbloqueio permitido";
    }
    
    @Override
    public String toString() {
        return getNomeEstado();
    }
}