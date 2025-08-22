package comportamentais.state.compadrao.interfaces;

import comportamentais.state.compadrao.classes.Reprodutor;

/**
 * Interface EstadoReprodutor - State no padrão State
 * 
 * Define o contrato que todos os estados concretos devem implementar.
 * Cada método representa uma ação que pode ser executada no reprodutor,
 * e cada estado implementa o comportamento específico para essa ação.
 * 
 * CARACTERÍSTICAS:
 * - Encapsula comportamento específico de cada estado
 * - Recebe referência do contexto (Reprodutor)
 * - Permite transições de estado
 * - Elimina condicionais complexas
 */
public interface EstadoReprodutor {
    
    /**
     * Executa ação de play no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     */
    void play(Reprodutor reprodutor);
    
    /**
     * Executa ação de pause no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     */
    void pause(Reprodutor reprodutor);
    
    /**
     * Executa ação de stop no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     */
    void stop(Reprodutor reprodutor);
    
    /**
     * Executa ação de próxima música no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     */
    void proximaMusica(Reprodutor reprodutor);
    
    /**
     * Executa ação de alterar volume no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     * @param novoVolume Novo volume a ser definido
     */
    void alterarVolume(Reprodutor reprodutor, int novoVolume);
    
    /**
     * Executa ação de bloquear no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     */
    void bloquear(Reprodutor reprodutor);
    
    /**
     * Executa ação de desbloquear no estado atual
     * 
     * @param reprodutor Contexto do reprodutor
     */
    void desbloquear(Reprodutor reprodutor);
    
    /**
     * Retorna o nome do estado para exibição
     * 
     * @return Nome do estado
     */
    String getNomeEstado();
    
    /**
     * Retorna descrição do estado atual
     * 
     * @return Descrição do estado
     */
    String getDescricaoEstado();
}