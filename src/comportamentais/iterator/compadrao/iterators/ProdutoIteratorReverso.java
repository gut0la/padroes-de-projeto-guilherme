package comportamentais.iterator.compadrao.iterators;

import comportamentais.iterator.compadrao.interfaces.Iterator;
import comportamentais.iterator.compadrao.classes.Produto;

/**
 * Iterator concreto para navegar em ordem reversa em uma coleção de produtos
 * Demonstra a flexibilidade do padrão Iterator
 */
public class ProdutoIteratorReverso implements Iterator<Produto> {
    
    private Produto[] produtos;
    private int posicao;
    
    public ProdutoIteratorReverso(Produto[] produtos) {
        this.produtos = produtos;
        // Encontra a última posição válida
        this.posicao = produtos.length - 1;
        while (posicao >= 0 && produtos[posicao] == null) {
            posicao--;
        }
    }
    
    @Override
    public boolean hasNext() {
        return posicao >= 0 && produtos[posicao] != null;
    }
    
    @Override
    public Produto next() {
        if (!hasNext()) {
            return null;
        }
        return produtos[posicao--];
    }
    
    @Override
    public void reset() {
        posicao = produtos.length - 1;
        while (posicao >= 0 && produtos[posicao] == null) {
            posicao--;
        }
    }
}