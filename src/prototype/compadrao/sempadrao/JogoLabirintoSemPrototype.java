package prototype.compadrao.sempadrao;

public class JogoLabirintoSemPrototype {
    public static void criarLabirinto(String tipo) {
        if (tipo.equals("classico")) {
            System.out.println("Criando sala clássica");
            System.out.println("Criando porta clássica");
        } else if (tipo.equals("encantado")) {
            System.out.println("Criando sala encantada com itens mágicos");
            System.out.println("Criando porta que precisa de feitiço");
        } else {
            System.out.println("Tipo de labirinto não reconhecido!");
        }
    }

    public static void main(String[] args) {
        criarLabirinto("classico");
        System.out.println();
        criarLabirinto("encantado");
    }
}