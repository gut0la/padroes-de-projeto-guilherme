package comportamentais.chainofresponsibility.compadrao;

import comportamentais.chainofresponsibility.compadrao.interfaces.Handler;
import comportamentais.chainofresponsibility.compadrao.handlers.*;

/**
 * Sistema de aprovação de despesas COM o padrão Chain of Responsibility
 * 
 * VANTAGENS:
 * - Desacoplamento entre remetente e receptor
 * - Flexibilidade para adicionar/remover handlers
 * - Cada handler tem uma única responsabilidade
 * - Fácil de testar e manter
 */
public class SistemaAprovacaoComChain {
    
    private Handler primeiroHandler;
    
    public static void main(String[] args) {
        SistemaAprovacaoComChain sistema = new SistemaAprovacaoComChain();
        sistema.configurarCadeia();
        
        System.out.println("=== SISTEMA DE APROVAÇÃO COM CHAIN OF RESPONSIBILITY ===");
        
        // Testando diferentes valores de despesas
        sistema.processarDespesa("Canetas", 50.0);
        sistema.processarDespesa("Notebook", 2500.0);
        sistema.processarDespesa("Reforma escritório", 15000.0);
        sistema.processarDespesa("Aquisição empresa", 500000.0);
        
        System.out.println("\n=== DEMONSTRAÇÃO DE FLEXIBILIDADE ===");
        sistema.demonstrarFlexibilidade();
    }
    
    /**
     * Configura a cadeia de responsabilidade
     * VANTAGEM: Fácil de modificar a ordem ou adicionar novos handlers
     */
    private void configurarCadeia() {
        Handler supervisor = new SupervisorHandler();
        Handler gerente = new GerenteHandler();
        Handler diretor = new DiretorHandler();
        
        // Configurando a cadeia: Supervisor -> Gerente -> Diretor
        supervisor.setProximo(gerente);
        gerente.setProximo(diretor);
        
        this.primeiroHandler = supervisor;
    }
    
    /**
     * Processa uma despesa através da cadeia
     * @param item descrição do item
     * @param valor valor da despesa
     */
    public void processarDespesa(String item, double valor) {
        System.out.println("\n=== PROCESSANDO DESPESA ===");
        System.out.println("Item: " + item);
        System.out.println("Valor: R$ " + valor);
        
        primeiroHandler.processar(item, valor);
    }
    
    /**
     * Demonstra a flexibilidade do padrão
     * Mostra como é fácil reconfigurar a cadeia
     */
    private void demonstrarFlexibilidade() {
        System.out.println("\nReconfigurando cadeia: Gerente -> Diretor (sem Supervisor)");
        
        Handler gerente = new GerenteHandler();
        Handler diretor = new DiretorHandler();
        
        gerente.setProximo(diretor);
        this.primeiroHandler = gerente;
        
        // Testando com a nova configuração
        processarDespesa("Material escritório", 50.0);
    }
    
    /*
     * VANTAGENS DO PADRÃO CHAIN OF RESPONSIBILITY:
     * 
     * 1. DESACOPLAMENTO: O cliente não precisa conhecer 
     *    qual handler processará a solicitação
     * 
     * 2. FLEXIBILIDADE: Fácil adicionar, remover ou 
     *    reordenar handlers na cadeia
     * 
     * 3. SINGLE RESPONSIBILITY: Cada handler tem 
     *    uma única responsabilidade
     * 
     * 4. EXTENSIBILIDADE: Novos tipos de aprovação 
     *    podem ser adicionados sem modificar código existente
     * 
     * 5. TESTABILIDADE: Cada handler pode ser 
     *    testado independentemente
     */
}