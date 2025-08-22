package comportamentais.memento.compadrao.classes;

import comportamentais.memento.compadrao.interfaces.Memento;

/**
 * Implementação concreta do Memento para o Editor
 * 
 * Armazena o estado interno do Editor de forma encapsulada.
 * Apenas o Editor (Originator) pode acessar o estado armazenado.
 * 
 * CARACTERÍSTICAS:
 * - Imutável após criação
 * - Estado encapsulado
 * - Acesso restrito ao Originator
 */
public class EditorMemento implements Memento {
    
    private final String conteudo;
    private final String fonte;
    private final int tamanhoFonte;
    private final long timestamp;
    
    /**
     * Construtor package-private - apenas classes do mesmo pacote podem criar
     * 
     * @param conteudo Conteúdo do editor
     * @param fonte Fonte utilizada
     * @param tamanhoFonte Tamanho da fonte
     */
    EditorMemento(String conteudo, String fonte, int tamanhoFonte) {
        this.conteudo = conteudo;
        this.fonte = fonte;
        this.tamanhoFonte = tamanhoFonte;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * Métodos package-private para acesso ao estado
     * Apenas o Originator (Editor) pode acessar
     */
    String getConteudo() {
        return conteudo;
    }
    
    String getFonte() {
        return fonte;
    }
    
    int getTamanhoFonte() {
        return tamanhoFonte;
    }
    
    long getTimestamp() {
        return timestamp;
    }
    
    /**
     * Método público para informações básicas (sem expor estado)
     */
    @Override
    public String toString() {
        return "EditorMemento[timestamp=" + timestamp + "]";
    }
    
    /**
     * Método para comparação de mementos
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        EditorMemento that = (EditorMemento) obj;
        return tamanhoFonte == that.tamanhoFonte &&
               timestamp == that.timestamp &&
               conteudo.equals(that.conteudo) &&
               fonte.equals(that.fonte);
    }
    
    @Override
    public int hashCode() {
        int result = conteudo.hashCode();
        result = 31 * result + fonte.hashCode();
        result = 31 * result + tamanhoFonte;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}