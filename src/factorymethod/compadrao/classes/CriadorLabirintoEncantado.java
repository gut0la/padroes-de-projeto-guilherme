package factorymethod.compadrao.classes;


import factorymethod.compadrao.abstracts.CriadorLabirinto;
import factorymethod.compadrao.interfaces.Porta;
import factorymethod.compadrao.interfaces.Sala;

public class CriadorLabirintoEncantado extends CriadorLabirinto {
    protected Sala criarSala() {
        return new SalaEncantada();
    }

    protected Porta criarPorta() {
        return new PortaEncantada();
    }
}
