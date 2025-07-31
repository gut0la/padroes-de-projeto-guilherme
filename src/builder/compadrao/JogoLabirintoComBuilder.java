package builder.compadrao;


import builder.compadrao.classes.ConstrutorLabirintoClassico;
import builder.compadrao.classes.ConstrutorLabirintoEncantado;
import builder.compadrao.classes.DiretorLabirinto;

public class JogoLabirintoComBuilder {
    public static void main(String[] args) {
        DiretorLabirinto diretor = new DiretorLabirinto();

        System.out.println("Criando labirinto clássico:");
        ConstrutorLabirintoClassico construtorClassico = new ConstrutorLabirintoClassico();
        diretor.construir(construtorClassico);
        construtorClassico.getLabirinto().mostrar();

        System.out.println("\nCriando labirinto encantado:");
        ConstrutorLabirintoEncantado construtorEncantado = new ConstrutorLabirintoEncantado();
        diretor.construir(construtorEncantado);
        construtorEncantado.getLabirinto().mostrar();
    }
}
