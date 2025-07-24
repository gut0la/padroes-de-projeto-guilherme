package abstractfactory.semia.sempadrao.classes;

import abstractfactory.semia.sempadrao.interfaces.FabricaLabirinto;
import abstractfactory.semia.sempadrao.interfaces.Porta;
import abstractfactory.semia.sempadrao.interfaces.Sala;

public class FabricaLabirintoEncantado implements FabricaLabirinto {
    // define a fábrica de labirintos encantados
    public Sala criarSala() {
        return new SalaEncantada();
    }

    public Porta criarPorta() {
        return new PortaEncantada();
    }
}
