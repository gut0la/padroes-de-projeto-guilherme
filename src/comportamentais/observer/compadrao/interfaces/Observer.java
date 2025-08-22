package comportamentais.observer.compadrao.interfaces;

/**
 * Interface Observer - Define o contrato para objetos que querem ser notificados
 * 
 * PADRÃO OBSERVER:
 * Esta interface representa o "Observer" no padrão Observer.
 * Todos os objetos que querem receber notificações sobre mudanças
 * no estado do Subject devem implementar esta interface.
 * 
 * BENEFÍCIOS:
 * - Desacoplamento: Observers não precisam conhecer detalhes do Subject
 * - Flexibilidade: Novos observers podem ser adicionados sem modificar código existente
 * - Reutilização: A mesma interface pode ser usada para diferentes tipos de notificação
 */
public interface Observer {
    
    /**
     * Método chamado quando o Subject notifica sobre uma mudança
     * 
     * @param evento Tipo do evento que ocorreu (ex: "movimento", "colisao", etc.)
     * @param dados Dados relacionados ao evento (pode ser qualquer objeto)
     * 
     * DESIGN:
     * - Usamos Object para dados para máxima flexibilidade
     * - O evento como String permite diferentes tipos de notificação
     * - Cada Observer decide como interpretar os dados recebidos
     */
    void notificar(String evento, Object dados);
}

/**
 * Interface Subject - Define o contrato para objetos observáveis
 * 
 * PADRÃO OBSERVER:
 * Esta interface representa o "Subject" (ou "Observable") no padrão Observer.
 * Objetos que implementam esta interface podem ter observers registrados
 * e são responsáveis por notificá-los quando seu estado muda.
 * 
 * RESPONSABILIDADES:
 * - Manter lista de observers
 * - Permitir adicionar/remover observers
 * - Notificar todos os observers quando necessário
 */
interface Subject {
    
    /**
     * Adiciona um observer à lista de observadores
     * 
     * @param observer O observer a ser adicionado
     */
    void adicionarObserver(Observer observer);
    
    /**
     * Remove um observer da lista de observadores
     * 
     * @param observer O observer a ser removido
     */
    void removerObserver(Observer observer);
    
    /**
     * Notifica todos os observers registrados sobre um evento
     * 
     * @param evento Tipo do evento
     * @param dados Dados do evento
     */
    void notificarObservers(String evento, Object dados);
}