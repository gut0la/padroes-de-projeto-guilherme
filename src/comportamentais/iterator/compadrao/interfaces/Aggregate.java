package comportamentais.iterator.compadrao.interfaces;

/**
 * Interface Aggregate do padrão Iterator
 * Define o contrato para coleções que podem ser iteradas
 * 
 * @param <T> tipo dos elementos da coleção
 */
public interface Aggregate<T> {
    
    /**
     * Cria um iterator para esta coleção
     * @return iterator para navegar na coleção
     */
    Iterator<T> createIterator();
    
    /**
     * Retorna o tamanho da coleção
     * @return número de elementos
     */
    int size();
}