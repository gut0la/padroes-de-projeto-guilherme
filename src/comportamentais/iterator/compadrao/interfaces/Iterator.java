package comportamentais.iterator.compadrao.interfaces;

/**
 * Interface Iterator do padrão Iterator
 * Define o contrato para navegação em coleções
 * 
 * @param <T> tipo dos elementos da coleção
 */
public interface Iterator<T> {
    
    /**
     * Verifica se há próximo elemento
     * @return true se há próximo elemento, false caso contrário
     */
    boolean hasNext();
    
    /**
     * Retorna o próximo elemento e avança o cursor
     * @return próximo elemento
     */
    T next();
    
    /**
     * Reinicia o iterator para o início
     */
    void reset();
}