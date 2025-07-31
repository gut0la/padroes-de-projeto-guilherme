package estruturais.adapter.sempadrao;

import estruturais.adapter.sempadrao.classes.PortaClassica;
import estruturais.adapter.sempadrao.classes.PortaMagicaExterna;
import estruturais.adapter.sempadrao.interfaces.Porta;

public class JogoLabirintoSemAdapter {
    public static void criarLabirinto(boolean usarPortaExterna) {
        if (!usarPortaExterna) {
            Porta porta = new PortaClassica();
            porta.abrir();
        } else {
            PortaMagicaExterna portaExterna = new PortaMagicaExterna();
            portaExterna.desbloquearComMagia(); // interface diferente, mais trabalho no cliente
        }
    }

    public static void main(String[] args) {
        System.out.println("Criando labirinto com porta clássica:");
        criarLabirinto(false);

        System.out.println("\nCriando labirinto com porta mágica externa:");
        criarLabirinto(true);
    }
}
