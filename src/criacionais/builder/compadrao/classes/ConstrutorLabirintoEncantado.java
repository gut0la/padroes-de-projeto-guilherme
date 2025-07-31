package criacionais.builder.compadrao.classes;

import criacionais.builder.compadrao.interfaces.ConstrutorLabirinto;

/**
 * Builder concreto para construir labirintos encantados
 * 
 * Implementação alternativa da interface ConstrutorLabirinto que
 * cria componentes com características mágicas/encantadas.
 * Demonstra como o mesmo processo de construção pode gerar
 * produtos com representações diferentes.
 */
public class ConstrutorLabirintoEncantado implements ConstrutorLabirinto {
    // Produto sendo construído
    private Labirinto labirinto = new Labirinto();

    /**
     * Constrói uma sala encantada com elementos mágicos
     * 
     * @param numero Número identificador da sala
     */
    public void construirSala(int numero) {
        labirinto.adicionarSala("Sala encantada com itens mágicos " + numero);
    }

    /**
     * Constrói uma porta encantada com feitiços
     * 
     * @param sala1 Número da primeira sala
     * @param sala2 Número da segunda sala
     */
    public void construirPorta(int sala1, int sala2) {
        labirinto.adicionarPorta("Porta encantada com feitiço entre salas " + sala1 + " e " + sala2);
    }

    /**
     * Retorna o labirinto encantado construído
     * 
     * @return O labirinto completo com elementos mágicos
     */
    public Labirinto getLabirinto() {
        return labirinto;
    }
}
