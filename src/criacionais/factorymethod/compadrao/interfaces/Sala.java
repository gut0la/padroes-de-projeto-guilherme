package criacionais.factorymethod.compadrao.interfaces;

/**
 * Interface que define o contrato para salas no padrão Factory Method
 * 
 * Interface "Product" que estabelece o comportamento comum
 * para todos os tipos de sala. Permite que o código cliente
 * trabalhe com diferentes tipos de sala de forma uniforme.
 */
public interface Sala {
    /**
     * Método para entrar na sala
     * Cada tipo de sala implementa seu comportamento específico
     */
    void entrar();
}
