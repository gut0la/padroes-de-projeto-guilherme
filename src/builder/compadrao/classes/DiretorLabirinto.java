package builder.compadrao.classes;


import builder.compadrao.interfaces.ConstrutorLabirinto;

public class DiretorLabirinto {
    public void construir(ConstrutorLabirinto construtor) {
        construtor.construirSala(1);
        construtor.construirSala(2);
        construtor.construirPorta(1, 2);
    }
}
