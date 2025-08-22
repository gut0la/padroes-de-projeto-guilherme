package comportamentais.iterator.compadrao.iterators;

import comportamentais.iterator.compadrao.interfaces.Iterator;
import comportamentais.iterator.compadrao.classes.Produto;

/**
 * Iterator concreto para navegar em uma coleção de produtos
 */
public class ProdutoIterator implements Iterator<Produto> {
    
    private Produto[] produtos;
    private int posicao;
    
    public ProdutoIterator(Produto[] produtos) {
        this.produtos = produtos;
        this.posicao = 0;
    }
    
    @Override
    public boolean hasNext() {
        return posicao < produtos.length && produtos[posicao] != null;
    }
    
    @Override
    public Produto next() {
        if (!hasNext()) {
            return null;
        }
        return produtos[posicao++];
    }
    
    @Override
    public void reset() {
        posicao = 0;
    }
}