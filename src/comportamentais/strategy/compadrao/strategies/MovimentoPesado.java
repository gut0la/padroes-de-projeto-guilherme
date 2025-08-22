package comportamentais.strategy.compadrao.strategies;

import comportamentais.strategy.compadrao.interfaces.EstrategiaMovimento;

/**
 * Estratégia concreta para movimento pesado (guerreiros)
 * 
 * PADRÃO STRATEGY - CONCRETE STRATEGY:
 * Esta classe implementa um algoritmo específico de movimento pesado,
 * típico de guerreiros com armadura pesada.
 * 
 * CARACTERÍSTICAS:
 * - Movimento lento mas estável
 * - Bônus em terrenos rochosos
 * - Penalidade em terrenos pantanosos
 * - Resistente a empurrões
 */
public class MovimentoPesado implements EstrategiaMovimento {
    
    @Override
    public int executarMovimento(String personagem, String terreno, int velocidadeBase) {
        // Velocidade base reduzida devido ao peso da armadura
        int velocidade = velocidadeBase - 2;
        
        // Modificadores baseados no terreno
        switch (terreno.toLowerCase()) {
            case "montanha":
            case "rochoso":
                velocidade += 3; // Guerreiros são bons em terrenos rochosos
                System.out.println("  ⛰️  Terreno rochoso favorece movimento pesado: +3 velocidade");
                break;
                
            case "pântano":
            case "lama":
                velocidade -= 3; // Armadura pesada afunda na lama
                System.out.println("  🐸 Armadura pesada afunda no pântano: -3 velocidade");
                break;
                
            case "gelo":
                velocidade -= 1; // Dificuldade para manter equilíbrio
                System.out.println("  🧊 Gelo dificulta movimento pesado: -1 velocidade");
                break;
                
            case "areia":
                velocidade -= 2; // Afunda na areia
                System.out.println("  🏜️  Areia dificulta movimento pesado: -2 velocidade");
                break;
                
            default:
                // Terreno neutro - sem modificadores
                break;
        }
        
        // Bônus de estabilidade (resistência a empurrões)
        System.out.println("  🛡️  Movimento estável e resistente a empurrões");
        
        System.out.println(personagem + " " + getDescricaoMovimento() + " por " + terreno);
        
        return Math.max(1, velocidade); // Velocidade mínima de 1
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Movimento Pesado";
    }
    
    @Override
    public String getDescricaoMovimento() {
        return "avança com passos firmes e determinados";
    }
    
    @Override
    public boolean isEficazEm(String terreno) {
        return terreno.toLowerCase().equals("montanha") || 
               terreno.toLowerCase().equals("rochoso");
    }
}

/**
 * Estratégia concreta para movimento mágico (magos)
 * 
 * CARACTERÍSTICAS:
 * - Levitação sobre obstáculos
 * - Bônus em áreas mágicas
 * - Penalidade em áreas anti-magia
 * - Consome mana
 */
class MovimentoMagico implements EstrategiaMovimento {
    
    @Override
    public int executarMovimento(String personagem, String terreno, int velocidadeBase) {
        // Velocidade base com bônus mágico
        int velocidade = velocidadeBase + 1;
        
        // Modificadores baseados no terreno
        switch (terreno.toLowerCase()) {
            case "floresta":
            case "selva":
                velocidade -= 2; // Interferência de energia natural
                System.out.println("  🌳 Energia natural interfere na magia: -2 velocidade");
                break;
                
            case "torre mágica":
            case "círculo mágico":
                velocidade += 4; // Amplificação mágica
                System.out.println("  ✨ Energia mágica amplifica movimento: +4 velocidade");
                break;
                
            case "pântano":
            case "água":
                velocidade += 2; // Levitação sobre obstáculos líquidos
                System.out.println("  🌊 Levitação sobre obstáculos líquidos: +2 velocidade");
                break;
                
            case "área anti-magia":
                velocidade -= 5; // Supressão mágica
                System.out.println("  🚫 Área anti-magia suprime levitação: -5 velocidade");
                break;
                
            default:
                // Terreno neutro
                break;
        }
        
        // Simula consumo de mana
        System.out.println("  🔮 Mana consumida para levitação: 5 pontos");
        
        System.out.println(personagem + " " + getDescricaoMovimento() + " por " + terreno);
        
        return Math.max(1, velocidade);
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Movimento Mágico";
    }
    
    @Override
    public String getDescricaoMovimento() {
        return "levita graciosamente alguns centímetros do chão";
    }
    
    @Override
    public boolean isEficazEm(String terreno) {
        return terreno.toLowerCase().contains("mágic") || 
               terreno.toLowerCase().equals("água") ||
               terreno.toLowerCase().equals("pântano");
    }
}

/**
 * Estratégia concreta para movimento ágil (arqueiros/ladinos)
 * 
 * CARACTERÍSTICAS:
 * - Alta velocidade
 * - Bônus em terrenos abertos
 * - Habilidade de escalada
 * - Penalidade em espaços apertados
 */
class MovimentoAgil implements EstrategiaMovimento {
    
    @Override
    public int executarMovimento(String personagem, String terreno, int velocidadeBase) {
        // Velocidade base aumentada por agilidade
        int velocidade = velocidadeBase + 3;
        
        // Modificadores baseados no terreno
        switch (terreno.toLowerCase()) {
            case "planície":
            case "campo aberto":
                velocidade += 4; // Terreno ideal para corrida
                System.out.println("  🏃 Terreno aberto permite corrida livre: +4 velocidade");
                break;
                
            case "floresta":
            case "árvores":
                velocidade += 2; // Habilidade de escalada e parkour
                System.out.println("  🌲 Agilidade permite parkour nas árvores: +2 velocidade");
                break;
                
            case "caverna":
            case "túnel":
                velocidade -= 3; // Espaços apertados limitam agilidade
                System.out.println("  🕳️  Espaços apertados limitam agilidade: -3 velocidade");
                break;
                
            case "gelo":
                velocidade += 1; // Agilidade ajuda com equilíbrio
                System.out.println("  ⛸️  Agilidade ajuda no equilíbrio no gelo: +1 velocidade");
                break;
                
            case "escombros":
                velocidade -= 1; // Obstáculos requerem cuidado
                System.out.println("  🧱 Escombros requerem movimento cuidadoso: -1 velocidade");
                break;
                
            default:
                // Terreno neutro
                break;
        }
        
        // Bônus de esquiva durante movimento
        System.out.println("  💨 Movimento ágil dificulta ataques inimigos");
        
        System.out.println(personagem + " " + getDescricaoMovimento() + " por " + terreno);
        
        return Math.max(1, velocidade);
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Movimento Ágil";
    }
    
    @Override
    public String getDescricaoMovimento() {
        return "corre com agilidade felina, saltando obstáculos";
    }
    
    @Override
    public boolean isEficazEm(String terreno) {
        return terreno.toLowerCase().equals("planície") || 
               terreno.toLowerCase().equals("floresta") ||
               terreno.toLowerCase().equals("campo aberto");
    }
}

/**
 * Estratégia concreta para movimento furtivo (assassinos/espiões)
 * 
 * CARACTERÍSTICAS:
 * - Movimento silencioso
 * - Bônus em áreas com sombras
 * - Habilidade de se esconder
 * - Velocidade moderada mas indetectável
 */
class MovimentoFurtivo implements EstrategiaMovimento {
    
    @Override
    public int executarMovimento(String personagem, String terreno, int velocidadeBase) {
        // Velocidade reduzida para manter silêncio
        int velocidade = velocidadeBase;
        
        // Modificadores baseados no terreno
        switch (terreno.toLowerCase()) {
            case "sombras":
            case "escuridão":
                velocidade += 3; // Ambiente ideal para furtividade
                System.out.println("  🌑 Sombras favorecem movimento furtivo: +3 velocidade");
                break;
                
            case "telhados":
            case "vigas":
                velocidade += 2; // Habilidade de escalada silenciosa
                System.out.println("  🏠 Movimento silencioso pelos telhados: +2 velocidade");
                break;
                
            case "folhas secas":
            case "galhos":
                velocidade -= 2; // Risco de fazer ruído
                System.out.println("  🍂 Folhas secas podem fazer ruído: -2 velocidade");
                break;
                
            case "água rasa":
                velocidade -= 1; // Respingos podem denunciar
                System.out.println("  💧 Água pode fazer respingos: -1 velocidade");
                break;
                
            case "área iluminada":
                velocidade -= 3; // Dificulta ocultação
                System.out.println("  💡 Área iluminada dificulta furtividade: -3 velocidade");
                break;
                
            default:
                // Terreno neutro
                break;
        }
        
        // Bônus de ocultação
        System.out.println("  👤 Movimento silencioso e difícil de detectar");
        
        System.out.println(personagem + " " + getDescricaoMovimento() + " por " + terreno);
        
        return Math.max(1, velocidade);
    }
    
    @Override
    public String getNomeEstrategia() {
        return "Movimento Furtivo";
    }
    
    @Override
    public String getDescricaoMovimento() {
        return "move-se silenciosamente como uma sombra";
    }
    
    @Override
    public boolean isEficazEm(String terreno) {
        return terreno.toLowerCase().contains("sombra") || 
               terreno.toLowerCase().contains("escur") ||
               terreno.toLowerCase().equals("telhados");
    }
}