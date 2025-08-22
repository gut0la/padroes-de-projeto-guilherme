package comportamentais.iterator.sempadrao;

import java.util.ArrayList;
import java.util.List;

/**
 * Catálogo de produtos SEM o padrão Iterator
 * 
 * PROBLEMAS:
 * - Cliente precisa conhecer estrutura interna da coleção
 * - Acoplamento forte entre cliente e implementação
 * - Difícil trocar tipo de coleção
 * - Lógica de iteração espalhada pelo código
 */
public class CatalogoSemIterator {
    
    // PROBLEMA: Exposição da estrutura interna
    private List<String> produtos;
    private List<Double> precos;
    
    public CatalogoSemIterator() {
        produtos = new ArrayList<>();
        precos = new ArrayList<>();
        
        // Adicionando alguns produtos
        adicionarProduto("Notebook", 2500.0);
        adicionarProduto("Mouse", 50.0);
        adicionarProduto("Teclado", 150.0);
        adicionarProduto("Monitor", 800.0);
        adicionarProduto("Impressora", 300.0);
    }
    
    public static void main(String[] args) {
        CatalogoSemIterator catalogo = new CatalogoSemIterator();
        
        System.out.println("=== CATÁLOGO SEM ITERATOR ===");
        
        // PROBLEMA: Cliente precisa conhecer como iterar
        catalogo.listarTodos();
        catalogo.buscarPorPreco(200.0);
        catalogo.contarProdutos();
    }
    
    public void adicionarProduto(String nome, double preco) {
        produtos.add(nome);
        precos.add(preco);
    }
    
    /**
     * PROBLEMA: Lógica de iteração no próprio catálogo
     * Violação do Single Responsibility Principle
     */
    public void listarTodos() {
        System.out.println("\n--- TODOS OS PRODUTOS ---");
        
        // PROBLEMA: Cliente precisa saber que são duas listas paralelas
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + ". " + produtos.get(i) + " - R$ " + precos.get(i));
        }
    }
    
    /**
     * PROBLEMA: Mais lógica de iteração misturada
     */
    public void buscarPorPreco(double precoMaximo) {
        System.out.println("\n--- PRODUTOS ATÉ R$ " + precoMaximo + " ---");
        
        // PROBLEMA: Lógica de busca e iteração juntas
        for (int i = 0; i < produtos.size(); i++) {
            if (precos.get(i) <= precoMaximo) {
                System.out.println(produtos.get(i) + " - R$ " + precos.get(i));
            }
        }
    }
    
    /**
     * PROBLEMA: Mais uma responsabilidade no catálogo
     */
    public void contarProdutos() {
        System.out.println("\n--- ESTATÍSTICAS ---");
        System.out.println("Total de produtos: " + produtos.size());
        
        // PROBLEMA: Lógica de cálculo misturada com iteração
        double total = 0;
        for (int i = 0; i < precos.size(); i++) {
            total += precos.get(i);
        }
        System.out.println("Valor total do estoque: R$ " + total);
    }
    
    // PROBLEMA: Getters expõem estrutura interna
    public List<String> getProdutos() {
        return produtos; // Quebra encapsulamento
    }
    
    public List<Double> getPrecos() {
        return precos; // Quebra encapsulamento
    }
    
    /*
     * PROBLEMAS DESTA ABORDAGEM:
     * 
     * 1. ACOPLAMENTO: Cliente conhece estrutura interna (duas listas)
     * 
     * 2. ENCAPSULAMENTO QUEBRADO: Getters expõem coleções internas
     * 
     * 3. RESPONSABILIDADES MISTURADAS: Catálogo faz muitas coisas
     * 
     * 4. DIFICULDADE DE MUDANÇA: Trocar ArrayList por Array 
     *    quebraria todo o código cliente
     * 
     * 5. CÓDIGO DUPLICADO: Lógica de iteração repetida 
     *    em vários métodos
     * 
     * 6. INFLEXIBILIDADE: Difícil implementar diferentes 
     *    tipos de iteração (reversa, filtrada, etc.)
     */
}