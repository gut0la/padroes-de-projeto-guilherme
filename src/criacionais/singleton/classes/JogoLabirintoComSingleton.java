package criacionais.singleton.classes;


public class JogoLabirintoComSingleton {
    public static void main(String[] args) {
        System.out.println("Criando labirinto clássico:");
        Labirinto labirinto1 = Labirinto.getInstancia("clássico");
        labirinto1.mostrar();

        System.out.println("\nTentando criar labirinto encantado (mas mantém o mesmo):");
        Labirinto labirinto2 = Labirinto.getInstancia("encantado");
        labirinto2.mostrar(); // Mostra o labirinto clássico, pois é a mesma instância

        System.out.println("\nReiniciando para labirinto encantado:");
        labirinto2.reiniciar("encantado");
        labirinto2.mostrar();
    }
}
