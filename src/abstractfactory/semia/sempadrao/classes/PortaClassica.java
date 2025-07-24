package abstractfactory.semia.sempadrao.classes;

import abstractfactory.semia.sempadrao.interfaces.Porta;

class PortaClassica implements Porta {
    // método para abrir a porta clássica
    public void abrir() {
        System.out.println("abriu uma porta clássica");
    }
}

