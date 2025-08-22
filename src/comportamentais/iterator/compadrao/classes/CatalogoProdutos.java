package comportamentais.iterator.compadrao.classes;

import comportamentais.iterator.compadrao.interfaces.Aggregate;
import comportamentais.iterator.compadrao.interfaces.Iterator;
import comportamentais.iterator.compadrao.iterators.ProdutoIterator;
import comportamentais.iterator.compadrao.iterators.ProdutoIteratorReverso;

/**
 * Catálogo de produtos que implementa o padrão Iterator
 * Encapsula a estrutura interna e fornece diferentes formas de iteração
 */
public class CatalogoProdutos implements Aggregate<Produto> {
    
    private static final int MAX_PRODUTOS = 10;
    private Produto[] produtos;
    private int contador;
    
    public CatalogoProdutos() {
        produtos = new Produto[MAX_PRODUTOS];
        contador = 0;
        
        // Adicionando alguns produtos
        adicionarProduto(new Produto("Notebook", 2500.0));
        adicionarProduto(new Produto("Mouse", 50.0));
        adicionarProduto(new Produto("Teclado", 150.0));
        adicionarProduto(new Produto("Monitor", 800.0));
        adicionarProduto(new Produto("Impressora", 300.0));
    }
    
    public void adicionarProduto(Produto produto) {
        if (contador < MAX_PRODUTOS) {
            produtos[contador] = produto;
            contador++;
        } else {
            System.out.println("Catálogo cheio! Não é possível adicionar mais produtos.");
        }
    }
    
    @Override
    public Iterator<Produto> createIterator() {
        return new ProdutoIterator(produtos);
    }
    
    /**
     * Cria um iterator reverso
     * VANTAGEM: Diferentes tipos de iteração sem expor estrutura interna
     */
    public Iterator<Produto> createReverseIterator() {
        return new ProdutoIteratorReverso(produtos);
    }
    
    @Override
    public int size() {
        return contador;
    }
    
    /**
     * VANTAGEM: Estrutura interna completamente encapsulada
     * Cliente não precisa saber que usamos array internamente
     */
}