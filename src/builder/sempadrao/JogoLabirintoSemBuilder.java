package builder.sempadrao;

public class JogoLabirintoSemBuilder {
    public static void criarLabirinto(String tipo) {
        if (tipo.equals("classico")) {
            System.out.println("Criando labirinto clássico:");
            System.out.println("Adicionando sala clássica 1");
            System.out.println("Adicionando sala clássica 2");
            System.out.println("Adicionando porta clássica entre salas");
        } else if (tipo.equals("encantado")) {
            System.out.println("Criando labirinto encantado:");
            System.out.println("Adicionando sala encantada com itens mágicos 1");
            System.out.println("Adicionando sala encantada com itens mágicos 2");
            System.out.println("Adicionando porta encantada que precisa de feitiço");
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
