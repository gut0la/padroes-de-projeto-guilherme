package criacionais.factorymethod.compadrao.abstracts;

import criacionais.factorymethod.compadrao.interfaces.Porta;
import criacionais.factorymethod.compadrao.interfaces.Sala;

public abstract class CriadorLabirinto {
    public void criarLabirinto() {
        Sala sala1 = criarSala();
        Porta porta = criarPorta();
        Sala sala2 = criarSala();

        sala1.entrar();
        porta.abrir();
        sala2.entrar();
    }

    protected abstract Sala criarSala();
    protected abstract Porta criarPorta();
}
