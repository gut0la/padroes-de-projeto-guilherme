package comportamentais.mediator.sempadrao;

import java.util.ArrayList;
import java.util.List;

/**
 * Sistema de chat SEM o padrão Mediator
 * 
 * PROBLEMAS:
 * - Usuários conhecem uns aos outros diretamente
 * - Acoplamento forte entre participantes
 * - Difícil adicionar novos tipos de interação
 * - Lógica de comunicação espalhada
 */
public class ChatSemMediator {
    
    public static void main(String[] args) {
        // PROBLEMA: Criação manual de todas as referências
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario pedro = new Usuario("Pedro");
        
        // PROBLEMA: Cada usuário precisa conhecer todos os outros
        joao.adicionarContato(maria);
        joao.adicionarContato(pedro);
        
        maria.adicionarContato(joao);
        maria.adicionarContato(pedro);
        
        pedro.adicionarContato(joao);
        pedro.adicionarContato(maria);
        
        System.out.println("=== CHAT SEM MEDIATOR ===");
        
        // Testando comunicação
        joao.enviarMensagem("Olá pessoal!");
        maria.enviarMensagemPrivada(pedro, "Oi Pedro, como vai?");
        pedro.enviarMensagem("Tudo bem! E vocês?");
    }
    
    /**
     * Classe Usuario com MUITAS responsabilidades
     * PROBLEMA: Violação do Single Responsibility Principle
     */
    static class Usuario {
        private String nome;
        private List<Usuario> contatos; // PROBLEMA: Conhece outros usuários diretamente
        
        public Usuario(String nome) {
            this.nome = nome;
            this.contatos = new ArrayList<>();
        }
        
        // PROBLEMA: Usuário gerencia sua própria lista de contatos
        public void adicionarContato(Usuario usuario) {
            if (!contatos.contains(usuario)) {
                contatos.add(usuario);
            }
        }
        
        // PROBLEMA: Lógica de broadcast no próprio usuário
        public void enviarMensagem(String mensagem) {
            System.out.println(nome + " enviou: " + mensagem);
            
            // PROBLEMA: Usuário precisa iterar por todos os contatos
            for (Usuario contato : contatos) {
                contato.receberMensagem(this, mensagem);
            }
        }
        
        // PROBLEMA: Lógica de mensagem privada também no usuário
        public void enviarMensagemPrivada(Usuario destinatario, String mensagem) {
            System.out.println(nome + " enviou privado para " + destinatario.nome + ": " + mensagem);
            destinatario.receberMensagem(this, mensagem);
        }
        
        // PROBLEMA: Método que recebe mensagens de outros
        public void receberMensagem(Usuario remetente, String mensagem) {
            if (!remetente.equals(this)) { // Evita receber próprias mensagens
                System.out.println(nome + " recebeu de " + remetente.nome + ": " + mensagem);
            }
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
    
    /*
     * PROBLEMAS DESTA ABORDAGEM:
     * 
     * 1. ACOPLAMENTO FORTE: Cada usuário conhece todos os outros
     * 
     * 2. RESPONSABILIDADES MISTURADAS: Usuario faz comunicação 
     *    e gerenciamento de contatos
     * 
     * 3. DIFICULDADE DE EXTENSÃO: Adicionar novos tipos de 
     *    mensagem requer modificar Usuario
     * 
     * 4. COMPLEXIDADE CRESCENTE: Com N usuários, temos 
     *    N*(N-1) relacionamentos
     * 
     * 5. DUPLICAÇÃO DE LÓGICA: Lógica de envio repetida 
     *    em diferentes métodos
     * 
     * 6. DIFICULDADE DE TESTE: Difícil testar comunicação 
     *    sem criar todos os usuários
     * 
     * 7. VIOLAÇÃO DRY: Código similar em vários lugares
     */
}