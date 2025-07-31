package factorymethod.compadrao.classes;

import factorymethod.compadrao.interfaces.Porta;

public class PortaClassica implements Porta {
    public void abrir() {
        System.out.println("Abriu uma porta clássica");
    }
}
