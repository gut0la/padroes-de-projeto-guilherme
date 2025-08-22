package comportamentais.observer.compadrao.observers;

import comportamentais.observer.compadrao.interfaces.Observer;
import comportamentais.observer.compadrao.classes.DadosMovimento;
import comportamentais.observer.compadrao.classes.DadosItem;
import comportamentais.observer.compadrao.classes.DadosVida;

/**
 * Observer concreto que gerencia o sistema de pontuação
 * 
 * PADRÃO OBSERVER - CONCRETE OBSERVER:
 * Esta classe implementa Observer e reage a diferentes eventos
 * para calcular e atualizar a pontuação do jogador.
 * 
 * RESPONSABILIDADES:
 * - Calcular pontos baseados em diferentes ações
 * - Manter histórico de pontuação
 * - Aplicar multiplicadores e bônus
 * - Detectar conquistas (achievements)
 * 
 * VANTAGENS DO PADRÃO:
 * - Sistema de pontuação independente e reutilizável
 * - Pode reagir a qualquer evento do jogo
 * - Fácil de modificar regras de pontuação
 * - Pode ser desabilitado sem afetar o resto do jogo
 */
public class PontuacaoObserver implements Observer {
    private int pontos;
    private int movimentos;
    private int itensColetados;
    private int danoTotal;
    private boolean jogadorVivo;
    private String nomeJogador;
    
    // Constantes de pontuação
    private static final int PONTOS_MOVIMENTO = 10;
    private static final int PONTOS_ITEM = 50;
    private static final int BONUS_SOBREVIVENCIA = 100;
    private static final int PENALIDADE_DANO = 5;
    
    /**
     * Construtor do sistema de pontuação
     */
    public PontuacaoObserver() {
        this.pontos = 0;
        this.movimentos = 0;
        this.itensColetados = 0;
        this.danoTotal = 0;
        this.jogadorVivo = true;
        
        System.out.println("[PONTUAÇÃO] Sistema de pontuação inicializado");
    }
    
    /**
     * Implementação do método notificar da interface Observer
     * 
     * PADRÃO OBSERVER EM AÇÃO:
     * Reage a diferentes tipos de eventos para calcular pontuação
     */
    @Override
    public void notificar(String evento, Object dados) {
        switch (evento) {
            case "movimento":
                tratarMovimento((DadosMovimento) dados);
                break;
            case "coleta":
                tratarColeta((DadosItem) dados);
                break;
            case "dano":
                tratarDano((DadosVida) dados);
                break;
            case "morte":
                tratarMorte((String) dados);
                break;
            default:
                // Ignora eventos não relevantes para pontuação
                break;
        }
    }
    
    /**
     * Trata eventos de movimento para pontuação
     */
    private void tratarMovimento(DadosMovimento dados) {
        nomeJogador = dados.nomeJogador;
        movimentos++;
        
        // Pontos básicos por movimento
        int pontosGanhos = PONTOS_MOVIMENTO;
        
        // Bônus por exploração (movimentos únicos)
        if (movimentos % 5 == 0) {
            pontosGanhos += 25; // Bônus de exploração
            System.out.println("  [PONTUAÇÃO] 🎯 BÔNUS DE EXPLORAÇÃO! +25 pontos");
        }
        
        pontos += pontosGanhos;
        System.out.println("  [PONTUAÇÃO] +" + pontosGanhos + " pontos por movimento (Total: " + pontos + ")");
        
        // Verifica conquistas baseadas em movimento
        verificarConquistasMovimento();
    }
    
    /**
     * Trata eventos de coleta de itens
     */
    private void tratarColeta(DadosItem dados) {
        itensColetados++;
        
        // Pontos baseados no tipo de item
        int pontosItem = calcularPontosItem(dados.item);
        
        // Multiplicador baseado na quantidade de itens coletados
        int multiplicador = Math.min(itensColetados, 3); // Máximo 3x
        int pontosTotal = pontosItem * multiplicador;
        
        pontos += pontosTotal;
        
        System.out.println("  [PONTUAÇÃO] 💰 Item coletado: " + dados.item);
        System.out.println("  [PONTUAÇÃO] +" + pontosTotal + " pontos (" + pontosItem + " x" + multiplicador + ") (Total: " + pontos + ")");
        
        // Verifica conquistas de coleta
        verificarConquistasColeta();
    }
    
    /**
     * Trata eventos de dano recebido
     */
    private void tratarDano(DadosVida dados) {
        danoTotal += dados.danoRecebido;
        
        // Penalidade por receber dano
        int penalidade = dados.danoRecebido * PENALIDADE_DANO;
        pontos = Math.max(0, pontos - penalidade); // Não permite pontuação negativa
        
        System.out.println("  [PONTUAÇÃO] 💔 Dano recebido: -" + penalidade + " pontos (Total: " + pontos + ")");
        
        // Aviso se a vida está baixa
        if (dados.vidaNova <= 20 && dados.vidaNova > 0) {
            System.out.println("  [PONTUAÇÃO] ⚠️  VIDA CRÍTICA! Cuidado para não perder o bônus de sobrevivência!");
        }
    }
    
    /**
     * Trata eventos de morte do jogador
     */
    private void tratarMorte(String nomeJogador) {
        jogadorVivo = false;
        
        System.out.println("  [PONTUAÇÃO] 💀 " + nomeJogador + " morreu!");
        System.out.println("  [PONTUAÇÃO] ❌ Bônus de sobrevivência perdido (-" + BONUS_SOBREVIVENCIA + " pontos potenciais)");
        
        // Calcula pontuação final
        calcularPontuacaoFinal();
    }
    
    /**
     * Calcula pontos baseados no tipo de item
     */
    private int calcularPontosItem(String item) {
        switch (item.toLowerCase()) {
            case "tesouro":
            case "ouro":
                return 100;
            case "gema":
            case "diamante":
                return 150;
            case "chave":
                return 75;
            case "poção":
                return 30;
            default:
                return PONTOS_ITEM; // Valor padrão
        }
    }
    
    /**
     * Verifica conquistas relacionadas a movimento
     */
    private void verificarConquistasMovimento() {
        if (movimentos == 10) {
            pontos += 50;
            System.out.println("  [PONTUAÇÃO] 🏆 CONQUISTA: Explorador Iniciante! +50 pontos");
        } else if (movimentos == 25) {
            pontos += 100;
            System.out.println("  [PONTUAÇÃO] 🏆 CONQUISTA: Explorador Experiente! +100 pontos");
        } else if (movimentos == 50) {
            pontos += 200;
            System.out.println("  [PONTUAÇÃO] 🏆 CONQUISTA: Mestre Explorador! +200 pontos");
        }
    }
    
    /**
     * Verifica conquistas relacionadas à coleta
     */
    private void verificarConquistasColeta() {
        if (itensColetados == 1) {
            pontos += 25;
            System.out.println("  [PONTUAÇÃO] 🏆 CONQUISTA: Primeiro Tesouro! +25 pontos");
        } else if (itensColetados == 5) {
            pontos += 100;
            System.out.println("  [PONTUAÇÃO] 🏆 CONQUISTA: Colecionador! +100 pontos");
        } else if (itensColetados == 10) {
            pontos += 250;
            System.out.println("  [PONTUAÇÃO] 🏆 CONQUISTA: Mestre Colecionador! +250 pontos");
        }
    }
    
    /**
     * Calcula a pontuação final (chamado quando o jogo termina)
     */
    private void calcularPontuacaoFinal() {
        int pontuacaoFinal = pontos;
        
        // Bônus de sobrevivência
        if (jogadorVivo) {
            pontuacaoFinal += BONUS_SOBREVIVENCIA;
            System.out.println("  [PONTUAÇÃO] 🎉 BÔNUS DE SOBREVIVÊNCIA: +" + BONUS_SOBREVIVENCIA + " pontos");
        }
        
        // Bônus por eficiência (poucos danos)
        if (danoTotal <= 20) {
            int bonusEficiencia = 150;
            pontuacaoFinal += bonusEficiencia;
            System.out.println("  [PONTUAÇÃO] 🛡️  BÔNUS DE EFICIÊNCIA: +" + bonusEficiencia + " pontos (pouco dano recebido)");
        }
        
        pontos = pontuacaoFinal;
    }
    
    /**
     * Mostra o relatório completo de pontuação
     */
    public void mostrarRelatorio() {
        System.out.println("\n[PONTUAÇÃO] ===== RELATÓRIO DE PONTUAÇÃO =====");
        if (nomeJogador != null) {
            System.out.println("[PONTUAÇÃO] Jogador: " + nomeJogador);
        }
        System.out.println("[PONTUAÇÃO] Pontuação Total: " + pontos + " pontos");
        System.out.println("[PONTUAÇÃO] Movimentos: " + movimentos);
        System.out.println("[PONTUAÇÃO] Itens Coletados: " + itensColetados);
        System.out.println("[PONTUAÇÃO] Dano Total Recebido: " + danoTotal);
        System.out.println("[PONTUAÇÃO] Status: " + (jogadorVivo ? "Vivo 🟢" : "Morto 🔴"));
        
        // Classificação baseada na pontuação
        String classificacao = obterClassificacao();
        System.out.println("[PONTUAÇÃO] Classificação: " + classificacao);
    }
    
    /**
     * Retorna classificação baseada na pontuação
     */
    private String obterClassificacao() {
        if (pontos >= 1000) return "🥇 LENDÁRIO";
        else if (pontos >= 750) return "🥈 MESTRE";
        else if (pontos >= 500) return "🥉 EXPERIENTE";
        else if (pontos >= 250) return "🎖️  AVENTUREIRO";
        else if (pontos >= 100) return "🏅 INICIANTE";
        else return "🆕 NOVATO";
    }
    
    /**
     * Mostra pontuação atual (versão resumida)
     */
    public void mostrarPontuacao() {
        System.out.println("[PONTUAÇÃO] Total: " + pontos + " pontos | Movimentos: " + movimentos + " | Itens: " + itensColetados);
    }
    
    // Getters
    public int getPontos() { return pontos; }
    public int getMovimentos() { return movimentos; }
    public int getItensColetados() { return itensColetados; }
    public boolean isJogadorVivo() { return jogadorVivo; }
}