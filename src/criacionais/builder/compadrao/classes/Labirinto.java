package criacionais.builder.compadrao.classes;

/**
 * Classe que representa o produto complexo sendo construído pelo padrão Builder
 * 
 * Esta é a classe "Product" no padrão Builder.
 * Representa o objeto complexo que está sendo construído passo a passo.
 * Contém os componentes que são adicionados durante o processo de construção.
 */
public class Labirinto {
    // Componentes do labirinto que são construídos incrementalmente
    private String salas = "";
    private String portas = "";

    /**
     * Adiciona uma sala ao labirinto
     * 
     * @param sala Descrição da sala a ser adicionada
     */
    public void adicionarSala(String sala) {
        salas += sala + "\n";
    }

    /**
     * Adiciona uma porta ao labirinto
     * 
     * @param porta Descrição da porta a ser adicionada
     */
    public void adicionarPorta(String porta) {
        portas += porta + "\n";
    }

    /**
     * Exibe o labirinto completo construído
     */
    public void mostrar() {
        System.out.println("Labirinto:\nSalas:\n" + salas + "Portas:\n" + portas);
    }
}