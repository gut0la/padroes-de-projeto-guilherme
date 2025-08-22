package comportamentais.observer.compadrao.observers;

import comportamentais.observer.compadrao.interfaces.Observer;
import comportamentais.observer.compadrao.classes.DadosMovimento;

/**
 * Observer concreto que monitora a posição do jogador no mapa
 * 
 * PADRÃO OBSERVER - CONCRETE OBSERVER:
 * Esta classe implementa Observer e reage especificamente a eventos
 * relacionados ao movimento do jogador no mapa.
 * 
 * RESPONSABILIDADES:
 * - Manter o estado atual da posição do jogador
 * - Atualizar o mapa visual quando o jogador se move
 * - Detectar colisões ou áreas especiais
 * 
 * VANTAGENS:
 * - Focada apenas na lógica do mapa
 * - Pode ser adicionada/removida sem afetar outras partes
 * - Reutilizável em diferentes contextos
 */
public class MapaObserver implements Observer {
    private int jogadorX, jogadorY;
    private String nomeJogador;
    private char[][] mapa;
    private final int TAMANHO = 5;
    
    /**
     * Construtor que inicializa o mapa
     */
    public MapaObserver() {
        // Inicializa um mapa simples 5x5
        mapa = new char[TAMANHO][TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                mapa[i][j] = '.';
            }
        }
        
        // Adiciona alguns obstáculos
        mapa[1][1] = '#';
        mapa[2][3] = '#';
        mapa[3][2] = '#';
        
        // Adiciona itens
        mapa[1][3] = '$'; // Tesouro
        mapa[4][1] = '$'; // Tesouro
        
        System.out.println("[MAPA] Sistema de mapa inicializado");
    }
    
    /**
     * Implementação do método notificar da interface Observer
     * 
     * PADRÃO OBSERVER EM AÇÃO:
     * Este método é chamado automaticamente pelo Subject (Jogador)
     * sempre que algo relevante acontece.
     */
    @Override
    public void notificar(String evento, Object dados) {
        switch (evento) {
            case "movimento":
                tratarMovimento((DadosMovimento) dados);
                break;
            case "morte":
                tratarMorte((String) dados);
                break;
            default:
                // Este observer só se interessa por movimento e morte
                break;
        }
    }
    
    /**
     * Trata eventos de movimento do jogador
     */
    private void tratarMovimento(DadosMovimento dados) {
        // Atualiza posição do jogador
        jogadorX = dados.xNovo;
        jogadorY = dados.yNovo;
        nomeJogador = dados.nomeJogador;
        
        System.out.println("  [MAPA] Posição atualizada: (" + jogadorX + "," + jogadorY + ")");
        
        // Verifica se está dentro dos limites do mapa visual
        if (jogadorX >= 0 && jogadorX < TAMANHO && jogadorY >= 0 && jogadorY < TAMANHO) {
            char celula = mapa[jogadorY][jogadorX];
            
            // Verifica o que há na posição
            switch (celula) {
                case '#':
                    System.out.println("  [MAPA] ⚠️  COLISÃO! Jogador bateu em um obstáculo!");
                    break;
                case '$':
                    System.out.println("  [MAPA] 💰 TESOURO encontrado na posição (" + jogadorX + "," + jogadorY + ")!");
                    mapa[jogadorY][jogadorX] = '.'; // Remove o tesouro
                    break;
                case '.':
                    System.out.println("  [MAPA] ✅ Área livre");
                    break;
            }
        } else {
            System.out.println("  [MAPA] ⚠️  Jogador saiu dos limites do mapa visual!");
        }
    }
    
    /**
     * Trata eventos de morte do jogador
     */
    private void tratarMorte(String nomeJogador) {
        System.out.println("  [MAPA] 💀 " + nomeJogador + " morreu na posição (" + jogadorX + "," + jogadorY + ")");
        System.out.println("  [MAPA] Marcando local da morte no mapa...");
        
        // Marca o local da morte no mapa (se estiver dentro dos limites)
        if (jogadorX >= 0 && jogadorX < TAMANHO && jogadorY >= 0 && jogadorY < TAMANHO) {
            mapa[jogadorY][jogadorX] = 'X';
        }
    }
    
    /**
     * Mostra o estado atual do mapa
     */
    public void mostrarMapa() {
        System.out.println("\n[MAPA] Estado atual do mapa:");
        System.out.println("Legenda: . = vazio, # = obstáculo, $ = tesouro, J = jogador, X = morte");
        
        for (int y = TAMANHO - 1; y >= 0; y--) { // Y invertido para mostrar corretamente
            System.out.print("[MAPA] ");
            for (int x = 0; x < TAMANHO; x++) {
                if (x == jogadorX && y == jogadorY && nomeJogador != null) {
                    System.out.print("J "); // Mostra jogador
                } else {
                    System.out.print(mapa[y][x] + " ");
                }
            }
            System.out.println(" (y=" + y + ")");
        }
        
        System.out.print("[MAPA] ");
        for (int x = 0; x < TAMANHO; x++) {
            System.out.print("x=" + x + " ");
        }
        System.out.println();
        
        if (nomeJogador != null) {
            System.out.println("[MAPA] " + nomeJogador + " está na posição: (" + jogadorX + "," + jogadorY + ")");
        }
    }
    
    /**
     * Retorna informações sobre a posição atual
     */
    public void mostrarStatus() {
        if (nomeJogador != null) {
            System.out.println("[MAPA] Status: " + nomeJogador + " em (" + jogadorX + "," + jogadorY + ")");
        } else {
            System.out.println("[MAPA] Nenhum jogador sendo rastreado");
        }
    }
}