package comportamentais.memento.compadrao.interfaces;

/**
 * Interface Memento
 * 
 * Define o contrato para objetos que armazenam o estado interno
 * de um Originator. O Memento deve ser opaco para outros objetos
 * exceto o Originator que o criou.
 * 
 * CARACTERÍSTICAS:
 * - Interface vazia (marker interface)
 * - Encapsula estado sem expor detalhes
 * - Apenas o Originator pode acessar o estado
 * - Imutável após criação
 */
public interface Memento {
    // Interface marcadora - não expõe métodos públicos
    // O estado é acessível apenas através de métodos package-private
    // ou através de classes internas
}