package criacionais.builder.compadrao.interfaces;

import criacionais.builder.compadrao.classes.Labirinto;

public interface ConstrutorLabirinto {
    void construirSala(int numero);
    void construirPorta(int sala1, int sala2);
    Labirinto getLabirinto();
}
