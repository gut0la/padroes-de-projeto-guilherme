package estruturais.facade.compadrao.facade;

import estruturais.facade.sempadrao.classes.GerenciadorItens;
import estruturais.facade.sempadrao.classes.GerenciadorPortas;
import estruturais.facade.sempadrao.classes.GerenciadorSalas;

/**
 * Classe Facade que simplifica a interface para criar labirintos
 * 
 * O padrão Facade fornece uma interface unificada para um conjunto
 * de interfaces em um subsistema. Define uma interface de nível mais alto
 * que torna o subsistema mais fácil de usar.
 * 
 * Esta classe esconde a complexidade de coordenar múltiplos gerenciadores
 * (salas, portas, itens) e oferece métodos simples para criar labirintos completos.
 */
public class FachadaLabirintoFacade {
    
    // Referências para os subsistemas complexos
    private GerenciadorSalas salas;
    private GerenciadorPortas portas;
    private GerenciadorItens itens;

    /**
     * Construtor que inicializa todos os gerenciadores necessários
     * 
     * O cliente não precisa se preocupar em criar e configurar
     * cada gerenciador individualmente.
     */
    public FachadaLabirintoFacade() {
        this.salas = new GerenciadorSalas();
        this.portas = new GerenciadorPortas();
        this.itens = new GerenciadorItens();
    }

    /**
     * Método de alto nível para criar um labirinto clássico completo
     * 
     * Encapsula toda a complexidade de coordenar os diferentes gerenciadores
     * para criar um labirinto clássico. O cliente só precisa chamar este método.
     */
    public void criarLabirintoClassico() {
        // Coordena as chamadas para os diferentes subsistemas
        salas.criarSala("clássica", 1);
        salas.criarSala("clássica", 2);
        portas.criarPorta("clássica", 1, 2);
        itens.adicionarItem("normal", 1);
    }

    /**
     * Método de alto nível para criar um labirinto encantado completo
     * 
     * Similar ao método anterior, mas configura os componentes
     * para criar um labirinto encantado.
     */
    public void criarLabirintoEncantado() {
        // Coordena as chamadas para criar um labirinto encantado
        salas.criarSala("encantada", 1);
        salas.criarSala("encantada", 2);
        portas.criarPorta("encantada com feitiço", 1, 2);
        itens.adicionarItem("mágico", 1);
    }
}
