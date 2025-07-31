package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.abstracts.CriadorLabirinto;
import criacionais.factorymethod.compadrao.interfaces.Porta;
import criacionais.factorymethod.compadrao.interfaces.Sala;

public class CriadorLabirintoClassico extends CriadorLabirinto {
    protected Sala criarSala() {
        return new SalaClassica();
    }

    protected Porta criarPorta() {
        return new PortaClassica();
    }
}
