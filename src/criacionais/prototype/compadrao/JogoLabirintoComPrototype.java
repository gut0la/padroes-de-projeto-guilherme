package criacionais.prototype.compadrao;


import criacionais.prototype.compadrao.classes.*;
import criacionais.prototype.compadrao.compadrao.classes.*;
import criacionais.prototype.compadrao.interfaces.ComponenteLabirinto;

public class JogoLabirintoComPrototype {
    public static void criarLabirinto(GerenciadorPrototipos gerenciador, String tipo) throws CloneNotSupportedException {
        ComponenteLabirinto sala1 = gerenciador.getPrototipo(tipo + "_sala");
        ComponenteLabirinto porta = gerenciador.getPrototipo(tipo + "_porta");
        ComponenteLabirinto sala2 = gerenciador.getPrototipo(tipo + "_sala");

        sala1.mostrar();
        porta.mostrar();
        sala2.mostrar();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        GerenciadorPrototipos gerenciador = new GerenciadorPrototipos();

        // configurando protótipos
        gerenciador.adicionarPrototipo("classico_sala", new SalaClassica());
        gerenciador.adicionarPrototipo("classico_porta", new PortaClassica());
        gerenciador.adicionarPrototipo("encantado_sala", new SalaEncantada());
        gerenciador.adicionarPrototipo("encantado_porta", new PortaEncantada());

        System.out.println("Criando labirinto clássico:");
        criarLabirinto(gerenciador, "classico");

        System.out.println("\nCriando labirinto encantado:");
        criarLabirinto(gerenciador, "encantado");
    }
}
