package comportamentais.command.compadrao.classes;

/**
 * Classe Jogador - RECEIVER no padrão Command
 * 
 * PADRÃO COMMAND - RECEIVER:
 * Esta classe sabe como executar as operações associadas a uma requisição.
 * Qualquer classe pode servir como Receiver - não há interface específica.
 * O Receiver contém a lógica de negócio real que será executada pelos comandos.
 * 
 * RESPONSABILIDADES:
 * - Implementar operações de negócio
 * - Manter estado interno
 * - Fornecer métodos que os comandos podem chamar
 * - Não conhecer os comandos que o utilizam (baixo acoplamento)
 */
public class Jogador {
    
    private String nome;
    private int x, y; // Posição no labirinto
    private int vida;
    private int vidaMaxima;
    private int mana;
    private int manaMaxima;
    private int experiencia;
    private int nivel;
    
    /**
     * Construtor do jogador
     */
    public Jogador(String nome, int x, int y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.vida = 100;
        this.vidaMaxima = 100;
        this.mana = 50;
        this.manaMaxima = 50;
        this.experiencia = 0;
        this.nivel = 1;
    }
    
    // ==================== OPERAÇÕES DE MOVIMENTO ====================
    
    /**
     * Move o jogador para o norte
     * 
     * RECEIVER OPERATION:
     * Esta é uma operação que pode ser encapsulada em um comando.
     * O comando salvará o estado anterior para permitir undo.
     */
    public void moverNorte() {
        y++;
        System.out.println(nome + " moveu para o norte. Nova posição: (" + x + ", " + y + ")");
    }
    
    /**
     * Move o jogador para o sul
     */
    public void moverSul() {
        y--;
        System.out.println(nome + " moveu para o sul. Nova posição: (" + x + ", " + y + ")");
    }
    
    /**
     * Move o jogador para o leste
     */
    public void moverLeste() {
        x++;
        System.out.println(nome + " moveu para o leste. Nova posição: (" + x + ", " + y + ")");
    }
    
    /**
     * Move o jogador para o oeste
     */
    public void moverOeste() {
        x--;
        System.out.println(nome + " moveu para o oeste. Nova posição: (" + x + ", " + y + ")");
    }
    
    /**
     * Move o jogador para uma posição específica
     * Usado principalmente para operações de undo
     */
    public void moverPara(int novoX, int novoY) {
        int antigoX = this.x;
        int antigoY = this.y;
        this.x = novoX;
        this.y = novoY;
        System.out.println(nome + " moveu de (" + antigoX + ", " + antigoY + ") para (" + x + ", " + y + ")");
    }
    
    // ==================== OPERAÇÕES DE COMBATE ====================
    
    /**
     * Executa um ataque
     */
    public int atacar() {
        int dano = 15 + (nivel * 2);
        System.out.println(nome + " ataca causando " + dano + " de dano!");
        return dano;
    }
    
    /**
     * Executa um ataque mágico
     */
    public int atacarMagico() {
        if (mana >= 15) {
            mana -= 15;
            int dano = 25 + (nivel * 3);
            System.out.println(nome + " lança magia causando " + dano + " de dano! Mana: " + mana + "/" + manaMaxima);
            return dano;
        } else {
            System.out.println("Mana insuficiente para ataque mágico!");
            return 0;
        }
    }
    
    /**
     * Recebe dano
     */
    public void receberDano(int dano) {
        int vidaAnterior = vida;
        vida = Math.max(0, vida - dano);
        System.out.println(nome + " recebeu " + dano + " de dano. Vida: " + vida + "/" + vidaMaxima + 
                          " (era " + vidaAnterior + ")");
        
        if (vida == 0) {
            System.out.println(nome + " foi derrotado!");
        }
    }
    
    // ==================== OPERAÇÕES DE ITENS ====================
    
    /**
     * Usa poção de cura
     */
    public int usarPocaoCura() {
        if (vida >= vidaMaxima) {
            System.out.println(nome + " já está com vida máxima!");
            return 0;
        }
        
        int vidaAnterior = vida;
        int cura = 30;
        vida = Math.min(vidaMaxima, vida + cura);
        int curaReal = vida - vidaAnterior;
        
        System.out.println(nome + " usa poção de cura. Vida restaurada: +" + curaReal + 
                          " (" + vida + "/" + vidaMaxima + ")");
        return curaReal;
    }
    
    /**
     * Usa poção de mana
     */
    public int usarPocaoMana() {
        if (mana >= manaMaxima) {
            System.out.println(nome + " já está com mana máxima!");
            return 0;
        }
        
        int manaAnterior = mana;
        int restauracao = 25;
        mana = Math.min(manaMaxima, mana + restauracao);
        int restauracaoReal = mana - manaAnterior;
        
        System.out.println(nome + " usa poção de mana. Mana restaurada: +" + restauracaoReal + 
                          " (" + mana + "/" + manaMaxima + ")");
        return restauracaoReal;
    }
    
    // ==================== OPERAÇÕES DE PROGRESSÃO ====================
    
    /**
     * Ganha experiência
     */
    public boolean ganharExperiencia(int exp) {
        int expAnterior = experiencia;
        experiencia += exp;
        System.out.println(nome + " ganhou " + exp + " de experiência! Total: " + experiencia);
        
        // Verifica se subiu de nível
        int expNecessaria = nivel * 100;
        if (experiencia >= expNecessaria) {
            subirNivel();
            return true; // Subiu de nível
        }
        
        return false; // Não subiu de nível
    }
    
    /**
     * Sobe de nível
     */
    private void subirNivel() {
        nivel++;
        int bonusVida = 20;
        int bonusMana = 10;
        
        vidaMaxima += bonusVida;
        vida = vidaMaxima; // Cura completa ao subir de nível
        manaMaxima += bonusMana;
        mana = manaMaxima; // Restaura mana completa
        
        System.out.println(nome + " subiu para o nível " + nivel + "!");
        System.out.println("   Vida máxima: +" + bonusVida + " (" + vidaMaxima + ")");
        System.out.println("   Mana máxima: +" + bonusMana + " (" + manaMaxima + ")");
        System.out.println("   Vida e mana completamente restauradas!");
    }
    
    // ==================== OPERAÇÕES DE ESTADO ====================
    
    /**
     * Verifica se o jogador está vivo
     */
    public boolean estaVivo() {
        return vida > 0;
    }
    
    /**
     * Restaura estado (usado para undo de operações complexas)
     */
    public void restaurarEstado(int x, int y, int vida, int mana, int experiencia, int nivel) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.mana = mana;
        this.experiencia = experiencia;
        this.nivel = nivel;
        
        System.out.println("Estado de " + nome + " restaurado:");
        System.out.println("   Posição: (" + x + ", " + y + ")");
        System.out.println("   Vida: " + vida + "/" + vidaMaxima);
        System.out.println("   Mana: " + mana + "/" + manaMaxima);
        System.out.println("   Experiência: " + experiencia + " (Nível " + nivel + ")");
    }
    
    /**
     * Exibe status completo do jogador
     */
    public void exibirStatus() {
        System.out.println("\n=== STATUS DE " + nome.toUpperCase() + " ===");
        System.out.println("Posição: (" + x + ", " + y + ")");
        System.out.println("Vida: " + vida + "/" + vidaMaxima + " (" + 
                          String.format("%.1f", (vida * 100.0 / vidaMaxima)) + "%)");
        System.out.println("Mana: " + mana + "/" + manaMaxima + " (" + 
                          String.format("%.1f", (mana * 100.0 / manaMaxima)) + "%)");
        System.out.println("Nível: " + nivel + " (Exp: " + experiencia + "/" + (nivel * 100) + ")");
        
        // Barra de vida visual
        System.out.print("Vida: [");
        int barrasVida = (vida * 20) / vidaMaxima;
        for (int i = 0; i < 20; i++) {
            if (i < barrasVida) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("] " + vida + "/" + vidaMaxima);
        
        // Barra de mana visual
        System.out.print("Mana: [");
        int barrasMana = (mana * 20) / manaMaxima;
        for (int i = 0; i < 20; i++) {
            if (i < barrasMana) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("] " + mana + "/" + manaMaxima);
        
        // Status de combate
        String statusVida = vida > (vidaMaxima * 0.7) ? "Saudável" : 
                           vida > (vidaMaxima * 0.3) ? "Ferido" : "Crítico";
        
        System.out.println("Status de Combate: " + statusVida);
        System.out.println("Dano de Ataque: " + (15 + nivel * 2));
        System.out.println("Dano Mágico: " + (25 + nivel * 3) + " (Custo: 15 mana)");
        System.out.println("Cura Disponível: " + (vida < vidaMaxima ? "SIM" : "NÃO"));
        System.out.println("Magia Disponível: " + (mana >= 15 ? "SIM" : "NÃO"));
        System.out.println("Status: " + (estaVivo() ? "VIVO" : "MORTO"));
        
        System.out.println("=" + "=".repeat(30 + nome.length()));
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public String getNome() { return nome; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getMana() { return mana; }
    public int getManaMaxima() { return manaMaxima; }
    public int getExperiencia() { return experiencia; }
    public int getNivel() { return nivel; }
    
    // Setters para operações de undo (usados pelos comandos)
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVida(int vida) { this.vida = vida; }
    public void setMana(int mana) { this.mana = mana; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
    public void setNivel(int nivel) { this.nivel = nivel; }
}