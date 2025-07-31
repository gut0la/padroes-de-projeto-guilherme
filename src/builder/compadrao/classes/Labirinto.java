package builder.compadrao.classes;

public class Labirinto {
    private String salas = "";
    private String portas = "";

    public void adicionarSala(String sala) {
        salas += sala + "\n";
    }

    public void adicionarPorta(String porta) {
        portas += porta + "\n";
    }

    public void mostrar() {
        System.out.println("Labirinto:\nSalas:\n" + salas + "Portas:\n" + portas);
    }
}