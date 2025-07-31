package criacionais.singleton.sempadrao;

public class JogoLabirintoSemSingleton {
    static class Labirinto {
        private String tipo;

        public Labirinto(String tipo) {
            this.tipo = tipo;
        }

        public void mostrar() {
            System.out.println("Labirinto " + tipo + " ativo");
        }
    }

    public static void main(String[] args) {
        Labirinto labirinto1 = new Labirinto("clássico");
        Labirinto labirinto2 = new Labirinto("encantado");
        labirinto1.mostrar();
        labirinto2.mostrar(); // Dois labirintos ativos, causando confusão
    }
}