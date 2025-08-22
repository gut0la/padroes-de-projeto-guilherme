package comportamentais.strategy.compadrao.strategies;

import comportamentais.strategy.compadrao.interfaces.EstrategiaAtaque;

/**
 * Estratégia concreta para ataques corpo a corpo
 * 
 * PADRÃO STRATEGY - CONCRETE STRATEGY:
 * Esta classe implementa um algoritmo específico de ataque corpo a corpo,
 * encapsulando toda a lógica relacionada a este tipo de combate.
 * 
 * CARACTERÍSTICAS:
 * - Alto dano base
 * - Bônus contra inimigos próximos
 * - Penalidade contra inimigos voadores
 * - Chance de ataque crítico
 */
public class AtaqueCorpoACorpo implements EstrategiaAtaque {
    
    private static final double CHANCE_CRITICO = 0.15; // 15% de chance
    private static final double MULTIPLICADOR_CRITICO = 2.0;
    
    /**
     * Implementa o algoritmo específico de ataque corpo a corpo
     * 
     * ALGORITMO:
     * 1. Calcula dano base com bônus de força
     * 2. Verifica chance de crítico
     * 3. Aplica modificadores baseados no tipo de alvo
     * 4. Retorna dano final
     */
    @Override
    public int executarAtaque(String atacante, int ataqueBase, Object alvo) {
        // Dano base com bônus de força para combate corpo a corpo
        int dano = ataqueBase + 10;
        
        // Verifica ataque crítico
        boolean critico = Math.random() < CHANCE_CRITICO;
        if (critico) {
            dano = (int) (dano * MULTIPLICADOR_CRITICO);
            System.out.println("  💥 ATAQUE CRÍTICO! Dano dobrado!");
        }
        
        // Modificadores baseados no tipo de alvo (simulação)
        String tipoAlvo = alvo.toString().toLowerCase();
        if (tipoAlvo.contains("orc") || tipoAlvo.contains("goblin")) {
            dano += 5; // Bônus contra humanoides
            System.out.println("  ⚔️  Bônus contra humanoides: +5 dano");
        } else if (tipoAlvo.contains("dragão") || tipoAlvo.contains("voador")) {
            dano -= 8; // Penalidade contra criaturas voadoras
            System.out.println("  ✈️  Penalidade contra criaturas voadoras: -8 dano");
        }
        
        // Exibe ação de ataque
        System.out.println(atacante + " " + getDescricaoAtaque());
        
        return Math.max(1, dano); // Dano mínimo de 1
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Combate Corpo a Corpo";
    }
    
    @Override
    public String getDescricaoAtaque() {
        return "avança com determinação e desfere um poderoso golpe de espada!";
    }
}

/**
 * Estratégia concreta para ataques mágicos
 * 
 * CARACTERÍSTICAS:
 * - Dano mágico que ignora armadura física
 * - Bônus contra criaturas das trevas
 * - Consome mana (simulado)
 * - Área de efeito
 */
class AtaqueMagico implements EstrategiaAtaque {
    
    private static final int BONUS_MAGICO = 15;
    private static final double CHANCE_AREA = 0.25; // 25% chance de área
    
    @Override
    public int executarAtaque(String atacante, int ataqueBase, Object alvo) {
        // Dano mágico com bônus especial
        int dano = ataqueBase + BONUS_MAGICO;
        
        // Verifica ataque em área
        boolean area = Math.random() < CHANCE_AREA;
        if (area) {
            dano = (int) (dano * 1.3); // 30% mais dano em área
            System.out.println("  🌟 EXPLOSÃO MÁGICA! Ataque em área!");
        }
        
        // Modificadores baseados no tipo de alvo
        String tipoAlvo = alvo.toString().toLowerCase();
        if (tipoAlvo.contains("morto-vivo") || tipoAlvo.contains("sombra")) {
            dano += 12; // Muito eficaz contra mortos-vivos
            System.out.println("  ✨ Magia sagrada! Bônus contra mortos-vivos: +12 dano");
        } else if (tipoAlvo.contains("golem") || tipoAlvo.contains("construto")) {
            dano -= 5; // Menos eficaz contra construtos
            System.out.println("  🗿 Resistência mágica: -5 dano");
        }
        
        // Simula consumo de mana
        System.out.println("  🔮 Mana consumida: 15 pontos");
        
        System.out.println(atacante + " " + getDescricaoAtaque());
        
        return Math.max(1, dano);
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Magia Ofensiva";
    }
    
    @Override
    public String getDescricaoAtaque() {
        return "concentra energia mágica e lança uma devastadora bola de fogo!";
    }
}

/**
 * Estratégia concreta para ataques à distância
 * 
 * CARACTERÍSTICAS:
 * - Ataque preciso de longa distância
 * - Bônus de precisão
 * - Eficaz contra alvos rápidos
 * - Penalidade em combate próximo
 */
class AtaqueDistancia implements EstrategiaAtaque {
    
    private static final int BONUS_PRECISAO = 8;
    private static final double CHANCE_TIRO_CERTEIRO = 0.20; // 20% chance
    
    @Override
    public int executarAtaque(String atacante, int ataqueBase, Object alvo) {
        // Dano base com bônus de precisão
        int dano = ataqueBase + BONUS_PRECISAO;
        
        // Verifica tiro certeiro (ignora defesas)
        boolean certeiro = Math.random() < CHANCE_TIRO_CERTEIRO;
        if (certeiro) {
            dano += 15;
            System.out.println("  🎯 TIRO CERTEIRO! Ignora defesas!");
        }
        
        // Modificadores baseados no tipo de alvo
        String tipoAlvo = alvo.toString().toLowerCase();
        if (tipoAlvo.contains("voador") || tipoAlvo.contains("rápido")) {
            dano += 7; // Bônus contra alvos ágeis
            System.out.println("  🏹 Tiro preciso contra alvo ágil: +7 dano");
        } else if (tipoAlvo.contains("blindado") || tipoAlvo.contains("tanque")) {
            dano -= 6; // Penalidade contra alvos blindados
            System.out.println("  🛡️  Armadura pesada reduz impacto: -6 dano");
        }
        
        // Simula consumo de munição
        System.out.println("  🏹 Flecha consumida");
        
        System.out.println(atacante + " " + getDescricaoAtaque());
        
        return Math.max(1, dano);
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Combate à Distância";
    }
    
    @Override
    public String getDescricaoAtaque() {
        return "mira cuidadosamente e dispara uma flecha certeira!";
    }
}

/**
 * Estratégia concreta para ataques furtivos
 * 
 * CARACTERÍSTICAS:
 * - Alto dano quando não detectado
 * - Chance de ataque instantâneo
 * - Bônus contra alvos distraídos
 * - Requer posicionamento
 */
class AtaqueFurtivo implements EstrategiaAtaque {
    
    private static final double CHANCE_FURTIVO = 0.30; // 30% chance
    private static final double MULTIPLICADOR_FURTIVO = 2.5;
    
    @Override
    public int executarAtaque(String atacante, int ataqueBase, Object alvo) {
        // Dano base moderado
        int dano = ataqueBase + 5;
        
        // Verifica se conseguiu ataque furtivo
        boolean furtivo = Math.random() < CHANCE_FURTIVO;
        if (furtivo) {
            dano = (int) (dano * MULTIPLICADOR_FURTIVO);
            System.out.println("  🗡️  ATAQUE FURTIVO! Dano massivo pelas costas!");
        }
        
        // Modificadores baseados no tipo de alvo
        String tipoAlvo = alvo.toString().toLowerCase();
        if (tipoAlvo.contains("mago") || tipoAlvo.contains("arqueiro")) {
            dano += 10; // Muito eficaz contra alvos frágeis
            System.out.println("  🎭 Alvo vulnerável: +10 dano");
        } else if (tipoAlvo.contains("alerta") || tipoAlvo.contains("vigilante")) {
            dano -= 8; // Penalidade contra alvos alertas
            System.out.println("  👁️  Alvo alerta detectou movimento: -8 dano");
        }
        
        System.out.println(atacante + " " + getDescricaoAtaque());
        
        return Math.max(1, dano);
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Ataque Furtivo";
    }
    
    @Override
    public String getDescricaoAtaque() {
        return "move-se silenciosamente pelas sombras e ataca pelas costas!";
    }
}