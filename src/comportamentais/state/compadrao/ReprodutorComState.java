package comportamentais.state.compadrao;

import comportamentais.state.compadrao.classes.Reprodutor;

/**
 * Demonstração do padrão State
 * 
 * Esta classe mostra como o padrão State resolve os problemas
 * da implementação sem padrão, proporcionando:
 * 
 * VANTAGENS:
 * - Eliminação de condicionais complexas
 * - Cada estado encapsula seu próprio comportamento
 * - Fácil adição de novos estados
 * - Transições de estado controladas
 * - Código mais limpo e manutenível
 * - Princípio Aberto/Fechado respeitado
 * - Responsabilidade única para cada estado
 */
public class ReprodutorComState {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DO PADRÃO STATE ===");
        System.out.println("Reprodutor de Música com Estados Bem Definidos\n");
        
        // Criação do reprodutor (inicia no estado PARADO)
        Reprodutor reprodutor = new Reprodutor();
        
        // Demonstração 1: Operações básicas
        System.out.println("\n🎵 === OPERAÇÕES BÁSICAS ===");
        reprodutor.mostrarEstado();
        
        // Tentativa de pausar quando parado (comportamento específico do estado)
        reprodutor.pause();
        
        // Play - transição para TOCANDO
        reprodutor.setMusicaAtual("Bohemian Rhapsody - Queen");
        reprodutor.play();
        reprodutor.mostrarEstado();
        
        // Pause - transição para PAUSADO
        reprodutor.pause();
        reprodutor.mostrarEstado();
        
        // Play novamente - volta para TOCANDO
        reprodutor.play();
        
        // Stop - volta para PARADO
        reprodutor.stop();
        reprodutor.mostrarEstado();
        
        // Demonstração 2: Controle de volume em diferentes estados
        System.out.println("\n🔊 === CONTROLE DE VOLUME ===");
        reprodutor.alterarVolume(80); // No estado PARADO
        
        reprodutor.play();
        reprodutor.alterarVolume(60); // No estado TOCANDO
        
        reprodutor.pause();
        reprodutor.alterarVolume(40); // No estado PAUSADO
        
        // Demonstração 3: Próxima música em diferentes estados
        System.out.println("\n⏭️ === PRÓXIMA MÚSICA ===");
        reprodutor.stop();
        reprodutor.proximaMusica(); // No estado PARADO
        
        reprodutor.play();
        reprodutor.proximaMusica(); // No estado TOCANDO
        
        reprodutor.pause();
        reprodutor.proximaMusica(); // No estado PAUSADO
        
        // Demonstração 4: Sistema de bloqueio
        System.out.println("\n🔒 === SISTEMA DE BLOQUEIO ===");
        reprodutor.stop();
        reprodutor.bloquear(); // Bloqueia o reprodutor
        reprodutor.mostrarEstado();
        
        // Tentativas de operação quando bloqueado
        reprodutor.play();
        reprodutor.pause();
        reprodutor.alterarVolume(100);
        reprodutor.proximaMusica();
        
        // Desbloqueio
        reprodutor.desbloquear();
        reprodutor.mostrarEstado();
        
        // Demonstração 5: Funcionalidades avançadas
        System.out.println("\n⚙️ === FUNCIONALIDADES AVANÇADAS ===");
        
        // Verificação de ações disponíveis
        System.out.println("\nAções disponíveis no estado atual:");
        String[] acoes = reprodutor.getAcoesDisponiveis();
        for (String acao : acoes) {
            System.out.println("- " + acao);
        }
        
        // Verificação se pode executar ação
        System.out.println("\nVerificações de ações:");
        System.out.println("Pode fazer play? " + reprodutor.podeExecutarAcao("play"));
        System.out.println("Pode pausar? " + reprodutor.podeExecutarAcao("pause"));
        
        reprodutor.play();
        System.out.println("\nApós play:");
        System.out.println("Pode fazer play? " + reprodutor.podeExecutarAcao("play"));
        System.out.println("Pode pausar? " + reprodutor.podeExecutarAcao("pause"));
        
        // Demonstração 6: Configurações adicionais
        System.out.println("\n🔄 === CONFIGURAÇÕES ===");
        reprodutor.setRepetirPlaylist(true);
        reprodutor.setMusicaAtual("Imagine - John Lennon");
        reprodutor.mostrarEstado();
        
        // Demonstração 7: Sequência complexa de operações
        System.out.println("\n🎼 === SEQUÊNCIA COMPLEXA ===");
        reprodutor.play();
        reprodutor.alterarVolume(75);
        reprodutor.proximaMusica();
        reprodutor.pause();
        reprodutor.alterarVolume(50);
        reprodutor.play();
        reprodutor.bloquear();
        reprodutor.play(); // Tentativa inválida
        reprodutor.desbloquear();
        reprodutor.stop();
        
        // Estado final
        System.out.println("\n📊 === ESTADO FINAL ===");
        reprodutor.mostrarEstado();
        
        // Demonstração das vantagens do padrão
        System.out.println("\n✅ === VANTAGENS DEMONSTRADAS ===");
        System.out.println("1. ✓ Eliminação de condicionais complexas");
        System.out.println("2. ✓ Comportamento específico por estado");
        System.out.println("3. ✓ Transições controladas e seguras");
        System.out.println("4. ✓ Código limpo e manutenível");
        System.out.println("5. ✓ Fácil extensão com novos estados");
        System.out.println("6. ✓ Responsabilidade única por estado");
        System.out.println("7. ✓ Princípio Aberto/Fechado respeitado");
        
        System.out.println("\n🎯 Padrão State implementado com sucesso!");
        System.out.println("Compare com ReprodutorSemState para ver as diferenças.");
    }
    
    /**
     * Método auxiliar para demonstrar a flexibilidade do padrão
     */
    private static void demonstrarFlexibilidade() {
        System.out.println("\n🔧 === FLEXIBILIDADE DO PADRÃO ===");
        
        Reprodutor reprodutor = new Reprodutor();
        
        // Simulação de diferentes cenários
        String[] comandos = {"play", "pause", "play", "stop", "bloquear", "play", "desbloquear", "play"};
        
        for (String comando : comandos) {
            System.out.println("\nExecutando: " + comando.toUpperCase());
            
            switch (comando) {
                case "play":
                    reprodutor.play();
                    break;
                case "pause":
                    reprodutor.pause();
                    break;
                case "stop":
                    reprodutor.stop();
                    break;
                case "bloquear":
                    reprodutor.bloquear();
                    break;
                case "desbloquear":
                    reprodutor.desbloquear();
                    break;
            }
            
            System.out.println("Estado atual: " + reprodutor.getEstado().getNomeEstado());
        }
    }
}