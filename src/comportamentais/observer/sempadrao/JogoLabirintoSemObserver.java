package comportamentais.observer.sempadrao;

/**
 * Demonstração do problema SEM usar o padrão Observer
 * 
 * PROBLEMA: O código está fortemente acoplado. Quando o jogador se move,
 * precisamos manualmente notificar cada sistema interessado (mapa, pontuação, log).
 * Se quisermos adicionar novos sistemas, precisamos modificar a classe Jogador.
 * 
 * VIOLAÇÕES:
 * - Princípio Aberto/Fechado: Jogador precisa ser modificado para cada novo observador
 * - Responsabilidade Única: Jogador conhece todos os sistemas que devem ser notificados
 * - Baixo acoplamento: Jogador está acoplado a classes específicas
 */
public class JogoLabirintoSemObserver {
    
    public static void main(String[] args) {
        System.out.println("=== JOGO DE LABIRINTO SEM OBSERVER ===");
        System.out.println("Problema: Acoplamento forte entre Jogador e sistemas\n");
        
        // Criando os sistemas
        Mapa mapa = new Mapa();
        SistemaPontuacao pontuacao = new SistemaPontuacao();
        LogMovimentos log = new LogMovimentos();
        
        // Criando o jogador e passando TODAS as dependências
        Jogador jogador = new Jogador("Herói", mapa, pontuacao, log);
        
        // Simulando movimentos
        jogador.mover("Norte");
        jogador.mover("Leste");
        jogador.mover("Sul");
        
        System.out.println("\n=== ESTADO FINAL ===");
        mapa.mostrarStatus();
        pontuacao.mostrarPontuacao();
        log.mostrarHistorico();
    }
}

/**
 * Classe Jogador ACOPLADA aos sistemas específicos
 * 
 * PROBLEMA: Esta classe conhece e depende diretamente de:
 * - Mapa
 * - SistemaPontuacao  
 * - LogMovimentos
 * 
 * Para adicionar um novo sistema (ex: SistemaAudio), seria necessário:
 * 1. Modificar o construtor
 * 2. Adicionar um novo atributo
 * 3. Modificar o método mover()
 */
class Jogador {
    private String nome;
    private int x, y;
    
    // ACOPLAMENTO FORTE: Jogador conhece classes específicas
    private Mapa mapa;
    private SistemaPontuacao pontuacao;
    private LogMovimentos log;
    
    /**
     * Construtor que recebe TODAS as dependências
     * PROBLEMA: Cada novo sistema requer modificação aqui
     */
    public Jogador(String nome, Mapa mapa, SistemaPontuacao pontuacao, LogMovimentos log) {
        this.nome = nome;
        this.x = 0;
        this.y = 0;
        this.mapa = mapa;
        this.pontuacao = pontuacao;
        this.log = log;
        
        System.out.println("Jogador " + nome + " criado na posição (0,0)");
    }
    
    /**
     * Método que move o jogador
     * PROBLEMA: Precisa notificar manualmente cada sistema
     */
    public void mover(String direcao) {
        // Atualiza posição baseada na direção
        switch (direcao.toLowerCase()) {
            case "norte": y++; break;
            case "sul": y--; break;
            case "leste": x++; break;
            case "oeste": x--; break;
        }
        
        System.out.println(nome + " moveu para " + direcao + " -> (" + x + "," + y + ")");
        
        // PROBLEMA: Notificação manual e acoplada
        // Se quisermos adicionar SistemaAudio, teríamos que modificar aqui
        mapa.atualizarPosicaoJogador(x, y);
        pontuacao.adicionarPontos(10); // 10 pontos por movimento
        log.registrarMovimento(direcao, x, y);
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public String getNome() { return nome; }
}

/**
 * Sistema que gerencia o mapa
 */
class Mapa {
    private int jogadorX, jogadorY;
    
    public void atualizarPosicaoJogador(int x, int y) {
        this.jogadorX = x;
        this.jogadorY = y;
        System.out.println("  [MAPA] Posição atualizada: (" + x + "," + y + ")");
    }
    
    public void mostrarStatus() {
        System.out.println("[MAPA] Jogador está na posição: (" + jogadorX + "," + jogadorY + ")");
    }
}

/**
 * Sistema de pontuação
 */
class SistemaPontuacao {
    private int pontos = 0;
    
    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
        System.out.println("  [PONTUAÇÃO] +" + pontos + " pontos (Total: " + this.pontos + ")");
    }
    
    public void mostrarPontuacao() {
        System.out.println("[PONTUAÇÃO] Total de pontos: " + pontos);
    }
}

/**
 * Sistema de log de movimentos
 */
class LogMovimentos {
    private StringBuilder historico = new StringBuilder();
    
    public void registrarMovimento(String direcao, int x, int y) {
        String movimento = direcao + "(" + x + "," + y + ")";
        historico.append(movimento).append(" -> ");
        System.out.println("  [LOG] Movimento registrado: " + movimento);
    }
    
    public void mostrarHistorico() {
        System.out.println("[LOG] Histórico: " + historico.toString());
    }
}