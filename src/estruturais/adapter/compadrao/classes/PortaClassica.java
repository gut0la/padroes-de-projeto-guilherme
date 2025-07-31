package estruturais.adapter.compadrao.classes;

import estruturais.adapter.compadrao.interfaces.Porta;

public class PortaClassica implements Porta {
    public void abrir() {
        System.out.println("Abriu uma porta clássica");
    }
}
