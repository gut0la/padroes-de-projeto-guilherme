package comportamentais.iterator.compadrao;

import comportamentais.iterator.compadrao.classes.CatalogoProdutos;
import comportamentais.iterator.compadrao.classes.Produto;
import comportamentais.iterator.compadrao.interfaces.Iterator;

/**
 * Catálogo de produtos COM o padrão Iterator
 * 
 * VANTAGENS:
 * - Encapsulamento da estrutura interna
 * - Desacoplamento entre cliente e implementação
 * - Múltiplas formas de iteração
 * - Fácil mudança de estrutura de dados
 */
public class CatalogoComIterator {
    
    public static void main(String[] args) {
        CatalogoProdutos catalogo = new CatalogoProdutos();
        
        System.out.println("=== CATÁLOGO COM ITERATOR ===");
        
        // VANTAGEM: Cliente não precisa conhecer estrutura interna
        listarTodos(catalogo);
        listarReverso(catalogo);
        buscarPorPreco(catalogo, 200.0);
        calcularEstatisticas(catalogo);
        
        System.out.println("\n=== DEMONSTRAÇÃO DE FLEXIBILIDADE ===");
        demonstrarFlexibilidade(catalogo);
    }
    
    /**
     * VANTAGEM: Método genérico que funciona com qualquer iterator
     */
    private static void listarTodos(CatalogoProdutos catalogo) {
        System.out.println("\n--- TODOS OS PRODUTOS ---");
        
        Iterator<Produto> iterator = catalogo.createIterator();
        int contador = 1;
        
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            System.out.println(contador + ". " + produto);
            contador++;
        }
    }
    
    /**
     * VANTAGEM: Iteração reversa sem conhecer estrutura interna
     */
    private static void listarReverso(CatalogoProdutos catalogo) {
        System.out.println("\n--- PRODUTOS EM ORDEM REVERSA ---");
        
        Iterator<Produto> iterator = catalogo.createReverseIterator();
        int contador = 1;
        
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            System.out.println(contador + ". " + produto);
            contador++;
        }
    }
    
    /**
     * VANTAGEM: Lógica de busca separada da lógica de iteração
     */
    private static void buscarPorPreco(CatalogoProdutos catalogo, double precoMaximo) {
        System.out.println("\n--- PRODUTOS ATÉ R$ " + precoMaximo + " ---");
        
        Iterator<Produto> iterator = catalogo.createIterator();
        
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getPreco() <= precoMaximo) {
                System.out.println(produto);
            }
        }
    }
    
    /**
     * VANTAGEM: Cálculos usando iterator, sem conhecer estrutura
     */
    private static void calcularEstatisticas(CatalogoProdutos catalogo) {
        System.out.println("\n--- ESTATÍSTICAS ---");
        System.out.println("Total de produtos: " + catalogo.size());
        
        Iterator<Produto> iterator = catalogo.createIterator();
        double total = 0;
        double menor = Double.MAX_VALUE;
        double maior = Double.MIN_VALUE;
        
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            double preco = produto.getPreco();
            
            total += preco;
            if (preco < menor) menor = preco;
            if (preco > maior) maior = preco;
        }
        
        System.out.println("Valor total do estoque: R$ " + total);
        System.out.println("Menor preço: R$ " + menor);
        System.out.println("Maior preço: R$ " + maior);
        System.out.println("Preço médio: R$ " + (total / catalogo.size()));
    }
    
    /**
     * Demonstra a flexibilidade do padrão
     * Múltiplas iterações simultâneas
     */
    private static void demonstrarFlexibilidade(CatalogoProdutos catalogo) {
        System.out.println("\nDemonstrando múltiplos iterators simultâneos:");
        
        Iterator<Produto> iterator1 = catalogo.createIterator();
        Iterator<Produto> iterator2 = catalogo.createReverseIterator();
        
        System.out.println("\nComparando primeira e última posições:");
        
        if (iterator1.hasNext() && iterator2.hasNext()) {
            Produto primeiro = iterator1.next();
            Produto ultimo = iterator2.next();
            
            System.out.println("Primeiro: " + primeiro);
            System.out.println("Último: " + ultimo);
        }
        
        // Reset e nova iteração
        iterator1.reset();
        System.out.println("\nApós reset, primeiro produto novamente: " + iterator1.next());
    }
    
    /*
     * VANTAGENS DO PADRÃO ITERATOR:
     * 
     * 1. ENCAPSULAMENTO: Estrutura interna completamente oculta
     * 
     * 2. FLEXIBILIDADE: Diferentes tipos de iteração 
     *    (normal, reversa, filtrada)
     * 
     * 3. DESACOPLAMENTO: Cliente não depende da implementação
     * 
     * 4. MÚLTIPLAS ITERAÇÕES: Vários iterators simultâneos
     * 
     * 5. UNIFORMIDADE: Interface consistente para 
     *    diferentes tipos de coleção
     */
}