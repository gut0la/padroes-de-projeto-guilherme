package abstractfactory.semia.sempadrao.classes;

import abstractfactory.semia.sempadrao.interfaces.Sala;

public class SalaEncantada implements Sala {
    // método para entrar na sala encantada
    public void entrar() {
        System.out.println("entrou em uma sala encantada com itens mágicos");
    }
}
