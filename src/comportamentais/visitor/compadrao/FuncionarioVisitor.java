package comportamentais.visitor.compadrao;

/**
 * Interface Visitor - Define as operações que podem ser realizadas
 * nos diferentes tipos de funcionários.
 * 
 * Vantagens:
 * - Separação clara entre dados e operações
 * - Facilita adição de novas operações sem modificar classes existentes
 * - Centraliza lógica relacionada em uma classe visitor
 * - Permite polimorfismo duplo (double dispatch)
 * - Facilita reutilização de operações
 */
public interface FuncionarioVisitor {
    
    /**
     * Visita um desenvolvedor para realizar uma operação específica
     * @param desenvolvedor O desenvolvedor a ser visitado
     */
    void visitDesenvolvedor(Desenvolvedor desenvolvedor);
    
    /**
     * Visita um gerente para realizar uma operação específica
     * @param gerente O gerente a ser visitado
     */
    void visitGerente(Gerente gerente);
    
    /**
     * Visita um estagiário para realizar uma operação específica
     * @param estagiario O estagiário a ser visitado
     */
    void visitEstagiario(Estagiario estagiario);
}

/**
 * Interface para elementos que podem ser visitados
 * 
 * Esta interface garante que todos os funcionários
 * possam aceitar visitors e delegar operações para eles.
 */
interface ElementoVisitavel {
    
    /**
     * Aceita um visitor e delega a operação para ele
     * @param visitor O visitor que realizará a operação
     */
    void aceitar(FuncionarioVisitor visitor);
}