package criacionais.builder.compadrao.classes;

import criacionais.builder.compadrao.interfaces.ConstrutorLabirinto;

/**
 * Classe Director do padrão Builder
 * 
 * O Director conhece a sequência de passos necessários para construir
 * um produto complexo. Ele usa a interface Builder para construir o produto,
 * mas não conhece os detalhes de implementação de cada passo.
 * 
 * Esta classe define "como" construir um labirinto (a sequência de passos),
 * enquanto o Builder concreto define "o que" é construído em cada passo.
 */
public class DiretorLabirinto {
    
    /**
     * Método que define o algoritmo de construção do labirinto
     * 
     * Este método conhece a sequência correta de passos para construir
     * um labirinto, mas delega para o construtor a implementação
     * específica de cada passo.
     * 
     * @param construtor - O builder concreto que implementará cada passo
     */
    public void construir(ConstrutorLabirinto construtor) {
        // Define a sequência de construção do labirinto
        construtor.construirSala(1);      // Primeiro: criar sala 1
        construtor.construirSala(2);      // Segundo: criar sala 2
        construtor.construirPorta(1, 2);  // Terceiro: conectar as salas com uma porta
    }
}
