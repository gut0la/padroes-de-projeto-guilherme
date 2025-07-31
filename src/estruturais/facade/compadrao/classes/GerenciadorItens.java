package estruturais.facade.compadrao.classes;

/**
 * Subsistema responsável por gerenciar itens do labirinto
 * 
 * Esta é uma das classes do subsistema complexo que é simplificado
 * pelo padrão Facade. O cliente não precisa conhecer os detalhes
 * de como os itens são gerenciados internamente.
 */
public class GerenciadorItens {
    
    /**
     * Adiciona um item em uma sala específica
     * 
     * @param tipo Tipo do item (normal, mágico, etc.)
     * @param sala Número da sala onde adicionar o item
     */
    public void adicionarItem(String tipo, int sala) {
        System.out.println("Adicionando item " + tipo + " na sala " + sala);
    }
}

