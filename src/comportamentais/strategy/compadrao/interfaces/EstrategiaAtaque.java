package comportamentais.strategy.compadrao.interfaces;

/**
 * Interface Strategy para algoritmos de ataque
 * 
 * PADRÃO STRATEGY:
 * Esta interface define o contrato comum para todas as estratégias de ataque.
 * Cada implementação concreta representa um algoritmo diferente de ataque.
 * 
 * BENEFÍCIOS:
 * - Encapsula algoritmos em classes separadas
 * - Permite trocar algoritmos em tempo de execução
 * - Facilita adição de novos algoritmos sem modificar código existente
 * - Elimina estruturas condicionais complexas
 */
public interface EstrategiaAtaque {
    
    /**
     * Executa o algoritmo de ataque específico
     * 
     * @param atacante Nome do personagem que está atacando
     * @param ataqueBase Valor base de ataque do personagem
     * @param alvo Objeto que representa o alvo do ataque
     * @return Quantidade de dano causado
     * 
     * DESIGN:
     * - Método comum para todos os algoritmos de ataque
     * - Recebe parâmetros necessários para calcular o dano
     * - Retorna resultado do cálculo para uso posterior
     * - Cada implementação pode ter lógica completamente diferente
     */
    int executarAtaque(String atacante, int ataqueBase, Object alvo);
    
    /**
     * Retorna o nome/tipo desta estratégia de ataque
     * 
     * @return Nome descritivo da estratégia
     * 
     * UTILIDADE:
     * - Útil para logs e debugging
     * - Permite identificar qual estratégia está sendo usada
     * - Facilita testes e validações
     */
    String getNomeEstrategia();
    
    /**
     * Retorna descrição de como o ataque é executado
     * 
     * @return Descrição textual da ação de ataque
     * 
     * PROPÓSITO:
     * - Fornece feedback visual para o usuário
     * - Torna o jogo mais imersivo
     * - Permite personalização da apresentação
     */
    String getDescricaoAtaque();
}

/**
 * Interface Strategy para algoritmos de movimento
 * 
 * PADRÃO STRATEGY:
 * Define o contrato para diferentes estratégias de movimento,
 * permitindo que personagens se movam de formas distintas.
 */
interface EstrategiaMovimento {
    
    /**
     * Executa o algoritmo de movimento específico
     * 
     * @param personagem Nome do personagem
     * @param terreno Tipo de terreno onde está se movendo
     * @param velocidadeBase Velocidade base do personagem
     * @return Velocidade final após aplicar a estratégia
     */
    int executarMovimento(String personagem, String terreno, int velocidadeBase);
    
    /**
     * Retorna o nome desta estratégia de movimento
     */
    String getNomeEstrategia();
    
    /**
     * Retorna descrição de como o movimento é executado
     */
    String getDescricaoMovimento();
    
    /**
     * Verifica se esta estratégia é eficaz no terreno especificado
     * 
     * @param terreno Tipo de terreno
     * @return true se a estratégia é eficaz neste terreno
     */
    boolean isEficazEm(String terreno);
}

/**
 * Interface Strategy para algoritmos de defesa
 * 
 * PADRÃO STRATEGY:
 * Define o contrato para diferentes estratégias de defesa,
 * cada uma com sua própria forma de reduzir dano.
 */
interface EstrategiaDefesa {
    
    /**
     * Executa o algoritmo de defesa específico
     * 
     * @param defensor Nome do personagem que está se defendendo
     * @param danoRecebido Quantidade de dano que seria recebido
     * @param defesaBase Valor base de defesa do personagem
     * @return Dano final após aplicar a defesa
     */
    int executarDefesa(String defensor, int danoRecebido, int defesaBase);
    
    /**
     * Retorna o nome desta estratégia de defesa
     */
    String getNomeEstrategia();
    
    /**
     * Retorna descrição de como a defesa é executada
     */
    String getDescricaoDefesa();
    
    /**
     * Retorna a eficácia desta defesa (0.0 a 1.0)
     * 
     * @return Valor entre 0.0 (ineficaz) e 1.0 (muito eficaz)
     */
    double getEficacia();
}