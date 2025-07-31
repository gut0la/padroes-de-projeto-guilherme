package criacionais.factorymethod.compadrao;

import criacionais.factorymethod.compadrao.abstracts.CriadorLabirinto;
import criacionais.factorymethod.compadrao.classes.CriadorLabirintoClassico;
import criacionais.factorymethod.compadrao.classes.CriadorLabirintoEncantado;

public class JogoLabirintoComFactoryMethod {
    public static void main(String[] args) {
        System.out.println("Criando labirinto clássico:");
        CriadorLabirinto criadorClassico = new CriadorLabirintoClassico();
        criadorClassico.criarLabirinto();

        System.out.println("\nCriando labirinto encantado:");
        CriadorLabirinto criadorEncantado = new CriadorLabirintoEncantado();
        criadorEncantado.criarLabirinto();
    }
}
