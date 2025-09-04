package aula.comportamentais.templatemethod;

public class Principal {

    public static void main (String[] args) {
        Bebida cha = new Cha();
        System.out.println("Preparando chá:");
        cha.prepararReceita();
        System.out.println();

        Bebida cafe = new Cafe();
        System.out.println("Preparando café:");
        cafe.prepararReceita();
    }
}
