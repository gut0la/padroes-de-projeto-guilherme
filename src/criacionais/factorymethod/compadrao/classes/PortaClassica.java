package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.interfaces.Porta;

public class PortaClassica implements Porta {
    public void abrir() {
        System.out.println("Abriu uma porta clássica");
    }
}
