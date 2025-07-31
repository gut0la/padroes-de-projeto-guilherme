package estruturais.adapter.compadrao;

// Importações necessárias para o padrão Adapter
import estruturais.adapter.compadrao.adapter.AdapterPortaMagica;
import estruturais.adapter.compadrao.classes.PortaClassica;
import estruturais.adapter.compadrao.classes.PortaMagicaExterna;
import estruturais.adapter.compadrao.interfaces.Porta;

/**
 * Classe principal que demonstra o uso do padrão Adapter
 * 
 * O padrão Adapter permite que classes com interfaces incompatíveis trabalhem juntas.
 * Neste exemplo, temos uma PortaMagicaExterna que não implementa a interface Porta,
 * mas através do AdapterPortaMagica, conseguimos usá-la como se fosse uma Porta.
 */
public class JogoLabirintoComAdapter {
    
    /**
     * Método que cria um labirinto usando qualquer tipo de porta
     * que implemente a interface Porta
     * 
     * @param porta - Qualquer objeto que implemente a interface Porta
     */
    public static void criarLabirinto(Porta porta) {
        // Chama o método abrir() da interface Porta
        // Funciona tanto para PortaClassica quanto para PortaMagicaExterna (via adapter)
        porta.abrir();
    }

    /**
     * Método principal que demonstra o uso do padrão Adapter
     */
    public static void main(String[] args) {
        // Exemplo 1: Usando uma porta que já implementa a interface Porta
        System.out.println("Criando labirinto com porta clássica:");
        criarLabirinto(new PortaClassica());

        // Exemplo 2: Usando uma porta externa que NÃO implementa a interface Porta
        // Precisamos do Adapter para fazer a "tradução" entre as interfaces
        System.out.println("\nCriando labirinto com porta mágica externa:");
        PortaMagicaExterna portaExterna = new PortaMagicaExterna();
        // O AdapterPortaMagica "adapta" a PortaMagicaExterna para funcionar como uma Porta
        criarLabirinto(new AdapterPortaMagica(portaExterna));
    }
}
