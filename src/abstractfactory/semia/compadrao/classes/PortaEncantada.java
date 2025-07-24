package abstractfactory.semia.compadrao.classes;

import abstractfactory.semia.compadrao.interfaces.Porta;

class PortaEncantada implements Porta {
    // método para abrir a porta encantada
    public void abrir() {
        System.out.println("abriu uma porta encantada com um feitiço");
    }
}

