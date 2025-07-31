package criacionais.factorymethod.compadrao.interfaces;

/**
 * Interface que define o contrato para portas no padrão Factory Method
 * 
 * Esta é a interface "Product" no padrão Factory Method.
 * Define o comportamento comum que todos os tipos de porta devem implementar.
 * Permite polimorfismo - tratar diferentes tipos de porta de forma uniforme.
 */
public interface Porta {
    /**
     * Método para abrir a porta
     * Cada implementação concreta define seu comportamento específico
     */
    void abrir();
}
