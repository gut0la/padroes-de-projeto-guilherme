package comportamentais.command.compadrao.commands;

import comportamentais.command.compadrao.interfaces.Command;
import comportamentais.command.compadrao.classes.*;

/**
 * Comandos de movimento - CONCRETE COMMAND no padrão Command
 * 
 * PADRÃO COMMAND - CONCRETE COMMAND:
 * Estas classes implementam a interface Command e definem uma ligação
 * entre um objeto Receiver e uma ação. Cada comando encapsula uma
 * requisição específica e mantém estado necessário para undo.
 * 
 * RESPONSABILIDADES:
 * - Implementar método executar() que chama operações no Receiver
 * - Implementar método desfazer() que reverte a operação
 * - Manter estado necessário para undo
 * - Fornecer descrição da operação
 */
public abstract class ComandoMovimento implements Command {
    
    protected Jogador jogador;
    protected SistemaJogo sistema;
    protected long timestamp;
    
    // Estado para undo
    protected int posicaoAnteriorX;
    protected int posicaoAnteriorY;
    protected int pontuacaoAnterior;
    protected int turnoAnterior;
    protected boolean executado = false;
    
    /**
     * Construtor base para comandos de movimento
     */
    public ComandoMovimento(Jogador jogador, SistemaJogo sistema) {
        this.jogador = jogador;
        this.sistema = sistema;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * Salva estado atual antes da execução
     */
    protected void salvarEstado() {
        posicaoAnteriorX = jogador.getX();
        posicaoAnteriorY = jogador.getY();
        pontuacaoAnterior = sistema.getPontuacao();
        turnoAnterior = sistema.getTurno();
    }
    
    /**
     * Restaura estado anterior
     */
    protected void restaurarEstado() {
        jogador.moverPara(posicaoAnteriorX, posicaoAnteriorY);
        sistema.setPontuacao(pontuacaoAnterior);
        sistema.setTurno(turnoAnterior);
    }
    
    @Override
    public boolean podeSerDesfeito() {
        return true; // Todos os movimentos podem ser desfeitos
    }
    
    @Override
    public long getTimestamp() {
        return timestamp;
    }
}

/**
 * Comando para mover para o norte
 */
class ComandoMoverNorte extends ComandoMovimento {
    
    public ComandoMoverNorte(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        // Salva estado para undo
        salvarEstado();
        
        // Executa operações no Receiver
        jogador.moverNorte();
        sistema.adicionarPontos(1);
        sistema.incrementarEstatistica("movimentos", 1);
        sistema.proximoTurno();
        
        executado = true;
        System.out.println("SUCESSO: Comando executado com sucesso!");
        return true;
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("AVISO: Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        
        // Restaura estado anterior
        restaurarEstado();
        
        executado = false;
        System.out.println("SUCESSO: Comando desfeito com sucesso!");
        return true;
    }
    
    @Override
    public String getDescricao() {
        return "Mover " + jogador.getNome() + " para o norte";
    }
}

/**
 * Comando para mover para o sul
 */
class ComandoMoverSul extends ComandoMovimento {
    
    public ComandoMoverSul(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        salvarEstado();
        
        jogador.moverSul();
        sistema.adicionarPontos(1);
        sistema.incrementarEstatistica("movimentos", 1);
        sistema.proximoTurno();
        
        executado = true;
        System.out.println("SUCESSO: Comando executado com sucesso!");
        return true;
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("AVISO: Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        
        restaurarEstado();
        
        executado = false;
        System.out.println("SUCESSO: Comando desfeito com sucesso!");
        return true;
    }
    
    @Override
    public String getDescricao() {
        return "Mover " + jogador.getNome() + " para o sul";
    }
}

/**
 * Comando para mover para o leste
 */
class ComandoMoverLeste extends ComandoMovimento {
    
    public ComandoMoverLeste(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        salvarEstado();
        
        jogador.moverLeste();
        sistema.adicionarPontos(1);
        sistema.incrementarEstatistica("movimentos", 1);
        sistema.proximoTurno();
        
        executado = true;
        System.out.println("SUCESSO: Comando executado com sucesso!");
        return true;
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("AVISO: Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        
        restaurarEstado();
        
        executado = false;
        System.out.println("SUCESSO: Comando desfeito com sucesso!");
        return true;
    }
    
    @Override
    public String getDescricao() {
        return "Mover " + jogador.getNome() + " para o leste";
    }
}

/**
 * Comando para mover para o oeste
 */
class ComandoMoverOeste extends ComandoMovimento {
    
    public ComandoMoverOeste(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        salvarEstado();
        
        jogador.moverOeste();
        sistema.adicionarPontos(1);
        sistema.incrementarEstatistica("movimentos", 1);
        sistema.proximoTurno();
        
        executado = true;
        System.out.println("SUCESSO: Comando executado com sucesso!");
        return true;
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("AVISO: Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        
        restaurarEstado();
        
        executado = false;
        System.out.println("SUCESSO: Comando desfeito com sucesso!");
        return true;
    }
    
    @Override
    public String getDescricao() {
        return "Mover " + jogador.getNome() + " para o oeste";
    }
}

/**
 * Factory para criar comandos de movimento
 * 
 * PADRÃO FACTORY + COMMAND:
 * Simplifica a criação de comandos e esconde detalhes de implementação
 */
class MovimentoCommandFactory {
    
    public static Command criarComandoMovimento(String direcao, Jogador jogador, SistemaJogo sistema) {
        switch (direcao.toLowerCase()) {
            case "norte":
            case "n":
            case "up":
                return new ComandoMoverNorte(jogador, sistema);
                
            case "sul":
            case "s":
            case "down":
                return new ComandoMoverSul(jogador, sistema);
                
            case "leste":
            case "l":
            case "east":
            case "right":
                return new ComandoMoverLeste(jogador, sistema);
                
            case "oeste":
            case "o":
            case "west":
            case "left":
                return new ComandoMoverOeste(jogador, sistema);
                
            default:
                throw new IllegalArgumentException("Direção inválida: " + direcao);
        }
    }
    
    /**
     * Lista direções disponíveis
     */
    public static String[] getDirecoesDisponiveis() {
        return new String[]{"norte", "sul", "leste", "oeste"};
    }
}