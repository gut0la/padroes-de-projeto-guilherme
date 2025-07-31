package estruturais.facade.compadrao.facade;

import estruturais.facade.sempadrao.classes.GerenciadorItens;
import estruturais.facade.sempadrao.classes.GerenciadorPortas;
import estruturais.facade.sempadrao.classes.GerenciadorSalas;

public class FachadaLabirintoFacade {
    private GerenciadorSalas salas;
    private GerenciadorPortas portas;
    private GerenciadorItens itens;

    public FachadaLabirintoFacade() {
        this.salas = new GerenciadorSalas();
        this.portas = new GerenciadorPortas();
        this.itens = new GerenciadorItens();
    }

    public void criarLabirintoClassico() {
        salas.criarSala("clássica", 1);
        salas.criarSala("clássica", 2);
        portas.criarPorta("clássica", 1, 2);
        itens.adicionarItem("normal", 1);
    }

    public void criarLabirintoEncantado() {
        salas.criarSala("encantada", 1);
        salas.criarSala("encantada", 2);
        portas.criarPorta("encantada com feitiço", 1, 2);
        itens.adicionarItem("mágico", 1);
    }
}
