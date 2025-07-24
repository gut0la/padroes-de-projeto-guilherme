package abstractfactory.semia.sempadrao.classes;

import abstractfactory.semia.sempadrao.interfaces.FabricaLabirinto;
import abstractfactory.semia.sempadrao.interfaces.Porta;
import abstractfactory.semia.sempadrao.interfaces.Sala;

public class FabricaLabirintoClassico implements FabricaLabirinto {

    // define métodos para criar os objetos do labirinto clássico
    public Sala criarSala() {
        return new SalaClassica();
    }
    public Porta criarPorta() {
        return new PortaClassica();
    }
}
