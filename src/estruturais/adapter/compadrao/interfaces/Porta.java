package estruturais.adapter.compadrao.interfaces;

/**
 * Interface que define o contrato para todas as portas do sistema
 * 
 * Esta é a interface "alvo" (Target) no padrão Adapter.
 * Define o método que o cliente espera usar.
 */
public interface Porta {
    /**
     * Método padrão para abrir uma porta
     * Todas as classes que implementam esta interface devem fornecer
     * uma implementação para este método
     */
    void abrir();
}
