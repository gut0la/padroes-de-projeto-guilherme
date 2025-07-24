package abstractfactory.semia.compadrao.classes;

import abstractfactory.semia.compadrao.interfaces.Sala;

public class SalaEncantada implements Sala {
    // método para entrar na sala encantada
    public void entrar() {
        System.out.println("entrou em uma sala encantada com itens mágicos");
    }
}
