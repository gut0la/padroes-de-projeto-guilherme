package builder.compadrao.classes;


import builder.compadrao.interfaces.ConstrutorLabirinto;

public class ConstrutorLabirintoClassico implements ConstrutorLabirinto {
    private Labirinto labirinto = new Labirinto();

    public void construirSala(int numero) {
        labirinto.adicionarSala("Sala clássica " + numero);
    }

    public void construirPorta(int sala1, int sala2) {
        labirinto.adicionarPorta("Porta clássica entre salas " + sala1 + " e " + sala2);
    }

    public Labirinto getLabirinto() {
        return labirinto;
    }
}

