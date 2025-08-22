package comportamentais.strategy.compadrao.classes;

import comportamentais.strategy.compadrao.interfaces.*;

/**
 * Classe Context do padrão Strategy
 * 
 * PADRÃO STRATEGY - CONTEXT:
 * Esta classe mantém uma referência para um objeto Strategy e delega
 * o trabalho para ele. O Context não conhece qual algoritmo concreto
 * está sendo usado.
 * 
 * BENEFÍCIOS:
 * - Elimina estruturas condicionais complexas (if/switch)
 * - Permite trocar algoritmos em tempo de execução
 * - Facilita adição de novos algoritmos
 * - Promove reutilização de código
 * - Segue o princípio Aberto/Fechado
 */
public class Personagem {
    
    // Atributos básicos do personagem
    private String nome;
    private String classe;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private int velocidade;
    
    // Estratégias - podem ser trocadas dinamicamente
    private EstrategiaAtaque estrategiaAtaque;
    private EstrategiaMovimento estrategiaMovimento;
    private EstrategiaDefesa estrategiaDefesa;
    
    /**
     * Construtor do personagem
     * 
     * @param nome Nome do personagem
     * @param classe Classe do personagem (Guerreiro, Mago, etc.)
     * @param vida Pontos de vida
     * @param ataque Poder de ataque base
     * @param defesa Poder de defesa base
     * @param velocidade Velocidade base
     */
    public Personagem(String nome, String classe, int vida, int ataque, int defesa, int velocidade) {
        this.nome = nome;
        this.classe = classe;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
    }
    
    // ==================== MÉTODOS DE ESTRATÉGIA ====================
    
    /**
     * Define a estratégia de ataque
     * 
     * STRATEGY PATTERN:
     * Permite trocar o algoritmo de ataque em tempo de execução
     */
    public void setEstrategiaAtaque(EstrategiaAtaque estrategia) {
        this.estrategiaAtaque = estrategia;
        System.out.println("🗡️  " + nome + " adotou estratégia: " + estrategia.getNomeEstrategia());
    }
    
    /**
     * Define a estratégia de movimento
     */
    public void setEstrategiaMovimento(EstrategiaMovimento estrategia) {
        this.estrategiaMovimento = estrategia;
        System.out.println("🏃 " + nome + " adotou estratégia: " + estrategia.getNomeEstrategia());
    }
    
    /**
     * Define a estratégia de defesa
     */
    public void setEstrategiaDefesa(EstrategiaDefesa estrategia) {
        this.estrategiaDefesa = estrategia;
        System.out.println("🛡️  " + nome + " adotou estratégia: " + estrategia.getNomeEstrategia());
    }
    
    // ==================== AÇÕES DO PERSONAGEM ====================
    
    /**
     * Executa um ataque usando a estratégia atual
     * 
     * STRATEGY PATTERN:
     * Delega a execução do ataque para a estratégia concreta,
     * sem conhecer os detalhes da implementação
     */
    public void atacar(Personagem alvo) {
        if (estrategiaAtaque == null) {
            System.out.println("❌ " + nome + " não possui estratégia de ataque definida!");
            return;
        }
        
        if (alvo.estaVivo()) {
            System.out.println("\n⚔️  === ATAQUE ===");
            System.out.println(nome + " (" + classe + ") ataca " + alvo.getNome() + " (" + alvo.getClasse() + ")");
            
            // Delega para a estratégia de ataque
            int dano = estrategiaAtaque.executarAtaque(nome, alvo.getNome(), ataque);
            
            // Aplica o dano no alvo
            alvo.receberDano(dano);
        } else {
            System.out.println("💀 " + alvo.getNome() + " já está morto!");
        }
    }
    
    /**
     * Executa movimento usando a estratégia atual
     */
    public void mover(String terreno, int distancia) {
        if (estrategiaMovimento == null) {
            System.out.println("❌ " + nome + " não possui estratégia de movimento definida!");
            return;
        }
        
        System.out.println("\n🏃 === MOVIMENTO ===");
        System.out.println(nome + " tenta se mover " + distancia + " metros em terreno: " + terreno);
        
        // Delega para a estratégia de movimento
        int velocidadeFinal = estrategiaMovimento.executarMovimento(nome, terreno, velocidade, distancia);
        
        System.out.println("✅ Movimento concluído com velocidade: " + velocidadeFinal);
    }
    
    /**
     * Recebe dano e executa defesa usando a estratégia atual
     */
    public void receberDano(int dano) {
        if (estrategiaDefesa == null) {
            // Sem estratégia de defesa, recebe dano total
            vida -= dano;
            System.out.println("💔 " + nome + " recebe " + dano + " de dano (sem defesa)!");
        } else {
            System.out.println("\n🛡️  === DEFESA ===");
            
            // Delega para a estratégia de defesa
            int danoFinal = estrategiaDefesa.executarDefesa(nome, dano, defesa);
            vida -= danoFinal;
            
            if (danoFinal < dano) {
                System.out.println("✅ Defesa bem-sucedida! Dano reduzido de " + dano + " para " + danoFinal);
            }
        }
        
        // Garante que a vida não fique negativa
        vida = Math.max(0, vida);
        
        System.out.println("❤️  Vida de " + nome + ": " + vida + "/" + vidaMaxima);
        
        if (!estaVivo()) {
            System.out.println("💀 " + nome + " foi derrotado!");
        }
    }
    
    // ==================== MÉTODOS AUXILIARES ====================
    
    /**
     * Verifica se o personagem está vivo
     */
    public boolean estaVivo() {
        return vida > 0;
    }
    
    /**
     * Cura o personagem
     */
    public void curar(int quantidade) {
        int vidaAnterior = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        int curaReal = vida - vidaAnterior;
        
        if (curaReal > 0) {
            System.out.println("💚 " + nome + " foi curado em " + curaReal + " pontos de vida!");
            System.out.println("❤️  Vida atual: " + vida + "/" + vidaMaxima);
        } else {
            System.out.println("💚 " + nome + " já está com vida máxima!");
        }
    }
    
    /**
     * Exibe informações detalhadas do personagem
     */
    public void exibirStatus() {
        System.out.println("\n📊 === STATUS DE " + nome.toUpperCase() + " ===");
        System.out.println("🏷️  Classe: " + classe);
        System.out.println("❤️  Vida: " + vida + "/" + vidaMaxima + " (" + 
                          String.format("%.1f", (vida * 100.0 / vidaMaxima)) + "%)");
        System.out.println("⚔️  Ataque: " + ataque);
        System.out.println("🛡️  Defesa: " + defesa);
        System.out.println("🏃 Velocidade: " + velocidade);
        
        System.out.println("\n🎯 ESTRATÉGIAS ATIVAS:");
        System.out.println("  ⚔️  Ataque: " + 
                          (estrategiaAtaque != null ? estrategiaAtaque.getNomeEstrategia() : "Nenhuma"));
        System.out.println("  🏃 Movimento: " + 
                          (estrategiaMovimento != null ? estrategiaMovimento.getNomeEstrategia() : "Nenhuma"));
        System.out.println("  🛡️  Defesa: " + 
                          (estrategiaDefesa != null ? estrategiaDefesa.getNomeEstrategia() : "Nenhuma"));
        
        // Exibe eficácia das estratégias
        if (estrategiaAtaque != null || estrategiaMovimento != null || estrategiaDefesa != null) {
            System.out.println("\n📈 EFICÁCIA DAS ESTRATÉGIAS:");
            if (estrategiaAtaque != null) {
                System.out.println("  ⚔️  Ataque: " + 
                                  String.format("%.1f", estrategiaAtaque.getEficacia() * 100) + "%");
            }
            if (estrategiaMovimento != null) {
                System.out.println("  🏃 Movimento: " + 
                                  String.format("%.1f", estrategiaMovimento.getEficacia() * 100) + "%");
            }
            if (estrategiaDefesa != null) {
                System.out.println("  🛡️  Defesa: " + 
                                  String.format("%.1f", estrategiaDefesa.getEficacia() * 100) + "%");
            }
        }
        
        System.out.println("=" + "=".repeat(30 + nome.length()));
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public String getNome() { return nome; }
    public String getClasse() { return classe; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getVelocidade() { return velocidade; }
    
    public EstrategiaAtaque getEstrategiaAtaque() { return estrategiaAtaque; }
    public EstrategiaMovimento getEstrategiaMovimento() { return estrategiaMovimento; }
    public EstrategiaDefesa getEstrategiaDefesa() { return estrategiaDefesa; }
    
    // Permite modificar atributos base (para evoluções, equipamentos, etc.)
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefesa(int defesa) { this.defesa = defesa; }
    public void setVelocidade(int velocidade) { this.velocidade = velocidade; }
}