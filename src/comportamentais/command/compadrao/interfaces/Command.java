package comportamentais.command.compadrao.interfaces;

/**
 * Interface Command do padrão Command
 * 
 * PADRÃO COMMAND - COMMAND INTERFACE:
 * Esta interface define o contrato comum para todos os comandos concretos.
 * Ela encapsula uma requisição como um objeto, permitindo parametrizar
 * clientes com diferentes requisições, enfileirar operações e suportar
 * operações de desfazer (undo).
 * 
 * RESPONSABILIDADES:
 * - Definir método comum para execução de comandos
 * - Definir método comum para desfazer comandos
 * - Permitir que comandos sejam tratados uniformemente
 * - Facilitar implementação de funcionalidades avançadas (macro, queue, etc.)
 * 
 * BENEFÍCIOS:
 * - Desacopla objeto que invoca operação do objeto que a executa
 * - Permite parametrizar objetos com diferentes requisições
 * - Permite enfileirar, registrar e desfazer operações
 * - Suporta logging, transações e recuperação de falhas
 * - Facilita implementação de macros e operações compostas
 */
public interface Command {
    
    /**
     * Executa o comando
     * 
     * RESPONSABILIDADES:
     * - Executar a operação encapsulada
     * - Salvar estado necessário para undo (se aplicável)
     * - Retornar resultado da execução
     * - Tratar erros de forma apropriada
     * 
     * @return true se comando foi executado com sucesso, false caso contrário
     */
    boolean executar();
    
    /**
     * Desfaz o comando (undo)
     * 
     * RESPONSABILIDADES:
     * - Reverter efeitos da execução
     * - Restaurar estado anterior
     * - Garantir que o sistema volte ao estado pré-execução
     * 
     * IMPORTANTE:
     * - Nem todos os comandos podem ser desfeitos
     * - Comandos que não podem ser desfeitos devem retornar false
     * - Estado deve ser salvo durante execução para permitir undo
     * 
     * @return true se comando foi desfeito com sucesso, false caso contrário
     */
    boolean desfazer();
    
    /**
     * Verifica se o comando pode ser desfeito
     * 
     * @return true se o comando suporta undo, false caso contrário
     */
    boolean podeSerDesfeito();
    
    /**
     * Retorna descrição do comando
     * 
     * UTILIDADE:
     * - Logging de operações
     * - Histórico de comandos
     * - Interface de usuário
     * - Debugging
     * 
     * @return descrição legível do comando
     */
    String getDescricao();
    
    /**
     * Retorna timestamp de quando o comando foi criado
     * 
     * UTILIDADE:
     * - Auditoria de operações
     * - Ordenação de comandos
     * - Timeout de comandos
     * 
     * @return timestamp de criação em milissegundos
     */
    long getTimestamp();
}

/**
 * Interface para comandos que podem ser agrupados (Composite Command)
 * 
 * PADRÃO COMPOSITE + COMMAND:
 * Permite criar comandos compostos que executam múltiplos comandos
 * como se fossem um único comando.
 */
interface CompositeCommand extends Command {
    
    /**
     * Adiciona um comando ao grupo
     * 
     * @param comando comando a ser adicionado
     */
    void adicionarComando(Command comando);
    
    /**
     * Remove um comando do grupo
     * 
     * @param comando comando a ser removido
     */
    void removerComando(Command comando);
    
    /**
     * Retorna lista de comandos no grupo
     * 
     * @return lista de comandos
     */
    java.util.List<Command> getComandos();
}

/**
 * Interface para comandos que podem ser executados de forma assíncrona
 */
interface AsyncCommand extends Command {
    
    /**
     * Executa o comando de forma assíncrona
     * 
     * @return Future representando a execução assíncrona
     */
    java.util.concurrent.Future<Boolean> executarAsync();
    
    /**
     * Cancela a execução assíncrona
     * 
     * @return true se cancelamento foi bem-sucedido
     */
    boolean cancelar();
    
    /**
     * Verifica se o comando está sendo executado
     * 
     * @return true se comando está em execução
     */
    boolean estaExecutando();
}

/**
 * Interface para comandos que têm prioridade
 */
interface PriorityCommand extends Command, Comparable<PriorityCommand> {
    
    /**
     * Retorna prioridade do comando
     * 
     * @return prioridade (maior valor = maior prioridade)
     */
    int getPrioridade();
    
    /**
     * Define prioridade do comando
     * 
     * @param prioridade nova prioridade
     */
    void setPrioridade(int prioridade);
    
    /**
     * Implementação padrão de comparação por prioridade
     */
    @Override
    default int compareTo(PriorityCommand other) {
        return Integer.compare(other.getPrioridade(), this.getPrioridade());
    }
}