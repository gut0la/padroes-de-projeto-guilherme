package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.interfaces.Porta;

public class PortaEncantada implements Porta {
    public void abrir() {
        System.out.println("Abriu uma porta encantada com um feitiço");
    }
}