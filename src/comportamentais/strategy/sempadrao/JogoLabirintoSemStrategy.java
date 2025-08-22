package comportamentais.strategy.sempadrao;

/**
 * Demonstração do problema SEM usar o padrão Strategy
 * 
 * PROBLEMA: O código usa estruturas condicionais (if/switch) para determinar
 * qual algoritmo usar. Isso viola vários princípios SOLID e torna o código
 * difícil de manter e estender.
 * 
 * VIOLAÇÕES:
 * - Princípio Aberto/Fechado: Para adicionar nova estratégia, precisa modificar a classe
 * - Responsabilidade Única: Classe conhece todos os algoritmos possíveis
 * - DRY: Lógica de seleção de algoritmo espalhada pelo código
 * - Baixo acoplamento: Classe acoplada a implementações específicas
 */
public class JogoLabirintoSemStrategy {
    
    public static void main(String[] args) {
        System.out.println("=== JOGO DE LABIRINTO SEM STRATEGY ===");
        System.out.println("Problema: Algoritmos fixos com estruturas condicionais\n");
        
        // Criando personagens com diferentes tipos
        Personagem guerreiro = new Personagem("Thorin", "guerreiro", 100, 20);
        Personagem mago = new Personagem("Gandalf", "mago", 80, 15);
        Personagem arqueiro = new Personagem("Legolas", "arqueiro", 90, 18);
        
        // Criando inimigos
        Inimigo orc = new Inimigo("Orc", 60);
        Inimigo troll = new Inimigo("Troll", 120);
        
        System.out.println("=== COMBATES ===");
        
        // Combates - note como cada personagem precisa verificar seu tipo
        guerreiro.atacar(orc);
        mago.atacar(orc);
        arqueiro.atacar(troll);
        
        System.out.println("\n=== MOVIMENTAÇÃO ===");
        
        // Movimentação - mesma lógica condicional
        guerreiro.mover("floresta");
        mago.mover("montanha");
        arqueiro.mover("planície");
        
        System.out.println("\n=== DEFESA ===");
        
        // Defesa - mais condicionais
        guerreiro.defender(25);
        mago.defender(30);
        arqueiro.defender(20);
        
        System.out.println("\n=== PROBLEMAS EVIDENCIADOS ===");
        System.out.println("1. Código repetitivo com if/switch");
        System.out.println("2. Difícil adicionar novos tipos de personagem");
        System.out.println("3. Lógica de algoritmos misturada com lógica de negócio");
        System.out.println("4. Violação do princípio aberto/fechado");
    }
}

/**
 * Classe Personagem com TODOS os algoritmos embutidos
 * 
 * PROBLEMA: Esta classe viola o princípio da responsabilidade única
 * pois conhece todos os algoritmos de ataque, movimento e defesa.
 */
class Personagem {
    private String nome;
    private String tipo; // PROBLEMA: Tipo determina comportamento via condicionais
    private int vida;
    private int ataque;
    
    public Personagem(String nome, String tipo, int vida, int ataque) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
        
        System.out.println(nome + " (" + tipo + ") criado com " + vida + " de vida e " + ataque + " de ataque");
    }
    
    /**
     * Método de ataque com ESTRUTURA CONDICIONAL
     * PROBLEMA: Para adicionar novo tipo, precisa modificar este método
     */
    public void atacar(Inimigo inimigo) {
        int dano = 0;
        String tipoAtaque = "";
        
        // PROBLEMA: Switch/if gigante que viola Open/Closed Principle
        switch (tipo.toLowerCase()) {
            case "guerreiro":
                // Algoritmo de ataque corpo a corpo
                dano = ataque + 10; // Bônus de força
                tipoAtaque = "golpe de espada";
                System.out.println(nome + " avança e desfere um " + tipoAtaque + "!");
                break;
                
            case "mago":
                // Algoritmo de ataque mágico
                dano = ataque + 15; // Bônus mágico
                tipoAtaque = "bola de fogo";
                System.out.println(nome + " conjura uma " + tipoAtaque + "!");
                break;
                
            case "arqueiro":
                // Algoritmo de ataque à distância
                dano = ataque + 8; // Bônus de precisão
                tipoAtaque = "flecha certeira";
                System.out.println(nome + " dispara uma " + tipoAtaque + "!");
                break;
                
            default:
                // PROBLEMA: Comportamento padrão pode não fazer sentido
                dano = ataque;
                tipoAtaque = "ataque básico";
                System.out.println(nome + " realiza um " + tipoAtaque);
                break;
        }
        
        inimigo.receberDano(dano);
        System.out.println("  Dano causado: " + dano);
    }
    
    /**
     * Método de movimento com MAIS CONDICIONAIS
     * PROBLEMA: Mesma estrutura condicional repetida
     */
    public void mover(String terreno) {
        String movimento = "";
        int velocidade = 0;
        
        // PROBLEMA: Outra estrutura condicional baseada no tipo
        switch (tipo.toLowerCase()) {
            case "guerreiro":
                // Algoritmo de movimento pesado
                velocidade = 5;
                movimento = "marcha pesada";
                if (terreno.equals("montanha")) {
                    velocidade += 2; // Guerreiros são bons em montanhas
                }
                break;
                
            case "mago":
                // Algoritmo de movimento mágico
                velocidade = 7;
                movimento = "levitação";
                if (terreno.equals("floresta")) {
                    velocidade -= 1; // Magos têm dificuldade em florestas
                }
                break;
                
            case "arqueiro":
                // Algoritmo de movimento ágil
                velocidade = 8;
                movimento = "corrida ágil";
                if (terreno.equals("planície")) {
                    velocidade += 3; // Arqueiros são rápidos em planícies
                }
                break;
                
            default:
                velocidade = 6;
                movimento = "caminhada";
                break;
        }
        
        System.out.println(nome + " se move por " + terreno + " usando " + movimento + " (velocidade: " + velocidade + ")");
    }
    
    /**
     * Método de defesa com AINDA MAIS CONDICIONAIS
     * PROBLEMA: Padrão se repete em todos os métodos
     */
    public void defender(int danoRecebido) {
        int defesa = 0;
        String tipoDefesa = "";
        
        // PROBLEMA: Terceira estrutura condicional idêntica
        switch (tipo.toLowerCase()) {
            case "guerreiro":
                // Algoritmo de defesa com armadura
                defesa = 8;
                tipoDefesa = "bloqueio com escudo";
                break;
                
            case "mago":
                // Algoritmo de defesa mágica
                defesa = 3;
                tipoDefesa = "barreira mágica";
                break;
                
            case "arqueiro":
                // Algoritmo de defesa por agilidade
                defesa = 5;
                tipoDefesa = "esquiva rápida";
                break;
                
            default:
                defesa = 2;
                tipoDefesa = "defesa básica";
                break;
        }
        
        int danoFinal = Math.max(0, danoRecebido - defesa);
        vida -= danoFinal;
        
        System.out.println(nome + " usa " + tipoDefesa + " (defesa: " + defesa + ")");
        System.out.println("  Dano recebido: " + danoFinal + " (vida restante: " + vida + ")");
    }
    
    // Getters
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getVida() { return vida; }
}

/**
 * Classe Inimigo simples para demonstração
 */
class Inimigo {
    private String nome;
    private int vida;
    
    public Inimigo(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }
    
    public void receberDano(int dano) {
        vida -= dano;
        System.out.println("  " + nome + " recebeu " + dano + " de dano (vida: " + vida + ")");
        
        if (vida <= 0) {
            System.out.println("  " + nome + " foi derrotado!");
        }
    }
    
    public String getNome() { return nome; }
    public int getVida() { return vida; }
}