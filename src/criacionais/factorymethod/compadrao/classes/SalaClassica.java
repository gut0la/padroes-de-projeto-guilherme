package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.interfaces.Sala;

public class SalaClassica implements Sala {
    public void entrar() {
        System.out.println("Entrou em uma sala clássica");
    }
}