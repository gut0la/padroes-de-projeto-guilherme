package factorymethod.compadrao.classes;

import factorymethod.compadrao.abstracts.CriadorLabirinto;
import factorymethod.compadrao.interfaces.Porta;
import factorymethod.compadrao.interfaces.Sala;

public class CriadorLabirintoClassico extends CriadorLabirinto {
    protected Sala criarSala() {
        return new SalaClassica();
    }

    protected Porta criarPorta() {
        return new PortaClassica();
    }
}
