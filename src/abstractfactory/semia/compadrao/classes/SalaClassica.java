package abstractfactory.semia.compadrao.classes;

import abstractfactory.semia.compadrao.interfaces.Sala;

public class SalaClassica implements Sala {
    // método para entrar na sala clássica
    public void entrar() {
        System.out.println("entrou em uma sala clássica");
    }
}
