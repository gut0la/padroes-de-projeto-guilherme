package estruturais.adapter.compadrao;

import estruturais.adapter.compadrao.adapter.AdapterPortaMagica;
import estruturais.adapter.compadrao.classes.PortaClassica;
import estruturais.adapter.compadrao.classes.PortaMagicaExterna;
import estruturais.adapter.compadrao.interfaces.Porta;

public class JogoLabirintoComAdapter {
    public static void criarLabirinto(Porta porta) {
        porta.abrir();
    }

    public static void main(String[] args) {
        System.out.println("Criando labirinto com porta clássica:");
        criarLabirinto(new PortaClassica());

        System.out.println("\nCriando labirinto com porta mágica externa:");
        PortaMagicaExterna portaExterna = new PortaMagicaExterna();
        criarLabirinto(new AdapterPortaMagica(portaExterna));
    }
}
