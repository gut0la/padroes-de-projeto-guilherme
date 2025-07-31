package estruturais.adapter.compadrao.adapter;

import estruturais.adapter.compadrao.classes.PortaMagicaExterna;
import estruturais.adapter.compadrao.interfaces.Porta;

public class AdapterPortaMagica implements Porta {
    private PortaMagicaExterna portaExterna;

    public AdapterPortaMagica(PortaMagicaExterna portaExterna) {
        this.portaExterna = portaExterna;
    }

    public void abrir() {
        portaExterna.desbloquearComMagia();
    }
}
