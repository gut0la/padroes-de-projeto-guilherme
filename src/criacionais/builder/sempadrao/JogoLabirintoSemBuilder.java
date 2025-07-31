package criacionais.builder.sempadrao;

/**
 * Exemplo SEM o padrão Builder
 * 
 * Demonstra os problemas que o Builder resolve:
 * - Código de construção misturado com lógica de negócio
 * - Dificuldade para reutilizar algoritmos de construção
 * - Código repetitivo para diferentes variações
 * - Violação do princípio da Responsabilidade Única
 */
public class JogoLabirintoSemBuilder {
    
    /**
     * Método que mistura lógica de decisão com construção
     * Problema: algoritmo de construção não é reutilizável
     */
    public static void criarLabirinto(String tipo) {
        // Código condicional - viola Aberto/Fechado
        if (tipo.equals("classico")) {
            System.out.println("Criando labirinto clássico:");
            // Passos de construção hardcoded
            System.out.println("Adicionando sala clássica 1");
            System.out.println("Adicionando sala clássica 2");
            System.out.println("Adicionando porta clássica entre salas");
        } else if (tipo.equals("encantado")) {
            System.out.println("Criando labirinto encantado:");
            // Código repetitivo - mesma estrutura, conteúdo diferente
            System.out.println("Adicionando sala encantada com itens mágicos 1");
            System.out.println("Adicionando sala encantada com itens mágicos 2");
            System.out.println("Adicionando porta encantada que precisa de feitiço");
        } else {
            System.out.println("Tipo de labirinto não reconhecido!");
        }
        // Problemas evidentes:
        // 1. Adicionar novo tipo requer modificar este método
        // 2. Não há separação entre algoritmo e implementação
        // 3. Difícil de testar e manter
    }

    public static void main(String[] args) {
        criarLabirinto("classico");
        System.out.println();
        criarLabirinto("encantado");
    }
}
