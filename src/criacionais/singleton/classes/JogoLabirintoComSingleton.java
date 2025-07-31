package criacionais.singleton.classes;

/**
 * Classe que demonstra o uso do padrão Singleton
 * 
 * Esta classe mostra como o Singleton garante que apenas uma instância
 * da classe Labirinto exista, mesmo quando tentamos criar "novas" instâncias.
 */
public class JogoLabirintoComSingleton {
    
    /**
     * Método principal que demonstra o comportamento do Singleton
     */
    public static void main(String[] args) {
        // Primeira chamada: cria a instância única
        System.out.println("Criando labirinto clássico:");
        Labirinto labirinto1 = Labirinto.getInstancia("clássico");
        labirinto1.mostrar();

        // Segunda chamada: retorna a mesma instância (ignora o parâmetro "encantado")
        System.out.println("\nTentando criar labirinto encantado (mas mantém o mesmo):");
        Labirinto labirinto2 = Labirinto.getInstancia("encantado");
        labirinto2.mostrar(); // Mostra o labirinto clássico, pois é a mesma instância
        
        // Demonstra que labirinto1 e labirinto2 são a mesma instância
        System.out.println("labirinto1 == labirinto2: " + (labirinto1 == labirinto2));

        // Usando o método reiniciar para alterar o tipo
        System.out.println("\nReiniciando para labirinto encantado:");
        labirinto2.reiniciar("encantado");
        labirinto2.mostrar();
        
        // Ambas as referências agora apontam para o labirinto encantado
        labirinto1.mostrar();
    }
}
