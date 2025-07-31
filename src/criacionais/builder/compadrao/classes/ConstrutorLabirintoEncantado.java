package criacionais.builder.compadrao.classes;


import criacionais.builder.compadrao.interfaces.ConstrutorLabirinto;

public class ConstrutorLabirintoEncantado implements ConstrutorLabirinto {
    private Labirinto labirinto = new Labirinto();

    public void construirSala(int numero) {
        labirinto.adicionarSala("Sala encantada com itens mágicos " + numero);
    }

    public void construirPorta(int sala1, int sala2) {
        labirinto.adicionarPorta("Porta encantada com feitiço entre salas " + sala1 + " e " + sala2);
    }

    public Labirinto getLabirinto() {
        return labirinto;
    }
}
