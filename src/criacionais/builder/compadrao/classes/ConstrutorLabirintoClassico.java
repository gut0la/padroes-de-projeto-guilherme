package criacionais.builder.compadrao.classes;

import criacionais.builder.compadrao.interfaces.ConstrutorLabirinto;

/**
 * Builder concreto para construir labirintos clássicos
 * 
 * Esta é uma implementação concreta da interface ConstrutorLabirinto.
 * Define como construir cada componente específico de um labirinto clássico.
 * Mantém uma referência ao produto sendo construído.
 */
public class ConstrutorLabirintoClassico implements ConstrutorLabirinto {
    // Produto sendo construído
    private Labirinto labirinto = new Labirinto();

    /**
     * Constrói uma sala clássica
     * 
     * @param numero Número identificador da sala
     */
    public void construirSala(int numero) {
        labirinto.adicionarSala("Sala clássica " + numero);
    }

    /**
     * Constrói uma porta clássica conectando duas salas
     * 
     * @param sala1 Número da primeira sala
     * @param sala2 Número da segunda sala
     */
    public void construirPorta(int sala1, int sala2) {
        labirinto.adicionarPorta("Porta clássica entre salas " + sala1 + " e " + sala2);
    }

    /**
     * Retorna o labirinto clássico construído
     * 
     * @return O labirinto completo
     */
    public Labirinto getLabirinto() {
        return labirinto;
    }
}

