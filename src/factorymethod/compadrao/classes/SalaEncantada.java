package factorymethod.compadrao.classes;

import factorymethod.compadrao.interfaces.Sala;

public class SalaEncantada implements Sala {
    public void entrar() {
        System.out.println("Entrou em uma sala encantada com itens mágicos");
    }
}

