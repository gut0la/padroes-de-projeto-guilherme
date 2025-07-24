package abstractfactory.semia.sempadrao.classes;

import abstractfactory.semia.sempadrao.interfaces.Sala;

public class SalaClassica implements Sala {
    // método para entrar na sala clássica
    public void entrar() {
        System.out.println("entrou em uma sala clássica");
    }
}
