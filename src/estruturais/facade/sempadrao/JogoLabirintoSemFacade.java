package estruturais.facade.sempadrao;

import estruturais.facade.sempadrao.classes.GerenciadorItens;
import estruturais.facade.sempadrao.classes.GerenciadorPortas;
import estruturais.facade.sempadrao.classes.GerenciadorSalas;

public class JogoLabirintoSemFacade {
    public static void main(String[] args) {
        GerenciadorSalas salas = new GerenciadorSalas();
        GerenciadorPortas portas = new GerenciadorPortas();
        GerenciadorItens itens = new GerenciadorItens();

        // Criando labirinto clássico
        System.out.println("Criando labirinto clássico:");
        salas.criarSala("clássica", 1);
        salas.criarSala("clássica", 2);
        portas.criarPorta("clássica", 1, 2);
        itens.adicionarItem("normal", 1);

        // Criando labirinto encantado
        System.out.println("\nCriando labirinto encantado:");
        salas.criarSala("encantada", 1);
        salas.criarSala("encantada", 2);
        portas.criarPorta("encantada com feitiço", 1, 2);
        itens.adicionarItem("mágico", 1);
    }
}
