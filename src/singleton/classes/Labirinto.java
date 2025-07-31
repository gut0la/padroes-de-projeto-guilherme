package singleton.classes;

public class Labirinto {
    private static Labirinto instanciaUnica;
    private String tipo;

    private Labirinto(String tipo) {
        this.tipo = tipo;
    }

    public static Labirinto getInstancia(String tipo) {
        if (instanciaUnica == null) {
            instanciaUnica = new Labirinto(tipo);
        }
        return instanciaUnica;
    }

    public void mostrar() {
        System.out.println("Labirinto " + tipo + " ativo");
    }

    public void reiniciar(String tipo) {
        instanciaUnica = new Labirinto(tipo);
    }
}

