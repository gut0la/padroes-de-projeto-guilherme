package criacionais.builder.compadrao;

import criacionais.builder.compadrao.classes.ConstrutorLabirintoClassico;
import criacionais.builder.compadrao.classes.ConstrutorLabirintoEncantado;
import criacionais.builder.compadrao.classes.DiretorLabirinto;

/**
 * Classe principal que demonstra o uso do padrão Builder
 * 
 * O padrão Builder separa a construção de um objeto complexo
 * de sua representação, permitindo que o mesmo processo de construção
 * crie diferentes representações.
 * 
 * Demonstra como o Director usa diferentes Builders para criar
 * produtos com o mesmo algoritmo mas representações diferentes.
 */
public class JogoLabirintoComBuilder {
    
    /**
     * Método principal que demonstra o padrão Builder
     */
    public static void main(String[] args) {
        // O Director conhece a sequência de construção
        DiretorLabirinto diretor = new DiretorLabirinto();

        // Exemplo 1: Construindo labirinto clássico
        System.out.println("Criando labirinto clássico:");
        ConstrutorLabirintoClassico construtorClassico = new ConstrutorLabirintoClassico();
        diretor.construir(construtorClassico);  // Mesmo algoritmo de construção
        construtorClassico.getLabirinto().mostrar();

        // Exemplo 2: Construindo labirinto encantado
        System.out.println("\nCriando labirinto encantado:");
        ConstrutorLabirintoEncantado construtorEncantado = new ConstrutorLabirintoEncantado();
        diretor.construir(construtorEncantado);  // Mesmo algoritmo, resultado diferente
        construtorEncantado.getLabirinto().mostrar();
    }
}
