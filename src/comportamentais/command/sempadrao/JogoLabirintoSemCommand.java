package comportamentais.command.sempadrao;

/**
 * Demonstração dos PROBLEMAS de não usar o padrão Command
 * 
 * PROBLEMAS IDENTIFICADOS:
 * PROBLEMA: Acoplamento forte entre interface e lógica de negócio
 * PROBLEMA: Impossibilidade de desfazer operações (undo)
 * PROBLEMA: Impossibilidade de refazer operações (redo)
 * PROBLEMA: Dificuldade para implementar macros/sequências de comandos
 * PROBLEMA: Impossibilidade de enfileirar operações
 * PROBLEMA: Dificuldade para implementar logging de operações
 * PROBLEMA: Violação do princípio da Responsabilidade Única
 * PROBLEMA: Código duplicado para operações similares
 * PROBLEMA: Dificuldade para implementar operações assíncronas
 */
public class JogoLabirintoSemCommand {
    
    public static void main(String[] args) {
        System.out.println("=== JOGO DE LABIRINTO SEM PADRÃO COMMAND ===");
        System.out.println("\nDEMONSTRAÇÃO: Problemas de acoplamento forte e falta de flexibilidade\n");
        
        // Criando jogador e sistema de jogo
        Jogador jogador = new Jogador("Herói", 5, 5);
        SistemaJogo sistema = new SistemaJogo();
        
        // Interface do usuário acoplada diretamente à lógica
        InterfaceUsuario ui = new InterfaceUsuario(jogador, sistema);
        
        System.out.println("Simulando interações do usuário:\n");
        
        // Simulação de comandos do usuário
        ui.processarComando("mover_norte");
        ui.processarComando("mover_leste");
        ui.processarComando("atacar");
        ui.processarComando("usar_item");
        ui.processarComando("mover_sul");
        
        System.out.println("\nPROBLEMAS DEMONSTRADOS:");
        System.out.println("   - Não é possível desfazer movimentos");
        System.out.println("   - Não é possível repetir sequências de ações");
        System.out.println("   - Interface fortemente acoplada à lógica");
        System.out.println("   - Impossível implementar macros");
        System.out.println("   - Difícil adicionar novos comandos");
        System.out.println("   - Sem histórico de operações");
        
        // Tentativa de implementar undo manualmente (problemática)
        System.out.println("\nTentativa problemática de implementar undo:");}]}
        ui.tentarDesfazer(); // Vai mostrar as limitações
    }
}

/**
 * Classe Jogador - Representa o estado do jogador
 */
class Jogador {
    private String nome;
    private int x, y;
    private int vida = 100;
    private int mana = 50;
    
    public Jogador(String nome, int x, int y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
    
    // Métodos de movimento - SEM possibilidade de undo
    public void moverNorte() {
        y++;
        System.out.println("🔼 " + nome + " moveu para o norte. Posição: (" + x + ", " + y + ")");
    }
    
    public void moverSul() {
        y--;
        System.out.println("🔽 " + nome + " moveu para o sul. Posição: (" + x + ", " + y + ")");
    }
    
    public void moverLeste() {
        x++;
        System.out.println("MOVIMENTO: " + nome + " moveu para o leste. Posição: (" + x + ", " + y + ")");
    }
    
    public void moverOeste() {
        x--;
        System.out.println("MOVIMENTO: " + nome + " moveu para o oeste. Posição: (" + x + ", " + y + ")");
    }
    
    public void atacar() {
        System.out.println("ATAQUE: " + nome + " ataca! Dano causado!");
    }
    
    public void usarItem() {
        if (mana >= 10) {
            mana -= 10;
            vida += 20;
            vida = Math.min(100, vida);
            System.out.println("ITEM: " + nome + " usa poção de cura. Vida: " + vida + ", Mana: " + mana);
        } else {
            System.out.println("ERRO: Mana insuficiente para usar item!");
        }
    }
    
    // Getters
    public String getNome() { return nome; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVida() { return vida; }
    public int getMana() { return mana; }
    
    // Setters para tentativas de undo (problemático)
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVida(int vida) { this.vida = vida; }
    public void setMana(int mana) { this.mana = mana; }
}

/**
 * Sistema de jogo - Gerencia estado global
 */
class SistemaJogo {
    private int pontuacao = 0;
    private int turno = 0;
    
    public void adicionarPontos(int pontos) {
        pontuacao += pontos;
        System.out.println("PONTUAÇÃO: +" + pontos + " (Total: " + pontuacao + ")");
    }
    
    public void proximoTurno() {
        turno++;
        System.out.println("TURNO: " + turno + " iniciado");
    }
    
    public void salvarJogo() {
        System.out.println("SAVE: Jogo salvo! Turno: " + turno + ", Pontuação: " + pontuacao);
    }
    
    // Getters
    public int getPontuacao() { return pontuacao; }
    public int getTurno() { return turno; }
}

/**
 * Interface do usuário - FORTEMENTE ACOPLADA à lógica de negócio
 * 
 * PROBLEMAS DESTA ABORDAGEM:
 * PROBLEMA: Método gigante com switch/if complexo
     * PROBLEMA: Acoplamento direto com classes de domínio
     * PROBLEMA: Impossível implementar undo/redo
     * PROBLEMA: Difícil adicionar novos comandos
     * PROBLEMA: Sem possibilidade de enfileirar comandos
     * PROBLEMA: Sem logging automático de operações
 */
class InterfaceUsuario {
    private Jogador jogador;
    private SistemaJogo sistema;
    
    // Tentativa problemática de manter histórico
    private String ultimoComando = "";
    private int ultimoX, ultimoY, ultimaVida, ultimaMana;
    
    public InterfaceUsuario(Jogador jogador, SistemaJogo sistema) {
        this.jogador = jogador;
        this.sistema = sistema;
    }
    
    /**
     * Método problemático que viola vários princípios SOLID
     * 
     * PROBLEMAS:
     * - Responsabilidade Única: faz muitas coisas
     * - Aberto/Fechado: precisa modificar para adicionar comandos
     * - Dependência: acoplado a múltiplas classes
     */
    public void processarComando(String comando) {
        // Salva estado atual para tentativa de undo (problemático)
        salvarEstadoAtual();
        
        // ESTRUTURA CONDICIONAL GIGANTE - ANTI-PATTERN!
        switch (comando.toLowerCase()) {
            case "mover_norte":
                jogador.moverNorte();
                sistema.adicionarPontos(1);
                sistema.proximoTurno();
                ultimoComando = comando;
                break;
                
            case "mover_sul":
                jogador.moverSul();
                sistema.adicionarPontos(1);
                sistema.proximoTurno();
                ultimoComando = comando;
                break;
                
            case "mover_leste":
                jogador.moverLeste();
                sistema.adicionarPontos(1);
                sistema.proximoTurno();
                ultimoComando = comando;
                break;
                
            case "mover_oeste":
                jogador.moverOeste();
                sistema.adicionarPontos(1);
                sistema.proximoTurno();
                ultimoComando = comando;
                break;
                
            case "atacar":
                jogador.atacar();
                sistema.adicionarPontos(10);
                sistema.proximoTurno();
                ultimoComando = comando;
                break;
                
            case "usar_item":
                jogador.usarItem();
                sistema.proximoTurno();
                ultimoComando = comando;
                break;
                
            case "salvar":
                sistema.salvarJogo();
                ultimoComando = comando;
                break;
                
            default:
                System.out.println("ERRO: Comando desconhecido: " + comando);
                break;
        }
        
        // PROBLEMA: Código duplicado para logging
        if (!comando.equals("salvar")) {
            System.out.println("📝 Comando executado: " + comando);
        }
    }
    
    /**
     * Tentativa problemática de implementar undo
     * 
     * PROBLEMAS:
     * PROBLEMA: Só funciona para o último comando
     * PROBLEMA: Não funciona para todos os tipos de comando
     * PROBLEMA: Estado pode ficar inconsistente
     * PROBLEMA: Lógica complexa e propensa a erros
     * PROBLEMA: Não escalável
     */
    public void tentarDesfazer() {
        System.out.println("\nTentando desfazer último comando: " + ultimoComando);
        
        if (ultimoComando.isEmpty()) {
            System.out.println("ERRO: Nenhum comando para desfazer!");
            return;
        }
        
        // LÓGICA COMPLEXA E PROPENSA A ERROS
        switch (ultimoComando.toLowerCase()) {
            case "mover_norte":
                System.out.println("Tentando desfazer movimento norte...");
                jogador.setY(ultimoY); // Problemático: acesso direto ao estado
                System.out.println("PROBLEMA: Como desfazer pontuação e turno?");
                break;
                
            case "mover_sul":
                System.out.println("Tentando desfazer movimento sul...");
            jogador.setY(ultimoY);
            System.out.println("PROBLEMA: Estado do sistema inconsistente!");
                break;
                
            case "atacar":
                System.out.println("ERRO: IMPOSSÍVEL desfazer ataque - efeito já aplicado!");
                break;
                
            case "usar_item":
                System.out.println("Tentando desfazer uso de item...");
                jogador.setVida(ultimaVida);
                jogador.setMana(ultimaMana);
                System.out.println("PROBLEMA: Item já foi consumido!");
                break;
                
            default:
                System.out.println("ERRO: Não sei como desfazer: " + ultimoComando);
                break;
        }
        
        System.out.println("\nPROBLEMAS DO UNDO MANUAL:");
        System.out.println("   - Só funciona para o último comando");
        System.out.println("   - Estado pode ficar inconsistente");
        System.out.println("   - Lógica complexa e propensa a erros");
        System.out.println("   - Não escalável para múltiplos undos");
        System.out.println("   - Difícil manter sincronização entre objetos");
    }
    
    /**
     * Salva estado atual - abordagem problemática
     */
    private void salvarEstadoAtual() {
        ultimoX = jogador.getX();
        ultimoY = jogador.getY();
        ultimaVida = jogador.getVida();
        ultimaMana = jogador.getMana();
        
        // PROBLEMA: E o estado do sistema? E outros objetos?
        // Esta abordagem não escala!
    }
}

/**
 * RESUMO DOS PROBLEMAS SEM COMMAND:
 * 
 * 1. ACOPLAMENTO FORTE:
 *    - Interface diretamente acoplada à lógica de negócio
 *    - Mudanças em uma classe afetam outras
 * 
 * 2. VIOLAÇÃO DE PRINCÍPIOS SOLID:
 *    - SRP: InterfaceUsuario faz muitas coisas
 *    - OCP: Precisa modificar código para adicionar comandos
 *    - DIP: Depende de implementações concretas
 * 
 * 3. FALTA DE FLEXIBILIDADE:
 *    - Impossível implementar undo/redo eficiente
 *    - Impossível enfileirar comandos
 *    - Impossível implementar macros
 *    - Impossível logging automático
 * 
 * 4. MANUTENIBILIDADE:
 *    - Código duplicado
 *    - Estruturas condicionais complexas
 *    - Difícil adicionar novos comandos
 *    - Difícil testar isoladamente
 * 
 * 5. ESCALABILIDADE:
 *    - Não suporta operações assíncronas
 *    - Não suporta priorização de comandos
 *    - Não suporta cancelamento de operações
 */