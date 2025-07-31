package estruturais.adapter.compadrao.classes;

/**
 * Classe externa que representa uma porta mágica
 * 
 * Esta é a classe "Adaptee" no padrão Adapter.
 * Ela NÃO implementa a interface Porta do nosso sistema,
 * mas possui funcionalidade similar com nome de método diferente.
 * 
 * Simula uma biblioteca externa ou código legado que não podemos modificar.
 */
public class PortaMagicaExterna {
    
    /**
     * Método com nome diferente do esperado pela interface Porta
     * Este método faz a mesma coisa que abrir(), mas com nome diferente
     * Por isso precisamos de um Adapter para "traduzir" a chamada
     */
    public void desbloquearComMagia() {
        System.out.println("Desbloqueou uma porta mágica externa com magia");
    }
}
