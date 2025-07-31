package criacionais.singleton.classes;

/**
 * Implementação do padrão Singleton para a classe Labirinto
 * 
 * O padrão Singleton garante que uma classe tenha apenas uma instância
 * e fornece um ponto de acesso global a essa instância.
 * 
 * Neste exemplo, garantimos que apenas um labirinto esteja ativo por vez,
 * evitando confusão no jogo.
 */
public class Labirinto {
    
    // Variável estática que armazena a única instância da classe
    private static Labirinto instanciaUnica;
    
    // Atributo da instância
    private String tipo;

    /**
     * Construtor privado - impede a criação de instâncias externas
     * 
     * Esta é uma característica fundamental do padrão Singleton:
     * o construtor deve ser privado para controlar a criação de instâncias.
     * 
     * @param tipo - Tipo do labirinto (clássico, encantado, etc.)
     */
    private Labirinto(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método estático que fornece acesso à única instância da classe
     * 
     * Este é o ponto de acesso global ao Singleton.
     * Se a instância não existe, cria uma nova.
     * Se já existe, retorna a instância existente (ignorando o parâmetro tipo).
     * 
     * @param tipo - Tipo do labirinto (usado apenas na primeira criação)
     * @return A única instância de Labirinto
     */
    public static Labirinto getInstancia(String tipo) {
        // Lazy initialization: cria a instância apenas quando necessário
        if (instanciaUnica == null) {
            instanciaUnica = new Labirinto(tipo);
        }
        return instanciaUnica;
    }

    /**
     * Método para exibir informações do labirinto ativo
     */
    public void mostrar() {
        System.out.println("Labirinto " + tipo + " ativo");
    }

    /**
     * Método para reiniciar o labirinto com um novo tipo
     * 
     * Este método quebra um pouco o padrão Singleton tradicional,
     * permitindo "recriar" a instância. Em uma implementação mais rigorosa,
     * seria melhor ter um método setTipo() para alterar o tipo.
     * 
     * @param tipo - Novo tipo do labirinto
     */
    public void reiniciar(String tipo) {
        instanciaUnica = new Labirinto(tipo);
    }
}

