package abstractfactory.semia.compadrao.classes;

import abstractfactory.semia.compadrao.interfaces.Porta;

public class PortaClassica implements Porta {
    // método para abrir a porta clássica
    public void abrir() {
        System.out.println("abriu uma porta clássica");
    }
}

