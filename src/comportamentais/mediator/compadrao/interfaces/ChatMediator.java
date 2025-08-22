package comportamentais.mediator.compadrao.interfaces;

import comportamentais.mediator.compadrao.classes.Usuario;

/**
 * Interface Mediator do padrão Mediator
 * Define o contrato para comunicação entre usuários
 */
public interface ChatMediator {
    
    /**
     * Adiciona um usuário ao chat
     * @param usuario usuário a ser adicionado
     */
    void adicionarUsuario(Usuario usuario);
    
    /**
     * Remove um usuário do chat
     * @param usuario usuário a ser removido
     */
    void removerUsuario(Usuario usuario);
    
    /**
     * Envia mensagem para todos os usuários
     * @param mensagem conteúdo da mensagem
     * @param remetente usuário que enviou a mensagem
     */
    void enviarMensagem(String mensagem, Usuario remetente);
    
    /**
     * Envia mensagem privada entre dois usuários
     * @param mensagem conteúdo da mensagem
     * @param remetente usuário que enviou
     * @param destinatario usuário que receberá
     */
    void enviarMensagemPrivada(String mensagem, Usuario remetente, Usuario destinatario);
}