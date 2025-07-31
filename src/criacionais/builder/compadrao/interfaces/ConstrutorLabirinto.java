package criacionais.builder.compadrao.interfaces;

import criacionais.builder.compadrao.classes.Labirinto;

/**
 * Interface Builder que define os métodos para construir um labirinto
 * 
 * O padrão Builder separa a construção de um objeto complexo
 * de sua representação, permitindo que o mesmo processo de construção
 * crie diferentes representações.
 * 
 * Esta interface define os "passos" para construir um labirinto,
 * mas cada implementação concreta define como cada passo é executado.
 */
public interface ConstrutorLabirinto {
    
    /**
     * Método para construir uma sala no labirinto
     * 
     * @param numero - Número identificador da sala
     */
    void construirSala(int numero);
    
    /**
     * Método para construir uma porta conectando duas salas
     * 
     * @param sala1 - Número da primeira sala
     * @param sala2 - Número da segunda sala
     */
    void construirPorta(int sala1, int sala2);
    
    /**
     * Método para obter o labirinto construído
     * 
     * @return O labirinto completo após a construção
     */
    Labirinto getLabirinto();
}
