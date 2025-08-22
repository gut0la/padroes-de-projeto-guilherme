package comportamentais.mediator.compadrao.classes;

import comportamentais.mediator.compadrao.interfaces.ChatMediator;

/**
 * Classe Usuario que participa da comunicação através do Mediator
 * VANTAGEM: Não conhece outros usuários diretamente
 */
public class Usuario {
    
    private String nome;
    private ChatMediator mediator;
    
    public Usuario(String nome, ChatMediator mediator) {
        this.nome = nome;
        this.mediator = mediator;
    }
    
    /**
     * VANTAGEM: Usuário só precisa conhecer o mediator
     * Não precisa gerenciar lista de contatos
     */
    public void enviarMensagem(String mensagem) {
        System.out.println(nome + " enviou: " + mensagem);
        mediator.enviarMensagem(mensagem, this);
    }
    
    /**
     * VANTAGEM: Lógica de mensagem privada delegada ao mediator
     */
    public void enviarMensagemPrivada(Usuario destinatario, String mensagem) {
        System.out.println(nome + " enviou privado para " + destinatario.getNome() + ": " + mensagem);
        mediator.enviarMensagemPrivada(mensagem, this, destinatario);
    }
    
    /**
     * Método para receber mensagens do mediator
     * VANTAGEM: Usuário não precisa filtrar mensagens próprias
     */
    public void receberMensagem(String mensagem, Usuario remetente) {
        System.out.println(nome + " recebeu de " + remetente.getNome() + ": " + mensagem);
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return nome.equals(usuario.nome);
    }
    
    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}