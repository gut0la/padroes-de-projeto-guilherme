package abstractfactory.semia.sempadrao;


public class JogoLabirintoSemPadrao {
    public static void criarLabirinto(String tipo) {
        if (tipo.equals("classico")) {
            System.out.println("criando sala clássica");
            System.out.println("criando porta clássica");
        } else if (tipo.equals("encantado")) {
            System.out.println("criando sala encantada com itens mágicos");
            System.out.println("criando porta que precisa de feitiço");
        } else {
            System.out.println("tipo de labirinto não reconhecido!");
        }
    }

    // chama na main direto com função criarLabirinto
    public static void main(String[] args) {
        criarLabirinto("classico");
        System.out.println();
        criarLabirinto("encantado");
    }
}
