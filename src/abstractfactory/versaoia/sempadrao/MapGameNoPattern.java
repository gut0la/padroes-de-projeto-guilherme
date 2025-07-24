package abstractfactory.versaoia.sempadrao;

public class MapGameNoPattern {
    public static void createMap(String theme) {
        if (theme.equals("forest")) {
            System.out.println("Criando terreno: Floresta Verde");
            System.out.println("Criando obstáculo: Árvore");
        } else if (theme.equals("desert")) {
            System.out.println("Criando terreno: Areia Quente");
            System.out.println("Criando obstáculo: Cacto");
        } else {
            System.out.println("Tema não suportado!");
        }
    }

    public static void main(String[] args) {
        createMap("forest");
        createMap("desert");
    }
}