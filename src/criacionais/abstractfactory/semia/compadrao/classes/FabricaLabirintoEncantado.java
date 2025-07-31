package criacionais.abstractfactory.semia.compadrao.classes;

import criacionais.abstractfactory.semia.compadrao.interfaces.FabricaLabirinto;
import criacionais.abstractfactory.semia.compadrao.interfaces.Porta;
import criacionais.abstractfactory.semia.compadrao.interfaces.Sala;

public class FabricaLabirintoEncantado implements FabricaLabirinto {
    // define a fábrica de labirintos encantados
    public Sala criarSala() {
        return new SalaEncantada();
    }

    public Porta criarPorta() {
        return new PortaEncantada();
    }
}
