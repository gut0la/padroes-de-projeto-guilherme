package comportamentais.iterator.compadrao.classes;

/**
 * Classe que representa um produto no catálogo
 */
public class Produto {
    
    private String nome;
    private double preco;
    
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    public String getNome() {
        return nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Produto produto = (Produto) obj;
        return Double.compare(produto.preco, preco) == 0 && 
               nome.equals(produto.nome);
    }
    
    @Override
    public int hashCode() {
        return nome.hashCode() + Double.hashCode(preco);
    }
}