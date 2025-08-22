package comportamentais.strategy.compadrao;

import comportamentais.strategy.compadrao.classes.Personagem;
import comportamentais.strategy.compadrao.strategies.*;

/**
 * Demonstração do padrão Strategy no contexto de um jogo de labirinto
 * 
 * PADRÃO STRATEGY (ESTRATÉGIA):
 * Define uma família de algoritmos, encapsula cada um deles e os torna
 * intercambiáveis. O Strategy permite que o algoritmo varie independentemente
 * dos clientes que o utilizam.
 * 
 * COMPONENTES DO PADRÃO:
 * 
 * 1. STRATEGY (EstrategiaAtaque, EstrategiaMovimento, EstrategiaDefesa):
 *    - Interface comum que define o contrato para todos os algoritmos
 *    - Declara métodos que o Context usa para executar estratégias
 * 
 * 2. CONCRETE STRATEGY (AtaqueCorpoACorpo, MovimentoAgil, DefesaEscudo, etc.):
 *    - Implementa algoritmos específicos usando a interface Strategy
 *    - Cada classe encapsula um comportamento/algoritmo diferente
 * 
 * 3. CONTEXT (Personagem):
 *    - Mantém referência para um objeto Strategy
 *    - Delega trabalho para o objeto Strategy ao invés de implementar múltiplas versões
 *    - Permite que o cliente configure qual algoritmo usar
 * 
 * BENEFÍCIOS:
 * ✅ Elimina estruturas condicionais complexas (if/switch gigantes)
 * ✅ Facilita adição de novos algoritmos sem modificar código existente
 * ✅ Permite trocar algoritmos em tempo de execução
 * ✅ Promove reutilização - estratégias podem ser usadas por diferentes contextos
 * ✅ Segue princípios SOLID (especialmente Aberto/Fechado e Responsabilidade Única)
 * ✅ Facilita testes unitários - cada estratégia pode ser testada isoladamente
 * 
 * QUANDO USAR:
 * - Quando você tem múltiplas maneiras de realizar uma tarefa
 * - Quando quer evitar condicionais complexas
 * - Quando algoritmos devem ser intercambiáveis
 * - Quando quer isolar detalhes de implementação de algoritmos
 */
public class JogoLabirintoComStrategy {
    
    public static void main(String[] args) {
        System.out.println("🎮 === JOGO DE LABIRINTO COM PADRÃO STRATEGY ===");
        System.out.println("\n📚 DEMONSTRAÇÃO: Como o padrão Strategy elimina condicionais");
        System.out.println("e permite trocar algoritmos dinamicamente.\n");
        
        // ==================== CRIAÇÃO DOS PERSONAGENS ====================
        
        System.out.println("👥 === CRIANDO PERSONAGENS ===");
        
        // Guerreiro - focado em combate corpo a corpo e defesa
        Personagem guerreiro = new Personagem(
            "Thorin", "Guerreiro", 120, 25, 20, 10
        );
        
        // Mago - focado em ataques mágicos e mobilidade
        Personagem mago = new Personagem(
            "Gandalf", "Mago", 80, 30, 10, 15
        );
        
        // Ladino - focado em agilidade e ataques furtivos
        Personagem ladino = new Personagem(
            "Legolas", "Ladino", 90, 22, 12, 25
        );
        
        System.out.println("✅ Personagens criados com sucesso!\n");
        
        // ==================== CONFIGURAÇÃO INICIAL DE ESTRATÉGIAS ====================
        
        System.out.println("⚙️  === CONFIGURANDO ESTRATÉGIAS INICIAIS ===");
        
        // Guerreiro: Estratégias típicas de tanque
        guerreiro.setEstrategiaAtaque(new AtaqueCorpoACorpo());
        guerreiro.setEstrategiaMovimento(new MovimentoPesado());
        guerreiro.setEstrategiaDefesa(new DefesaEscudo());
        
        // Mago: Estratégias típicas de caster
        mago.setEstrategiaAtaque(new AtaqueMagico());
        mago.setEstrategiaMovimento(new MovimentoMagico());
        mago.setEstrategiaDefesa(new DefesaMagica());
        
        // Ladino: Estratégias típicas de DPS ágil
        ladino.setEstrategiaAtaque(new AtaqueFurtivo());
        ladino.setEstrategiaMovimento(new MovimentoAgil());
        ladino.setEstrategiaDefesa(new DefesaEsquiva());
        
        System.out.println("\n📊 Status inicial dos personagens:");
        guerreiro.exibirStatus();
        mago.exibirStatus();
        ladino.exibirStatus();
        
        // ==================== SIMULAÇÃO DE COMBATE ====================
        
        System.out.println("\n\n⚔️  === SIMULAÇÃO DE COMBATE ===");
        System.out.println("Demonstrando como as estratégias funcionam em ação:\n");
        
        // Rodada 1: Combate inicial
        System.out.println("🥊 RODADA 1: Combate inicial");
        guerreiro.atacar(mago);
        mago.atacar(ladino);
        ladino.atacar(guerreiro);
        
        // ==================== DEMONSTRAÇÃO DE MOVIMENTO ====================
        
        System.out.println("\n\n🗺️  === TESTE DE MOVIMENTO EM DIFERENTES TERRENOS ===");
        
        System.out.println("\n🌲 Movendo através de floresta densa:");
        guerreiro.mover("floresta", 20);
        mago.mover("floresta", 20);
        ladino.mover("floresta", 20);
        
        System.out.println("\n🏔️  Movendo através de montanha:");
        guerreiro.mover("montanha", 15);
        mago.mover("montanha", 15);
        ladino.mover("montanha", 15);
        
        // ==================== TROCA DINÂMICA DE ESTRATÉGIAS ====================
        
        System.out.println("\n\n🔄 === DEMONSTRAÇÃO: TROCA DINÂMICA DE ESTRATÉGIAS ===");
        System.out.println("\n💡 CENÁRIO: Os personagens encontram situações que exigem");
        System.out.println("adaptação de suas estratégias em tempo de execução.\n");
        
        // Guerreiro muda para ataque à distância (encontrou um arco)
        System.out.println("🏹 Thorin encontra um arco élfico e muda sua estratégia:");
        guerreiro.setEstrategiaAtaque(new AtaqueDistancia());
        
        // Mago muda para movimento ágil (poção de agilidade)
        System.out.println("⚡ Gandalf bebe uma poção de agilidade:");
        mago.setEstrategiaMovimento(new MovimentoAgil());
        
        // Ladino muda para defesa regenerativa (item mágico)
        System.out.println("🌿 Legolas equipa um amuleto de regeneração:");
        ladino.setEstrategiaDefesa(new DefesaRegenerativa());
        
        // Teste das novas estratégias
        System.out.println("\n🧪 Testando as novas estratégias:");
        guerreiro.atacar(ladino);
        mago.mover("planície", 25);
        
        // ==================== COMBATE COM NOVAS ESTRATÉGIAS ====================
        
        System.out.println("\n🥊 RODADA 2: Combate com estratégias adaptadas");
        guerreiro.atacar(mago);
        ladino.atacar(guerreiro);
        mago.atacar(ladino);
        
        // ==================== MÚLTIPLAS TROCAS DE ESTRATÉGIA ====================
        
        System.out.println("\n\n🎭 === DEMONSTRAÇÃO: MÚLTIPLAS ADAPTAÇÕES ===");
        System.out.println("\n🎯 CENÁRIO: Situação crítica exige múltiplas mudanças estratégicas.\n");
        
        // Situação crítica: todos mudam para estratégias defensivas
        System.out.println("🚨 ALERTA: Boss apareceu! Todos adotam estratégias defensivas:");
        
        guerreiro.setEstrategiaAtaque(new AtaqueMagico()); // Ataque mágico contra boss
        guerreiro.setEstrategiaDefesa(new DefesaRegenerativa()); // Defesa regenerativa
        
        mago.setEstrategiaDefesa(new DefesaEscudo()); // Escudo mágico
        mago.setEstrategiaMovimento(new MovimentoFurtivo()); // Movimento furtivo
        
        ladino.setEstrategiaAtaque(new AtaqueCorpoACorpo()); // Ataque direto
        ladino.setEstrategiaMovimento(new MovimentoPesado()); // Movimento defensivo
        
        // ==================== STATUS FINAL ====================
        
        System.out.println("\n\n📊 === STATUS FINAL DOS PERSONAGENS ===");
        guerreiro.exibirStatus();
        mago.exibirStatus();
        ladino.exibirStatus();
        
        // ==================== ANÁLISE DO PADRÃO ====================
        
        System.out.println("\n\n🎓 === ANÁLISE DO PADRÃO STRATEGY ===");
        
        System.out.println("\n✅ BENEFÍCIOS DEMONSTRADOS:");
        System.out.println("   🔹 Eliminação de condicionais complexas");
        System.out.println("   🔹 Troca dinâmica de algoritmos em tempo de execução");
        System.out.println("   🔹 Facilidade para adicionar novas estratégias");
        System.out.println("   🔹 Reutilização de estratégias entre diferentes personagens");
        System.out.println("   🔹 Testabilidade - cada estratégia é independente");
        System.out.println("   🔹 Manutenibilidade - mudanças isoladas em cada estratégia");
        
        System.out.println("\n🔄 COMPARAÇÃO COM ABORDAGEM SEM PADRÃO:");
        System.out.println("   ❌ Sem Strategy: Métodos gigantes com if/switch");
        System.out.println("   ❌ Sem Strategy: Difícil adicionar novos comportamentos");
        System.out.println("   ❌ Sem Strategy: Código duplicado entre classes");
        System.out.println("   ❌ Sem Strategy: Violação do princípio Aberto/Fechado");
        System.out.println("   ✅ Com Strategy: Código limpo e extensível");
        System.out.println("   ✅ Com Strategy: Fácil manutenção e teste");
        System.out.println("   ✅ Com Strategy: Reutilização máxima de código");
        
        System.out.println("\n🎯 CASOS DE USO IDEAIS:");
        System.out.println("   🔸 Sistemas de pagamento (diferentes formas de pagamento)");
        System.out.println("   🔸 Algoritmos de ordenação (quicksort, mergesort, etc.)");
        System.out.println("   🔸 Sistemas de validação (diferentes regras de validação)");
        System.out.println("   🔸 Jogos (diferentes comportamentos de IA, estratégias de combate)");
        System.out.println("   🔸 Compressão de arquivos (diferentes algoritmos de compressão)");
        
        System.out.println("\n🏁 Demonstração do padrão Strategy concluída com sucesso!");
        System.out.println("\n💡 LIÇÃO APRENDIDA: O padrão Strategy transforma condicionais");
        System.out.println("complexas em uma hierarquia de classes elegante e extensível.");
    }
    
    /**
     * Método auxiliar para simular uma pausa dramática
     */
    private static void pausa() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}