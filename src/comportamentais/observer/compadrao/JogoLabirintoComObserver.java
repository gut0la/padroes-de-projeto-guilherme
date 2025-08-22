package comportamentais.observer.compadrao;

import comportamentais.observer.compadrao.classes.Jogador;
import comportamentais.observer.compadrao.observers.MapaObserver;
import comportamentais.observer.compadrao.observers.PontuacaoObserver;
import comportamentais.observer.compadrao.observers.LogObserver;

/**
 * Demonstração do padrão Observer em ação
 * 
 * PADRÃO OBSERVER:
 * Define uma dependência um-para-muitos entre objetos, de modo que quando
 * um objeto muda de estado, todos os seus dependentes são notificados
 * e atualizados automaticamente.
 * 
 * COMPONENTES DO PADRÃO:
 * - Subject (Jogador): Objeto observável que mantém lista de observers
 * - Observer (Interface): Define contrato para objetos que querem ser notificados
 * - ConcreteObserver (MapaObserver, PontuacaoObserver, LogObserver): Implementações específicas
 * 
 * BENEFÍCIOS DEMONSTRADOS:
 * ✅ Baixo acoplamento: Jogador não conhece classes específicas de observers
 * ✅ Extensibilidade: Novos observers podem ser adicionados sem modificar código existente
 * ✅ Flexibilidade: Observers podem ser adicionados/removidos dinamicamente
 * ✅ Responsabilidade única: Cada observer tem uma responsabilidade específica
 * ✅ Princípio aberto/fechado: Aberto para extensão, fechado para modificação
 */
public class JogoLabirintoComObserver {
    
    public static void main(String[] args) {
        System.out.println("=== JOGO DE LABIRINTO COM PADRÃO OBSERVER ===");
        System.out.println("Demonstração: Baixo acoplamento e notificação automática\n");
        
        // FASE 1: Criação do Subject (objeto observável)
        System.out.println("🎮 FASE 1: Criando jogador (Subject)...");
        Jogador heroi = new Jogador("Herói Aventureiro");
        
        // FASE 2: Criação dos Observers (objetos interessados)
        System.out.println("\n👁️  FASE 2: Criando observers...");
        MapaObserver mapa = new MapaObserver();
        PontuacaoObserver pontuacao = new PontuacaoObserver();
        LogObserver log = new LogObserver();
        
        // FASE 3: Registro dos Observers no Subject
        System.out.println("\n🔗 FASE 3: Registrando observers no jogador...");
        heroi.adicionarObserver(mapa);
        heroi.adicionarObserver(pontuacao);
        heroi.adicionarObserver(log);
        
        System.out.println("Observers registrados: " + heroi.getNumeroObservers());
        
        // FASE 4: Demonstração do padrão em ação
        System.out.println("\n🚀 FASE 4: Padrão Observer em ação!");
        System.out.println("Cada ação do jogador notifica AUTOMATICAMENTE todos os observers:\n");
        
        // Movimentos - todos os observers são notificados automaticamente
        System.out.println("--- Explorando o labirinto ---");
        heroi.mover("Norte");
        heroi.mover("Leste");
        heroi.mover("Norte");
        heroi.mover("Leste");
        
        // Coleta de itens
        System.out.println("\n--- Coletando tesouros ---");
        heroi.coletarItem("Tesouro");
        heroi.mover("Sul");
        heroi.coletarItem("Gema");
        
        // Mais movimentos para demonstrar padrões
        System.out.println("\n--- Continuando exploração ---");
        heroi.mover("Oeste");
        heroi.mover("Norte");
        heroi.mover("Norte");
        heroi.coletarItem("Chave");
        
        // Simulando dano
        System.out.println("\n--- Enfrentando perigos ---");
        heroi.receberDano(30);
        heroi.mover("Sul");
        heroi.receberDano(25);
        
        // Mais coletas e movimentos
        System.out.println("\n--- Buscando mais tesouros ---");
        heroi.coletarItem("Poção");
        heroi.mover("Leste");
        heroi.mover("Norte");
        heroi.coletarItem("Diamante");
        
        // FASE 5: Demonstração de remoção dinâmica de observer
        System.out.println("\n🔄 FASE 5: Demonstrando remoção dinâmica de observer...");
        System.out.println("Removendo observer de pontuação...");
        heroi.removerObserver(pontuacao);
        
        System.out.println("Movimento após remoção (pontuação não será notificada):");
        heroi.mover("Sul");
        
        // Readicionando o observer
        System.out.println("\nReadicionando observer de pontuação...");
        heroi.adicionarObserver(pontuacao);
        heroi.mover("Oeste");
        
        // FASE 6: Demonstração de adição de novo observer em runtime
        System.out.println("\n➕ FASE 6: Adicionando novo observer em runtime...");
        AudioObserver audio = new AudioObserver();
        heroi.adicionarObserver(audio);
        
        System.out.println("Movimento com novo observer:");
        heroi.mover("Norte");
        heroi.coletarItem("Ouro");
        
        // FASE 7: Situação crítica
        System.out.println("\n⚠️  FASE 7: Situação crítica...");
        heroi.receberDano(40); // Vida crítica
        heroi.mover("Sul");
        
        // FASE 8: Final trágico (demonstra notificação de morte)
        System.out.println("\n💀 FASE 8: Final trágico...");
        heroi.receberDano(50); // Morte
        
        // FASE 9: Relatórios finais
        System.out.println("\n📊 FASE 9: Relatórios finais dos observers...");
        
        System.out.println("\n=== RELATÓRIO DO MAPA ===");
        mapa.mostrarMapa();
        
        System.out.println("\n=== RELATÓRIO DE PONTUAÇÃO ===");
        pontuacao.mostrarRelatorio();
        
        System.out.println("\n=== ESTATÍSTICAS DO LOG ===");
        log.mostrarEstatisticas();
        
        System.out.println("\n=== ÚLTIMOS 5 EVENTOS ===");
        log.mostrarUltimosEventos(5);
        
        // FASE 10: Demonstração dos benefícios do padrão
        System.out.println("\n🎯 FASE 10: Benefícios do padrão Observer demonstrados:");
        demonstrarBeneficios();
    }
    
    /**
     * Demonstra os benefícios alcançados com o padrão Observer
     */
    private static void demonstrarBeneficios() {
        System.out.println("\n✅ BENEFÍCIOS ALCANÇADOS:");
        System.out.println("1. 🔗 BAIXO ACOPLAMENTO:");
        System.out.println("   - Jogador não conhece classes específicas de observers");
        System.out.println("   - Observers não dependem uns dos outros");
        
        System.out.println("\n2. 🔧 EXTENSIBILIDADE:");
        System.out.println("   - AudioObserver foi adicionado sem modificar código existente");
        System.out.println("   - Novos observers podem ser criados facilmente");
        
        System.out.println("\n3. 🔄 FLEXIBILIDADE:");
        System.out.println("   - Observers podem ser adicionados/removidos em runtime");
        System.out.println("   - Sistema funciona com qualquer número de observers");
        
        System.out.println("\n4. 📋 RESPONSABILIDADE ÚNICA:");
        System.out.println("   - Cada observer tem uma responsabilidade específica");
        System.out.println("   - Jogador foca apenas em sua lógica principal");
        
        System.out.println("\n5. 🎯 NOTIFICAÇÃO AUTOMÁTICA:");
        System.out.println("   - Todos os observers são notificados automaticamente");
        System.out.println("   - Não há risco de esquecer de notificar algum sistema");
        
        System.out.println("\n6. 🔒 PRINCÍPIO ABERTO/FECHADO:");
        System.out.println("   - Sistema aberto para extensão (novos observers)");
        System.out.println("   - Fechado para modificação (código existente não muda)");
    }
}

/**
 * Observer adicional criado em runtime para demonstrar extensibilidade
 * 
 * DEMONSTRAÇÃO: Este observer foi criado para mostrar como é fácil
 * adicionar novos observers sem modificar o código existente.
 */
class AudioObserver implements comportamentais.observer.compadrao.interfaces.Observer {
    
    @Override
    public void notificar(String evento, Object dados) {
        switch (evento) {
            case "movimento":
                System.out.println("  [ÁUDIO] 🔊 Som de passos: *tap tap*");
                break;
            case "coleta":
                System.out.println("  [ÁUDIO] 🔊 Som de coleta: *bling!*");
                break;
            case "dano":
                System.out.println("  [ÁUDIO] 🔊 Som de dor: *auch!*");
                break;
            case "morte":
                System.out.println("  [ÁUDIO] 🔊 Som dramático: *música triste*");
                break;
        }
    }
}