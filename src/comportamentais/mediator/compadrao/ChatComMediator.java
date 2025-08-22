package comportamentais.mediator.compadrao;

import comportamentais.mediator.compadrao.classes.ChatRoom;
import comportamentais.mediator.compadrao.classes.Usuario;

/**
 * Sistema de chat COM o padrão Mediator
 * 
 * VANTAGENS:
 * - Desacoplamento entre usuários
 * - Centralização da lógica de comunicação
 * - Fácil adição de novas funcionalidades
 * - Reutilização do mediator
 */
public class ChatComMediator {
    
    public static void main(String[] args) {
        // VANTAGEM: Criação centralizada do mediator
        ChatRoom chatGeral = new ChatRoom("Chat Geral");
        
        // VANTAGEM: Usuários só precisam conhecer o mediator
        Usuario joao = new Usuario("João", chatGeral);
        Usuario maria = new Usuario("Maria", chatGeral);
        Usuario pedro = new Usuario("Pedro", chatGeral);
        
        System.out.println("=== CHAT COM MEDIATOR ===");
        
        // VANTAGEM: Mediator gerencia entrada/saída
        chatGeral.adicionarUsuario(joao);
        chatGeral.adicionarUsuario(maria);
        chatGeral.adicionarUsuario(pedro);
        
        chatGeral.listarUsuarios();
        
        System.out.println("\n--- COMUNICAÇÃO GERAL ---");
        joao.enviarMensagem("Olá pessoal!");
        maria.enviarMensagem("Oi João! Como vai?");
        pedro.enviarMensagem("Tudo bem! E vocês?");
        
        System.out.println("\n--- MENSAGENS PRIVADAS ---");
        maria.enviarMensagemPrivada(pedro, "Pedro, você viu o jogo ontem?");
        pedro.enviarMensagemPrivada(maria, "Vi sim! Foi incrível!");
        
        System.out.println("\n--- FUNCIONALIDADES ADICIONAIS ---");
        chatGeral.enviarAnuncio("Reunião às 14h na sala de conferências");
        
        System.out.println("\n--- DEMONSTRAÇÃO DE FLEXIBILIDADE ---");
        demonstrarFlexibilidade();
    }
    
    /**
     * Demonstra a flexibilidade do padrão
     * Múltiplos chats e migração de usuários
     */
    private static void demonstrarFlexibilidade() {
        // VANTAGEM: Múltiplos mediators podem coexistir
        ChatRoom chatTecnico = new ChatRoom("Chat Técnico");
        ChatRoom chatSocial = new ChatRoom("Chat Social");
        
        Usuario ana = new Usuario("Ana", chatTecnico);
        Usuario carlos = new Usuario("Carlos", chatTecnico);
        
        chatTecnico.adicionarUsuario(ana);
        chatTecnico.adicionarUsuario(carlos);
        
        System.out.println("\n=== MÚLTIPLOS CHATS ===");
        chatTecnico.listarUsuarios();
        
        ana.enviarMensagem("Alguém pode ajudar com o bug no sistema?");
        carlos.enviarMensagem("Claro! Qual é o problema?");
        
        // VANTAGEM: Fácil migração entre chats
        System.out.println("\n--- MIGRAÇÃO DE CHAT ---");
        chatTecnico.removerUsuario(ana);
        
        // Ana muda para chat social (precisaria criar novo Usuario com novo mediator)
        Usuario anaNoSocial = new Usuario("Ana", chatSocial);
        chatSocial.adicionarUsuario(anaNoSocial);
        
        anaNoSocial.enviarMensagem("Oi pessoal do chat social!");
        
        System.out.println("\n--- ESTATÍSTICAS ---");
        System.out.println("Usuários no Chat Técnico: " + chatTecnico.getNumeroUsuarios());
        System.out.println("Usuários no Chat Social: " + chatSocial.getNumeroUsuarios());
    }
    
    /*
     * VANTAGENS DO PADRÃO MEDIATOR:
     * 
     * 1. DESACOPLAMENTO: Usuários não conhecem uns aos outros
     * 
     * 2. CENTRALIZAÇÃO: Lógica de comunicação em um só lugar
     * 
     * 3. REUTILIZAÇÃO: Mediator pode ser usado por diferentes usuários
     * 
     * 4. EXTENSIBILIDADE: Novas funcionalidades sem modificar usuários
     * 
     * 5. CONTROLE: Mediator pode implementar regras de negócio
     * 
     * 6. FLEXIBILIDADE: Múltiplos mediators podem coexistir
     * 
     * 7. SINGLE RESPONSIBILITY: Cada classe tem uma responsabilidade
     */
}