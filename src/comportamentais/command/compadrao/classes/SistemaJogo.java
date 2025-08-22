package comportamentais.command.compadrao.classes;

import java.util.*;

/**
 * Classe SistemaJogo - RECEIVER no padrão Command
 * 
 * PADRÃO COMMAND - RECEIVER:
 * Esta classe gerencia o estado global do jogo e fornece operações
 * que podem ser encapsuladas em comandos. Como Receiver, ela contém
 * a lógica de negócio real para operações do sistema de jogo.
 * 
 * RESPONSABILIDADES:
 * - Gerenciar pontuação do jogo
 * - Controlar turnos e tempo
 * - Manter histórico de eventos
 * - Gerenciar save/load do jogo
 * - Fornecer operações que comandos podem executar
 */
public class SistemaJogo {
    
    private int pontuacao;
    private int turno;
    private long tempoInicio;
    private boolean jogoAtivo;
    private String nomeJogo;
    
    // Histórico de eventos para auditoria
    private List<String> historicoEventos;
    private Map<String, Integer> estatisticas;
    
    /**
     * Construtor do sistema de jogo
     */
    public SistemaJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
        this.pontuacao = 0;
        this.turno = 0;
        this.tempoInicio = System.currentTimeMillis();
        this.jogoAtivo = true;
        this.historicoEventos = new ArrayList<>();
        this.estatisticas = new HashMap<>();
        
        inicializarEstatisticas();
        registrarEvento("Jogo iniciado: " + nomeJogo);
    }
    
    /**
     * Inicializa estatísticas do jogo
     */
    private void inicializarEstatisticas() {
        estatisticas.put("movimentos", 0);
        estatisticas.put("ataques", 0);
        estatisticas.put("itens_usados", 0);
        estatisticas.put("dano_causado", 0);
        estatisticas.put("dano_recebido", 0);
        estatisticas.put("saves_realizados", 0);
    }
    
    // ==================== OPERAÇÕES DE PONTUAÇÃO ====================
    
    /**
     * Adiciona pontos à pontuação
     * 
     * RECEIVER OPERATION:
     * Esta operação pode ser encapsulada em um comando que permite undo.
     */
    public void adicionarPontos(int pontos) {
        int pontuacaoAnterior = this.pontuacao;
        this.pontuacao += pontos;
        
        String evento = "Pontos adicionados: +" + pontos + " (" + pontuacaoAnterior + " → " + this.pontuacao + ")";
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Remove pontos da pontuação
     */
    public void removerPontos(int pontos) {
        int pontuacaoAnterior = this.pontuacao;
        this.pontuacao = Math.max(0, this.pontuacao - pontos);
        
        String evento = "Pontos removidos: -" + pontos + " (" + pontuacaoAnterior + " → " + this.pontuacao + ")";
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Define pontuação específica (usado para undo)
     */
    public void setPontuacao(int pontuacao) {
        int pontuacaoAnterior = this.pontuacao;
        this.pontuacao = pontuacao;
        
        String evento = "Pontuação definida: " + pontuacaoAnterior + " → " + pontuacao;
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    // ==================== OPERAÇÕES DE TURNO ====================
    
    /**
     * Avança para o próximo turno
     */
    public void proximoTurno() {
        turno++;
        String evento = "Turno " + turno + " iniciado";
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Volta para o turno anterior (usado para undo)
     */
    public void turnoAnterior() {
        if (turno > 0) {
            turno--;
            String evento = "Voltou para turno " + turno;
            registrarEvento(evento);
            System.out.println(evento);
        }
    }
    
    /**
     * Define turno específico (usado para undo)
     */
    public void setTurno(int turno) {
        int turnoAnterior = this.turno;
        this.turno = turno;
        
        String evento = "Turno definido: " + turnoAnterior + " → " + turno;
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    // ==================== OPERAÇÕES DE ESTATÍSTICAS ====================
    
    /**
     * Incrementa uma estatística
     */
    public void incrementarEstatistica(String chave, int valor) {
        int valorAnterior = estatisticas.getOrDefault(chave, 0);
        estatisticas.put(chave, valorAnterior + valor);
        
        String evento = "Estatística " + chave + ": +" + valor + " (" + valorAnterior + " → " + (valorAnterior + valor) + ")";
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Define valor específico de uma estatística (usado para undo)
     */
    public void setEstatistica(String chave, int valor) {
        int valorAnterior = estatisticas.getOrDefault(chave, 0);
        estatisticas.put(chave, valor);
        
        String evento = "Estatística " + chave + " definida: " + valorAnterior + " → " + valor;
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Obtém valor de uma estatística
     */
    public int getEstatistica(String chave) {
        return estatisticas.getOrDefault(chave, 0);
    }
    
    // ==================== OPERAÇÕES DE SAVE/LOAD ====================
    
    /**
     * Salva o jogo
     */
    public boolean salvarJogo() {
        try {
            // Simula operação de save
            Thread.sleep(100); // Simula tempo de I/O
            
            incrementarEstatistica("saves_realizados", 1);
            String evento = "Jogo salvo - Turno: " + turno + ", Pontuação: " + pontuacao;
            registrarEvento(evento);
            System.out.println(evento);
            
            return true;
        } catch (InterruptedException e) {
            System.out.println("Erro ao salvar jogo: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Carrega o jogo
     */
    public boolean carregarJogo(int turnoSalvo, int pontuacaoSalva) {
        try {
            // Simula operação de load
            Thread.sleep(100); // Simula tempo de I/O
            
            int turnoAnterior = this.turno;
            int pontuacaoAnterior = this.pontuacao;
            
            this.turno = turnoSalvo;
            this.pontuacao = pontuacaoSalva;
            
            String evento = "Jogo carregado - Turno: " + turnoAnterior + " → " + turno + 
                           ", Pontuação: " + pontuacaoAnterior + " → " + pontuacao;
            registrarEvento(evento);
            System.out.println(evento);
            
            return true;
        } catch (InterruptedException e) {
            System.out.println("Erro ao carregar jogo: " + e.getMessage());
            return false;
        }
    }
    
    // ==================== OPERAÇÕES DE CONTROLE ====================
    
    /**
     * Pausa o jogo
     */
    public void pausarJogo() {
        jogoAtivo = false;
        String evento = "Jogo pausado";
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Resume o jogo
     */
    public void resumirJogo() {
        jogoAtivo = true;
        String evento = "Jogo resumido";
        registrarEvento(evento);
        System.out.println(evento);
    }
    
    /**
     * Finaliza o jogo
     */
    public void finalizarJogo() {
        jogoAtivo = false;
        long tempoTotal = System.currentTimeMillis() - tempoInicio;
        
        String evento = "Jogo finalizado após " + (tempoTotal / 1000) + " segundos";
        registrarEvento(evento);
        System.out.println(evento);
        
        exibirResumoFinal();
    }
    
    // ==================== OPERAÇÕES DE HISTÓRICO ====================
    
    /**
     * Registra um evento no histórico
     */
    private void registrarEvento(String evento) {
        String timestamp = new Date().toString();
        String eventoCompleto = "[" + timestamp + "] " + evento;
        historicoEventos.add(eventoCompleto);
    }
    
    /**
     * Exibe histórico de eventos
     */
    public void exibirHistorico() {
        System.out.println("\n=== HISTÓRICO DE EVENTOS ===");
        
        if (historicoEventos.isEmpty()) {
            System.out.println("Nenhum evento registrado.");
            return;
        }
        
        // Exibe últimos 10 eventos
        int inicio = Math.max(0, historicoEventos.size() - 10);
        for (int i = inicio; i < historicoEventos.size(); i++) {
            System.out.println((i + 1) + ". " + historicoEventos.get(i));
        }
        
        if (historicoEventos.size() > 10) {
            System.out.println("... (" + (historicoEventos.size() - 10) + " eventos anteriores)");
        }
        
        System.out.println("=" + "=".repeat(30));
    }
    
    /**
     * Limpa histórico de eventos
     */
    public void limparHistorico() {
        historicoEventos.clear();
        registrarEvento("Histórico limpo");
        System.out.println("Histórico de eventos limpo");
    }
    
    // ==================== OPERAÇÕES DE RELATÓRIO ====================
    
    /**
     * Exibe status atual do sistema
     */
    public void exibirStatus() {
        System.out.println("\n=== STATUS DO SISTEMA DE JOGO ===");
        System.out.println("Nome: " + nomeJogo);
        System.out.println("Pontuação: " + pontuacao);
        System.out.println("Turno: " + turno);
        System.out.println("Status: " + (jogoAtivo ? "Ativo" : "Pausado"));
        
        long tempoDecorrido = System.currentTimeMillis() - tempoInicio;
        System.out.println("Tempo decorrido: " + (tempoDecorrido / 1000) + " segundos");
        
        System.out.println("\nESTATÍSTICAS:");
        estatisticas.forEach((chave, valor) -> {
            System.out.println("   " + formatarChave(chave) + ": " + valor);
        });
        
        System.out.println("\nEventos registrados: " + historicoEventos.size());
        System.out.println("=" + "=".repeat(35));
    }
    
    /**
     * Exibe resumo final do jogo
     */
    private void exibirResumoFinal() {
        System.out.println("\n=== RESUMO FINAL DO JOGO ===");
        System.out.println("Jogo: " + nomeJogo);
        System.out.println("Pontuação final: " + pontuacao);
        System.out.println("Turnos jogados: " + turno);
        
        long tempoTotal = System.currentTimeMillis() - tempoInicio;
        System.out.println("Tempo total: " + (tempoTotal / 1000) + " segundos");
        
        System.out.println("\nESTATÍSTICAS FINAIS:");
        estatisticas.forEach((chave, valor) -> {
            System.out.println("   " + formatarChave(chave) + ": " + valor);
        });
        
        // Calcula algumas métricas
        if (turno > 0) {
            double pontosPorTurno = (double) pontuacao / turno;
            System.out.println("\nMÉTRICAS:");
            System.out.println("   Pontos por turno: " + String.format("%.2f", pontosPorTurno));
            
            if (tempoTotal > 0) {
                double turnosPorMinuto = (turno * 60000.0) / tempoTotal;
                System.out.println("   Turnos por minuto: " + String.format("%.2f", turnosPorMinuto));
            }
        }
        
        System.out.println("\nObrigado por jogar!");
        System.out.println("=" + "=".repeat(35));
    }
    

    
    /**
     * Formata chave de estatística
     */
    private String formatarChave(String chave) {
        return chave.replace("_", " ").substring(0, 1).toUpperCase() + 
               chave.replace("_", " ").substring(1);
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public String getNomeJogo() { return nomeJogo; }
    public int getPontuacao() { return pontuacao; }
    public int getTurno() { return turno; }
    public boolean isJogoAtivo() { return jogoAtivo; }
    public long getTempoInicio() { return tempoInicio; }
    public List<String> getHistoricoEventos() { return new ArrayList<>(historicoEventos); }
    public Map<String, Integer> getEstatisticas() { return new HashMap<>(estatisticas); }
}