package comportamentais.mediator.compadrao.classes;

import comportamentais.mediator.compadrao.interfaces.ChatMediator;
import java.util.ArrayList;
import java.util.List;

/**
 * Mediator concreto que gerencia a comunicação entre usuários
 * VANTAGEM: Centraliza toda a lógica de comunicação
 */
public class ChatRoom implements ChatMediator {
    
    private List<Usuario> usuarios;
    private String nome;
    
    public ChatRoom(String nome) {
        this.nome = nome;
        this.usuarios = new ArrayList<>();
    }
    
    @Override
    public void adicionarUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            System.out.println("[" + nome + "] " + usuario.getNome() + " entrou no chat");
        }
    }
    
    @Override
    public void removerUsuario(Usuario usuario) {
        if (usuarios.remove(usuario)) {
            System.out.println("[" + nome + "] " + usuario.getNome() + " saiu do chat");
        }
    }
    
    @Override
    public void enviarMensagem(String mensagem, Usuario remetente) {
        // VANTAGEM: Lógica de broadcast centralizada no mediator
        for (Usuario usuario : usuarios) {
            if (!usuario.equals(remetente)) {
                usuario.receberMensagem(mensagem, remetente);
            }
        }
    }
    
    @Override
    public void enviarMensagemPrivada(String mensagem, Usuario remetente, Usuario destinatario) {
        // VANTAGEM: Validação centralizada
        if (usuarios.contains(remetente) && usuarios.contains(destinatario)) {
            destinatario.receberMensagem(mensagem, remetente);
        } else {
            System.out.println("ERRO: Um dos usuários não está no chat");
        }
    }
    
    /**
     * VANTAGEM: Funcionalidades adicionais podem ser facilmente adicionadas
     */
    public void listarUsuarios() {
        System.out.println("\n[" + nome + "] Usuários online:");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNome());
        }
    }
    
    /**
     * VANTAGEM: Novas funcionalidades sem modificar Usuario
     */
    public void enviarAnuncio(String anuncio) {
        System.out.println("\n[ANÚNCIO do " + nome + "] " + anuncio);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome() + " recebeu anúncio: " + anuncio);
        }
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getNumeroUsuarios() {
        return usuarios.size();
    }
}