package estruturais.adapter.sempadrao.classes;

import estruturais.adapter.sempadrao.interfaces.Porta;

public class PortaClassica implements Porta {
    public void abrir() {
        System.out.println("Abriu uma porta clássica");
    }
}
