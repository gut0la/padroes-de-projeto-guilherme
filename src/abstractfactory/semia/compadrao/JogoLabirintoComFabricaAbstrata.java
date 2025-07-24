package abstractfactory.semia.compadrao;

import abstractfactory.semia.compadrao.interfaces.FabricaLabirinto;
import abstractfactory.semia.compadrao.classes.FabricaLabirintoEncantado;
import abstractfactory.semia.compadrao.interfaces.Porta;
import abstractfactory.semia.compadrao.interfaces.Sala;
import abstractfactory.semia.compadrao.classes.FabricaLabirintoClassico;

public class JogoLabirintoComFabricaAbstrata {
    // método para criar um labirinto usando uma fábrica abstrata
    // chama os métodos da fábrica para criar salas e portas
    // usa interfaces para garantir que o código funcione com diferentes implementações de fábricas
    public static void criarLabirinto(FabricaLabirinto fabrica) {
        Sala sala1 = fabrica.criarSala();
        Porta porta = fabrica.criarPorta();
        Sala sala2 = fabrica.criarSala();

        sala1.entrar();
        porta.abrir();
        sala2.entrar();
    }

    // chama o método criarLabirinto com diferentes fábricas
    public static void main(String[] args) {
        System.out.println("criando labirinto clássico:");
        criarLabirinto(new FabricaLabirintoClassico());

        System.out.println("criando labirinto encantado:");
        criarLabirinto(new FabricaLabirintoEncantado());
    }
}
