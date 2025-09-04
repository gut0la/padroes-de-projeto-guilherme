package aula.comportamentais.templatemethod;

public class Bebida {

    void prepararReceita() {
        ferverAgua();
        adicionarIngrediente();
        servir();
    }

    void ferverAgua() {
        System.out.println("Fervendo água");
    }

    void adicionarIngrediente() {
        // Método a ser implementado pelas subclasses
    }

    void servir() {
        System.out.println("Servindo na xícara");
    }
}
