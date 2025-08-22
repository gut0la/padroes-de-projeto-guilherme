package comportamentais.command.compadrao.commands;

import comportamentais.command.compadrao.interfaces.Command;
import comportamentais.command.compadrao.classes.*;

/**
 * Comandos de ação - CONCRETE COMMAND no padrão Command
 * 
 * PADRÃO COMMAND - CONCRETE COMMAND:
 * Estes comandos encapsulam ações do jogador como atacar, usar itens,
 * salvar jogo, etc. Cada comando mantém estado necessário para undo
 * quando possível.
 */
public abstract class ComandoAcao implements Command {
    
    protected Jogador jogador;
    protected SistemaJogo sistema;
    protected long timestamp;
    protected boolean executado = false;
    
    public ComandoAcao(Jogador jogador, SistemaJogo sistema) {
        this.jogador = jogador;
        this.sistema = sistema;
        this.timestamp = System.currentTimeMillis();
    }
    
    @Override
    public long getTimestamp() {
        return timestamp;
    }
}

/**
 * Comando para atacar
 */
class ComandoAtacar extends ComandoAcao {
    
    private int pontuacaoAnterior;
    private int turnoAnterior;
    private int danoGerado;
    
    public ComandoAtacar(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        if (!jogador.estaVivo()) {
            System.out.println("ERRO: " + jogador.getNome() + " não pode atacar - está morto!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        // Salva estado para undo
        pontuacaoAnterior = sistema.getPontuacao();
        turnoAnterior = sistema.getTurno();
        
        // Executa ataque
        danoGerado = jogador.atacar();
        
        // Atualiza sistema
        sistema.adicionarPontos(10);
        sistema.incrementarEstatistica("ataques", 1);
        sistema.incrementarEstatistica("dano_causado", danoGerado);
        sistema.proximoTurno();
        
        executado = true;
        System.out.println("SUCESSO: Ataque executado com sucesso!");
        return true;
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("AVISO: Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        System.out.println("ATENÇÃO: Dano já foi causado e não pode ser revertido!");
        System.out.println("   Revertendo apenas pontuação e estatísticas...");
        
        // Reverte apenas o que é possível
        sistema.setPontuacao(pontuacaoAnterior);
        sistema.setTurno(turnoAnterior);
        sistema.setEstatistica("ataques", sistema.getEstatistica("ataques") - 1);
        sistema.setEstatistica("dano_causado", sistema.getEstatistica("dano_causado") - danoGerado);
        
        executado = false;
        System.out.println("SUCESSO: Comando parcialmente desfeito!");
        return true;
    }
    
    @Override
    public boolean podeSerDesfeito() {
        return true; // Parcialmente - não pode reverter dano causado
    }
    
    @Override
    public String getDescricao() {
        return jogador.getNome() + " executa ataque";
    }
}

/**
 * Comando para ataque mágico
 */
class ComandoAtaqueMagico extends ComandoAcao {
    
    private int pontuacaoAnterior;
    private int turnoAnterior;
    private int manaAnterior;
    private int danoGerado;
    private boolean manaConsumida = false;
    
    public ComandoAtaqueMagico(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        if (!jogador.estaVivo()) {
            System.out.println("ERRO: " + jogador.getNome() + " não pode atacar - está morto!");
            return false;
        }
        
        if (jogador.getMana() < 15) {
            System.out.println("ERRO: Mana insuficiente para ataque mágico!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        // Salva estado para undo
        pontuacaoAnterior = sistema.getPontuacao();
        turnoAnterior = sistema.getTurno();
        manaAnterior = jogador.getMana();
        
        // Executa ataque mágico
        danoGerado = jogador.atacarMagico();
        
        if (danoGerado > 0) {
            manaConsumida = true;
            
            // Atualiza sistema
            sistema.adicionarPontos(15);
            sistema.incrementarEstatistica("ataques", 1);
            sistema.incrementarEstatistica("dano_causado", danoGerado);
            sistema.proximoTurno();
            
            executado = true;
            System.out.println("SUCESSO: Ataque mágico executado com sucesso!");
            return true;
        } else {
            System.out.println("ERRO: Falha no ataque mágico!");
            return false;
        }
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("AVISO: Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        
        // Restaura mana
        if (manaConsumida) {
            jogador.setMana(manaAnterior);
            System.out.println("Mana restaurada: " + manaAnterior);
        }
        
        // Reverte sistema
        sistema.setPontuacao(pontuacaoAnterior);
        sistema.setTurno(turnoAnterior);
        sistema.setEstatistica("ataques", sistema.getEstatistica("ataques") - 1);
        sistema.setEstatistica("dano_causado", sistema.getEstatistica("dano_causado") - danoGerado);
        
        executado = false;
        System.out.println("SUCESSO: Ataque mágico desfeito com sucesso!");
        return true;
    }
    
    @Override
    public boolean podeSerDesfeito() {
        return true;
    }
    
    @Override
    public String getDescricao() {
        return jogador.getNome() + " executa ataque mágico";
    }
}

/**
 * Comando para usar poção de cura
 */
class ComandoUsarPocaoCura extends ComandoAcao {
    
    private int turnoAnterior;
    private int vidaAnterior;
    private int curaRealizada;
    
    public ComandoUsarPocaoCura(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("Comando já foi executado!");
            return false;
        }
        
        if (!jogador.estaVivo()) {
            System.out.println(" Aviso " + jogador.getNome() + " não pode usar itens - está morto!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        // Salva estado para undo
        turnoAnterior = sistema.getTurno();
        vidaAnterior = jogador.getVida();
        
        // Usa poção
        curaRealizada = jogador.usarPocaoCura();
        
        if (curaRealizada > 0) {
            // Atualiza sistema
            sistema.incrementarEstatistica("itens_usados", 1);
            sistema.proximoTurno();
            
            executado = true;
            System.out.println(" Poção de cura usada com sucesso!");
            return true;
        } else {
            System.out.println("  Poção não teve efeito (vida já estava máxima)");
            return false;
        }
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println("Comando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        System.out.println("ATENÇÃO: Poção já foi consumida!");
        System.out.println("   Revertendo apenas efeitos da cura...");
        
        // Reverte vida
        jogador.setVida(vidaAnterior);
        
        // Reverte sistema
        sistema.setTurno(turnoAnterior);
        sistema.setEstatistica("itens_usados", sistema.getEstatistica("itens_usados") - 1);
        
        executado = false;
        System.out.println("Efeitos da poção revertidos!");
        return true;
    }
    
    @Override
    public boolean podeSerDesfeito() {
        return true; // Pode reverter efeitos, mas não recupera a poção
    }
    
    @Override
    public String getDescricao() {
        return jogador.getNome() + " usa poção de cura";
    }
}

/**
 * Comando para usar poção de mana
 */
class ComandoUsarPocaoMana extends ComandoAcao {
    
    private int turnoAnterior;
    private int manaAnterior;
    private int restauracaoRealizada;
    
    public ComandoUsarPocaoMana(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("Comando já foi executado!");
            return false;
        }
        
        if (!jogador.estaVivo()) {
            System.out.println("Erro " + jogador.getNome() + " não pode usar itens - está morto!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        // Salva estado para undo
        turnoAnterior = sistema.getTurno();
        manaAnterior = jogador.getMana();
        
        // Usa poção
        restauracaoRealizada = jogador.usarPocaoMana();
        
        if (restauracaoRealizada > 0) {
            // Atualiza sistema
            sistema.incrementarEstatistica("itens_usados", 1);
            sistema.proximoTurno();
            
            executado = true;
            System.out.println("Poção de mana usada com sucesso!");
            return true;
        } else {
            System.out.println("Poção não teve efeito (mana já estava máxima)");
            return false;
        }
    }
    
    @Override
    public boolean desfazer() {
        if (!executado) {
            System.out.println(" omando não foi executado ainda!");
            return false;
        }
        
        System.out.println("\nDesfazendo: " + getDescricao());
        
        // Reverte mana
        jogador.setMana(manaAnterior);
        
        // Reverte sistema
        sistema.setTurno(turnoAnterior);
        sistema.setEstatistica("itens_usados", sistema.getEstatistica("itens_usados") - 1);
        
        executado = false;
        System.out.println("SUCESSO: Efeitos da poção revertidos!");
        return true;
    }
    
    @Override
    public boolean podeSerDesfeito() {
        return true;
    }
    
    @Override
    public String getDescricao() {
        return jogador.getNome() + " usa poção de mana";
    }
}

/**
 * Comando para salvar jogo
 */
class ComandoSalvarJogo extends ComandoAcao {
    
    public ComandoSalvarJogo(Jogador jogador, SistemaJogo sistema) {
        super(jogador, sistema);
    }
    
    @Override
    public boolean executar() {
        if (executado) {
            System.out.println("AVISO: Comando já foi executado!");
            return false;
        }
        
        System.out.println("\nExecutando: " + getDescricao());
        
        // Executa save
        boolean sucesso = sistema.salvarJogo();
        
        if (sucesso) {
            executado = true;
            System.out.println("SUCESSO: Jogo salvo com sucesso!");
            return true;
        } else {
            System.out.println("ERRO: Falha ao salvar jogo!");
            return false;
        }
    }
    
    @Override
    public boolean desfazer() {
        System.out.println("\nTentando desfazer: " + getDescricao());
        System.out.println("ERRO: IMPOSSÍVEL desfazer save - arquivo já foi criado!");
        System.out.println("   Operações de I/O não podem ser revertidas.");
        return false;
    }
    
    @Override
    public boolean podeSerDesfeito() {
        return false; // Operações de I/O não podem ser desfeitas
    }
    
    @Override
    public String getDescricao() {
        return "Salvar jogo atual";
    }
}

/**
 * Factory para criar comandos de ação
 */
class AcaoCommandFactory {
    
    public static Command criarComandoAcao(String acao, Jogador jogador, SistemaJogo sistema) {
        switch (acao.toLowerCase()) {
            case "atacar":
            case "ataque":
            case "attack":
                return new ComandoAtacar(jogador, sistema);
                
            case "magia":
            case "ataque_magico":
            case "magic":
                return new ComandoAtaqueMagico(jogador, sistema);
                
            case "cura":
            case "pocao_cura":
            case "heal":
                return new ComandoUsarPocaoCura(jogador, sistema);
                
            case "mana":
            case "pocao_mana":
            case "restore_mana":
                return new ComandoUsarPocaoMana(jogador, sistema);
                
            case "salvar":
            case "save":
                return new ComandoSalvarJogo(jogador, sistema);
                
            default:
                throw new IllegalArgumentException("Ação inválida: " + acao);
        }
    }
    
    /**
     * Lista ações disponíveis
     */
    public static String[] getAcoesDisponiveis() {
        return new String[]{"atacar", "magia", "cura", "mana", "salvar"};
    }
}