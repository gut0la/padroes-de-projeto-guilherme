package comportamentais.strategy.compadrao.strategies;

import comportamentais.strategy.compadrao.interfaces.EstrategiaDefesa;

/**
 * Estratégia concreta para defesa com escudo
 * 
 * PADRÃO STRATEGY - CONCRETE STRATEGY:
 * Esta classe implementa um algoritmo específico de defesa usando escudo,
 * típico de guerreiros e paladinos.
 * 
 * CARACTERÍSTICAS:
 * - Alta redução de dano físico
 * - Chance de bloqueio total
 * - Menos eficaz contra magia
 * - Pode quebrar sob muito dano
 */
public class DefesaEscudo implements EstrategiaDefesa {
    
    private static final double CHANCE_BLOQUEIO_TOTAL = 0.20; // 20% chance
    private static final int REDUCAO_BASE = 8;
    private int durabilitadeEscudo = 100;
    
    @Override
    public int executarDefesa(String defensor, int danoRecebido, int defesaBase) {
        // Verifica se o escudo ainda está funcional
        if (durabilitadeEscudo <= 0) {
            System.out.println("  🛡️💥 ESCUDO QUEBRADO! Defesa reduzida!");
            return Math.max(0, danoRecebido - (defesaBase / 2));
        }
        
        // Calcula redução base do escudo
        int reducao = REDUCAO_BASE + defesaBase;
        
        // Verifica bloqueio total
        boolean bloqueioTotal = Math.random() < CHANCE_BLOQUEIO_TOTAL;
        if (bloqueioTotal) {
            System.out.println("  🛡️✨ BLOQUEIO PERFEITO! Dano completamente anulado!");
            durabilitadeEscudo -= 5; // Escudo sofre desgaste mesmo em bloqueio perfeito
            return 0;
        }
        
        // Redução normal de dano
        int danoFinal = Math.max(0, danoRecebido - reducao);
        
        // Escudo sofre desgaste baseado no dano recebido
        int desgaste = Math.max(1, danoRecebido / 5);
        durabilitadeEscudo -= desgaste;
        
        System.out.println(defensor + " " + getDescricaoDefesa());
        System.out.println("  🛡️  Redução de dano: " + (danoRecebido - danoFinal));
        System.out.println("  🔧 Durabilidade do escudo: " + durabilitadeEscudo + "/100");
        
        // Aviso se escudo está danificado
        if (durabilitadeEscudo <= 30) {
            System.out.println("  ⚠️  ESCUDO DANIFICADO! Considere reparo!");
        }
        
        return danoFinal;
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Defesa com Escudo";
    }
    
    @Override
    public String getDescricaoDefesa() {
        return "levanta o escudo e bloqueia o ataque com determinação!";
    }
    
    @Override
    public double getEficacia() {
        // Eficácia baseada na durabilidade do escudo
        return (durabilitadeEscudo / 100.0) * 0.85; // Máximo 85% de eficácia
    }
    
    /**
     * Método para reparar o escudo
     */
    public void repararEscudo() {
        durabilitadeEscudo = 100;
        System.out.println("  🔨 Escudo reparado! Durabilidade restaurada.");
    }
}

/**
 * Estratégia concreta para defesa mágica
 * 
 * CARACTERÍSTICAS:
 * - Barreira mágica que absorve dano
 * - Muito eficaz contra magia
 * - Menos eficaz contra ataques físicos
 * - Consome mana
 */
class DefesaMagica implements EstrategiaDefesa {
    
    private static final int CUSTO_MANA = 10;
    private static final double EFICACIA_CONTRA_MAGIA = 0.90;
    private static final double EFICACIA_CONTRA_FISICO = 0.30;
    private int manaDisponivel = 100;
    
    @Override
    public int executarDefesa(String defensor, int danoRecebido, int defesaBase) {
        // Verifica se há mana suficiente
        if (manaDisponivel < CUSTO_MANA) {
            System.out.println("  🔮💔 MANA INSUFICIENTE! Barreira mágica falhou!");
            return danoRecebido; // Sem defesa
        }
        
        // Consome mana
        manaDisponivel -= CUSTO_MANA;
        
        // Determina tipo de ataque (simulação baseada no dano)
        boolean ataquemagico = danoRecebido > 25; // Ataques mágicos tendem a ser mais fortes
        
        double eficacia = ataquemagico ? EFICACIA_CONTRA_MAGIA : EFICACIA_CONTRA_FISICO;
        int reducao = (int) ((defesaBase + 5) * eficacia);
        
        int danoFinal = Math.max(0, danoRecebido - reducao);
        
        System.out.println(defensor + " " + getDescricaoDefesa());
        
        if (ataquemagico) {
            System.out.println("  ✨ Barreira mágica muito eficaz contra magia!");
        } else {
            System.out.println("  ⚔️  Barreira mágica parcialmente eficaz contra ataques físicos");
        }
        
        System.out.println("  🔮 Mana consumida: " + CUSTO_MANA + " (restante: " + manaDisponivel + ")");
        System.out.println("  🛡️  Redução de dano: " + (danoRecebido - danoFinal));
        
        return danoFinal;
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Defesa Mágica";
    }
    
    @Override
    public String getDescricaoDefesa() {
        return "conjura uma barreira mágica cintilante ao seu redor!";
    }
    
    @Override
    public double getEficacia() {
        // Eficácia baseada na mana disponível
        return (manaDisponivel / 100.0) * 0.75; // Máximo 75% de eficácia
    }
    
    /**
     * Método para restaurar mana
     */
    public void restaurarMana(int quantidade) {
        manaDisponivel = Math.min(100, manaDisponivel + quantidade);
        System.out.println("  💙 Mana restaurada: +" + quantidade + " (total: " + manaDisponivel + ")");
    }
}

/**
 * Estratégia concreta para defesa por esquiva
 * 
 * CARACTERÍSTICAS:
 * - Evita completamente o dano ou recebe tudo
 * - Baseada em agilidade
 * - Mais eficaz contra ataques lentos
 * - Consome stamina
 */
class DefesaEsquiva implements EstrategiaDefesa {
    
    private static final double CHANCE_BASE_ESQUIVA = 0.35; // 35% base
    private static final int CUSTO_STAMINA = 15;
    private int staminaDisponivel = 100;
    
    @Override
    public int executarDefesa(String defensor, int danoRecebido, int defesaBase) {
        // Verifica se há stamina suficiente
        if (staminaDisponivel < CUSTO_STAMINA) {
            System.out.println("  💨💔 STAMINA INSUFICIENTE! Muito cansado para esquivar!");
            return danoRecebido; // Sem defesa
        }
        
        // Calcula chance de esquiva baseada na defesa (agilidade)
        double chanceEsquiva = CHANCE_BASE_ESQUIVA + (defesaBase * 0.02);
        chanceEsquiva = Math.min(0.80, chanceEsquiva); // Máximo 80% de chance
        
        // Consome stamina
        staminaDisponivel -= CUSTO_STAMINA;
        
        // Tenta esquivar
        boolean esquivou = Math.random() < chanceEsquiva;
        
        System.out.println(defensor + " " + getDescricaoDefesa());
        
        if (esquivou) {
            System.out.println("  💨✨ ESQUIVA PERFEITA! Ataque completamente evitado!");
            System.out.println("  🏃 Stamina consumida: " + CUSTO_STAMINA + " (restante: " + staminaDisponivel + ")");
            return 0;
        } else {
            System.out.println("  💨❌ Falhou na esquiva! Recebe dano total!");
            System.out.println("  😰 Stamina consumida: " + CUSTO_STAMINA + " (restante: " + staminaDisponivel + ")");
            return danoRecebido;
        }
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Defesa por Esquiva";
    }
    
    @Override
    public String getDescricaoDefesa() {
        return "tenta esquivar com movimentos ágeis e precisos!";
    }
    
    @Override
    public double getEficacia() {
        // Eficácia baseada na stamina disponível
        return (staminaDisponivel / 100.0) * 0.70; // Máximo 70% de eficácia
    }
    
    /**
     * Método para recuperar stamina
     */
    public void recuperarStamina(int quantidade) {
        staminaDisponivel = Math.min(100, staminaDisponivel + quantidade);
        System.out.println("  💚 Stamina recuperada: +" + quantidade + " (total: " + staminaDisponivel + ")");
    }
}

/**
 * Estratégia concreta para defesa regenerativa
 * 
 * CARACTERÍSTICAS:
 * - Absorve parte do dano e regenera vida
 * - Eficaz contra dano contínuo
 * - Melhora com o tempo
 * - Baseada em resistência natural
 */
class DefesaRegenerativa implements EstrategiaDefesa {
    
    private static final double TAXA_ABSORCAO = 0.40; // 40% do dano absorvido
    private static final double TAXA_REGENERACAO = 0.15; // 15% do dano vira cura
    private int vidaRegenerada = 0;
    
    @Override
    public int executarDefesa(String defensor, int danoRecebido, int defesaBase) {
        // Calcula absorção baseada na defesa
        int absorcao = (int) ((defesaBase + 3) * TAXA_ABSORCAO);
        int danoFinal = Math.max(0, danoRecebido - absorcao);
        
        // Calcula regeneração baseada no dano absorvido
        int regeneracao = (int) ((danoRecebido - danoFinal) * TAXA_REGENERACAO);
        vidaRegenerada += regeneracao;
        
        System.out.println(defensor + " " + getDescricaoDefesa());
        System.out.println("  🌿 Dano absorvido: " + (danoRecebido - danoFinal));
        
        if (regeneracao > 0) {
            System.out.println("  💚 Vida regenerada: +" + regeneracao + " (total regenerado: " + vidaRegenerada + ")");
        }
        
        // Bônus de resistência acumulativa
        if (vidaRegenerada > 50) {
            danoFinal = (int) (danoFinal * 0.9); // 10% de redução adicional
            System.out.println("  🛡️  Resistência acumulativa: -10% dano adicional");
        }
        
        return danoFinal;
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Defesa Regenerativa";
    }
    
    @Override
    public String getDescricaoDefesa() {
        return "absorve o impacto e regenera energia vital!";
    }
    
    @Override
    public double getEficacia() {
        // Eficácia melhora com regeneração acumulada
        double bonus = Math.min(0.20, vidaRegenerada / 250.0); // Máximo 20% de bônus
        return 0.60 + bonus; // Base 60% + bônus
    }
    
    /**
     * Retorna total de vida regenerada
     */
    public int getVidaRegenerada() {
        return vidaRegenerada;
    }
}