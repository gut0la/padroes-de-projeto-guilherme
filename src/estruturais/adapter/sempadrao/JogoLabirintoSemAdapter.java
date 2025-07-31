package estruturais.adapter.sempadrao;

import estruturais.adapter.sempadrao.classes.PortaClassica;
import estruturais.adapter.sempadrao.classes.PortaMagicaExterna;
import estruturais.adapter.sempadrao.interfaces.Porta;

/**
 * Exemplo SEM o padrão Adapter
 * 
 * Demonstra os problemas que o Adapter resolve:
 * - Cliente precisa conhecer diferentes interfaces
 * - Código condicional para tratar tipos diferentes
 * - Impossibilidade de tratar objetos uniformemente
 * - Violação do princípio Aberto/Fechado
 */
public class JogoLabirintoSemAdapter {
    
    /**
     * Método que precisa tratar diferentes tipos de porta
     * Problema: cliente precisa conhecer as interfaces específicas
     */
    public static void criarLabirinto(boolean usarPortaExterna) {
        if (!usarPortaExterna) {
            // Usa interface padrão do sistema
            Porta porta = new PortaClassica();
            porta.abrir();
        } else {
            // Precisa usar interface diferente - quebra uniformidade
            PortaMagicaExterna portaExterna = new PortaMagicaExterna();
            portaExterna.desbloquearComMagia(); // Método com nome diferente!
        }
        // Problemas:
        // 1. Código condicional
        // 2. Cliente acoplado a implementações específicas
        // 3. Difícil adicionar novos tipos de porta
    }

    public static void main(String[] args) {
        System.out.println("Criando labirinto com porta clássica:");
        criarLabirinto(false);

        System.out.println("\nCriando labirinto com porta mágica externa:");
        criarLabirinto(true);
    }
}
