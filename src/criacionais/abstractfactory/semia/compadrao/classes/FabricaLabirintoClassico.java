package criacionais.abstractfactory.semia.compadrao.classes;

import criacionais.abstractfactory.semia.compadrao.interfaces.FabricaLabirinto;
import criacionais.abstractfactory.semia.compadrao.interfaces.Porta;
import criacionais.abstractfactory.semia.compadrao.interfaces.Sala;

public class FabricaLabirintoClassico implements FabricaLabirinto {

    // define métodos para criar os objetos do labirinto clássico
    public Sala criarSala() {
        return new SalaClassica();
    }
    public Porta criarPorta() {
        return new PortaClassica();
    }
}
