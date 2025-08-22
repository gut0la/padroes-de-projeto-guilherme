package comportamentais.observer.compadrao.observers;

import comportamentais.observer.compadrao.interfaces.Observer;
import comportamentais.observer.compadrao.classes.DadosMovimento;
import comportamentais.observer.compadrao.classes.DadosItem;
import comportamentais.observer.compadrao.classes.DadosVida;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Observer concreto que registra logs detalhados de todos os eventos
 * 
 * PADRÃO OBSERVER - CONCRETE OBSERVER:
 * Esta classe implementa Observer e mantém um registro completo
 * de todas as ações que acontecem no jogo.
 * 
 * RESPONSABILIDADES:
 * - Registrar todos os eventos com timestamp
 * - Manter histórico completo das ações
 * - Fornecer relatórios e estatísticas
 * - Detectar padrões de comportamento
 * 
 * VANTAGENS DO PADRÃO:
 * - Sistema de log independente e não intrusivo
 * - Pode ser facilmente desabilitado
 * - Registra eventos de qualquer parte do sistema
 * - Útil para debug e análise de gameplay
 */
public class LogObserver implements Observer {
    private List<String> historico;
    private DateTimeFormatter formatoTempo;
    private int totalEventos;
    private String nomeJogador;
    
    // Contadores para estatísticas
    private int movimentosRegistrados;
    private int itensRegistrados;
    private int danosRegistrados;
    private int eventosEspeciais;
    
    /**
     * Construtor do sistema de log
     */
    public LogObserver() {
        this.historico = new ArrayList<>();
        this.formatoTempo = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.totalEventos = 0;
        this.movimentosRegistrados = 0;
        this.itensRegistrados = 0;
        this.danosRegistrados = 0;
        this.eventosEspeciais = 0;
        
        registrarEvento("SISTEMA", "Sistema de log inicializado", "INFO");
        System.out.println("[LOG] Sistema de log inicializado");
    }
    
    /**
     * Implementação do método notificar da interface Observer
     * 
     * PADRÃO OBSERVER EM AÇÃO:
     * Registra TODOS os eventos que recebe, criando um log completo
     */
    @Override
    public void notificar(String evento, Object dados) {
        switch (evento) {
            case "movimento":
                registrarMovimento((DadosMovimento) dados);
                break;
            case "coleta":
                registrarColeta((DadosItem) dados);
                break;
            case "dano":
                registrarDano((DadosVida) dados);
                break;
            case "morte":
                registrarMorte((String) dados);
                break;
            default:
                registrarEventoGenerico(evento, dados);
                break;
        }
    }
    
    /**
     * Registra eventos de movimento
     */
    private void registrarMovimento(DadosMovimento dados) {
        nomeJogador = dados.nomeJogador;
        movimentosRegistrados++;
        
        String detalhes = String.format(
            "Movimento %d: %s de (%d,%d) para (%d,%d) direção %s",
            movimentosRegistrados,
            dados.nomeJogador,
            dados.xAnterior, dados.yAnterior,
            dados.xNovo, dados.yNovo,
            dados.direcao
        );
        
        registrarEvento("MOVIMENTO", detalhes, "INFO");
        System.out.println("  [LOG] Movimento registrado: " + dados.direcao + " -> (" + dados.xNovo + "," + dados.yNovo + ")");
        
        // Detecta padrões de movimento
        detectarPadraoMovimento(dados);
    }
    
    /**
     * Registra eventos de coleta de itens
     */
    private void registrarColeta(DadosItem dados) {
        itensRegistrados++;
        
        String detalhes = String.format(
            "Coleta %d: %s coletou '%s' na posição (%d,%d)",
            itensRegistrados,
            dados.nomeJogador,
            dados.item,
            dados.x, dados.y
        );
        
        registrarEvento("COLETA", detalhes, "SUCCESS");
        System.out.println("  [LOG] Item coletado: " + dados.item + " em (" + dados.x + "," + dados.y + ")");
    }
    
    /**
     * Registra eventos de dano
     */
    private void registrarDano(DadosVida dados) {
        danosRegistrados++;
        
        String detalhes = String.format(
            "Dano %d: %s recebeu %d de dano. Vida: %d -> %d",
            danosRegistrados,
            dados.nomeJogador,
            dados.danoRecebido,
            dados.vidaAnterior,
            dados.vidaNova
        );
        
        String nivel = dados.vidaNova <= 20 ? "WARNING" : "INFO";
        registrarEvento("DANO", detalhes, nivel);
        System.out.println("  [LOG] Dano registrado: -" + dados.danoRecebido + " vida (" + dados.vidaNova + " restante)");
        
        // Alerta para vida crítica
        if (dados.vidaNova <= 20 && dados.vidaNova > 0) {
            registrarEvento("ALERTA", dados.nomeJogador + " está com vida crítica: " + dados.vidaNova, "WARNING");
        }
    }
    
    /**
     * Registra eventos de morte
     */
    private void registrarMorte(String nomeJogador) {
        eventosEspeciais++;
        
        String detalhes = String.format(
            "MORTE: %s morreu após %d movimentos, %d itens coletados e %d danos recebidos",
            nomeJogador,
            movimentosRegistrados,
            itensRegistrados,
            danosRegistrados
        );
        
        registrarEvento("MORTE", detalhes, "CRITICAL");
        System.out.println("  [LOG] 💀 Morte registrada: " + nomeJogador);
        
        // Gera relatório final automático
        gerarRelatorioFinal();
    }
    
    /**
     * Registra eventos genéricos não específicos
     */
    private void registrarEventoGenerico(String evento, Object dados) {
        eventosEspeciais++;
        
        String detalhes = "Evento: " + evento + " | Dados: " + dados.toString();
        registrarEvento("GENERICO", detalhes, "INFO");
        System.out.println("  [LOG] Evento genérico: " + evento);
    }
    
    /**
     * Método central para registrar qualquer evento
     */
    private void registrarEvento(String categoria, String detalhes, String nivel) {
        totalEventos++;
        String timestamp = LocalTime.now().format(formatoTempo);
        
        String entrada = String.format(
            "[%s] [%s] [%s] %s",
            timestamp,
            nivel,
            categoria,
            detalhes
        );
        
        historico.add(entrada);
    }
    
    /**
     * Detecta padrões interessantes nos movimentos
     */
    private void detectarPadraoMovimento(DadosMovimento dados) {
        // Detecta se o jogador está andando em círculos
        if (movimentosRegistrados >= 4) {
            boolean possivelCirculo = verificarMovimentoCircular();
            if (possivelCirculo) {
                registrarEvento("PADRÃO", "Possível movimento circular detectado", "INFO");
                System.out.println("  [LOG] 🔄 Padrão detectado: Movimento circular");
            }
        }
        
        // Detecta movimentos muito rápidos
        if (movimentosRegistrados % 10 == 0) {
            registrarEvento("MILESTONE", "Milestone: " + movimentosRegistrados + " movimentos completados", "INFO");
            System.out.println("  [LOG] 🎯 Milestone: " + movimentosRegistrados + " movimentos");
        }
    }
    
    /**
     * Verifica se há padrão circular nos últimos movimentos
     */
    private boolean verificarMovimentoCircular() {
        // Implementação simplificada - verifica se voltou à posição anterior recentemente
        if (historico.size() >= 4) {
            String ultimoMovimento = historico.get(historico.size() - 1);
            String quartoUltimoMovimento = historico.get(historico.size() - 4);
            
            // Verifica se as posições são similares (implementação básica)
            return ultimoMovimento.contains("(0,0)") && quartoUltimoMovimento.contains("(0,0)");
        }
        return false;
    }
    
    /**
     * Gera relatório final quando o jogo termina
     */
    private void gerarRelatorioFinal() {
        registrarEvento("RELATORIO", "Gerando relatório final da sessão", "INFO");
        
        String relatorio = String.format(
            "SESSÃO FINALIZADA - Total: %d eventos | Movimentos: %d | Itens: %d | Danos: %d | Especiais: %d",
            totalEventos,
            movimentosRegistrados,
            itensRegistrados,
            danosRegistrados,
            eventosEspeciais
        );
        
        registrarEvento("FINAL", relatorio, "INFO");
    }
    
    /**
     * Mostra o histórico completo de eventos
     */
    public void mostrarHistoricoCompleto() {
        System.out.println("\n[LOG] ===== HISTÓRICO COMPLETO DE EVENTOS =====");
        
        if (historico.isEmpty()) {
            System.out.println("[LOG] Nenhum evento registrado ainda.");
            return;
        }
        
        for (String entrada : historico) {
            System.out.println("[LOG] " + entrada);
        }
        
        System.out.println("[LOG] ===== FIM DO HISTÓRICO =====");
    }
    
    /**
     * Mostra apenas os últimos N eventos
     */
    public void mostrarUltimosEventos(int quantidade) {
        System.out.println("\n[LOG] ===== ÚLTIMOS " + quantidade + " EVENTOS =====");
        
        int inicio = Math.max(0, historico.size() - quantidade);
        for (int i = inicio; i < historico.size(); i++) {
            System.out.println("[LOG] " + historico.get(i));
        }
    }
    
    /**
     * Mostra estatísticas resumidas
     */
    public void mostrarEstatisticas() {
        System.out.println("\n[LOG] ===== ESTATÍSTICAS DA SESSÃO =====");
        if (nomeJogador != null) {
            System.out.println("[LOG] Jogador: " + nomeJogador);
        }
        System.out.println("[LOG] Total de Eventos: " + totalEventos);
        System.out.println("[LOG] Movimentos: " + movimentosRegistrados);
        System.out.println("[LOG] Itens Coletados: " + itensRegistrados);
        System.out.println("[LOG] Danos Recebidos: " + danosRegistrados);
        System.out.println("[LOG] Eventos Especiais: " + eventosEspeciais);
        
        // Calcula algumas métricas
        if (movimentosRegistrados > 0) {
            double itensPerMovimento = (double) itensRegistrados / movimentosRegistrados;
            double danosPerMovimento = (double) danosRegistrados / movimentosRegistrados;
            
            System.out.printf("[LOG] Eficiência de Coleta: %.2f itens/movimento%n", itensPerMovimento);
            System.out.printf("[LOG] Taxa de Dano: %.2f danos/movimento%n", danosPerMovimento);
        }
    }
    
    /**
     * Exporta o log para análise (simulação)
     */
    public void exportarLog() {
        System.out.println("\n[LOG] 📁 Exportando log para análise...");
        System.out.println("[LOG] Arquivo simulado: jogo_log_" + LocalTime.now().format(formatoTempo).replace(":", "") + ".txt");
        System.out.println("[LOG] Total de linhas: " + historico.size());
        System.out.println("[LOG] ✅ Log exportado com sucesso!");
    }
    
    // Getters para estatísticas
    public int getTotalEventos() { return totalEventos; }
    public int getMovimentosRegistrados() { return movimentosRegistrados; }
    public int getItensRegistrados() { return itensRegistrados; }
    public int getDanosRegistrados() { return danosRegistrados; }
    public List<String> getHistorico() { return new ArrayList<>(historico); }
}