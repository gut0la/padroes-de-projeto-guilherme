package comportamentais.memento.compadrao.classes;

import comportamentais.memento.compadrao.interfaces.Memento;

/**
 * Classe Editor - Originator no padrão Memento
 * 
 * Responsável por:
 * - Manter seu estado interno
 * - Criar mementos com seu estado atual
 * - Restaurar estado a partir de mementos
 * 
 * VANTAGENS:
 * - Foco na lógica de negócio
 * - Estado encapsulado
 * - Criação/restauração simples de snapshots
 */
public class Editor {
    
    private String conteudo;
    private String fonte;
    private int tamanhoFonte;
    
    public Editor() {
        this.conteudo = "";
        this.fonte = "Arial";
        this.tamanhoFonte = 12;
    }
    
    // Métodos de negócio do Editor
    public void escrever(String texto) {
        this.conteudo += texto;
        System.out.println("Texto adicionado: " + texto);
    }
    
    public void alterarFonte(String novaFonte) {
        this.fonte = novaFonte;
        System.out.println("Fonte alterada para: " + novaFonte);
    }
    
    public void alterarTamanhoFonte(int novoTamanho) {
        this.tamanhoFonte = novoTamanho;
        System.out.println("Tamanho da fonte alterado para: " + novoTamanho);
    }
    
    public void limparConteudo() {
        this.conteudo = "";
        System.out.println("Conteúdo limpo");
    }
    
    public void substituirConteudo(String novoConteudo) {
        this.conteudo = novoConteudo;
        System.out.println("Conteúdo substituído por: " + novoConteudo);
    }
    
    // Métodos do padrão Memento
    
    /**
     * Cria um memento com o estado atual
     * 
     * @return Memento contendo o estado atual
     */
    public Memento criarMemento() {
        return new EditorMemento(conteudo, fonte, tamanhoFonte);
    }
    
    /**
     * Restaura o estado a partir de um memento
     * 
     * @param memento Memento contendo o estado a ser restaurado
     * @throws IllegalArgumentException se o memento não for do tipo correto
     */
    public void restaurarMemento(Memento memento) {
        if (!(memento instanceof EditorMemento)) {
            throw new IllegalArgumentException("Memento inválido para Editor");
        }
        
        EditorMemento editorMemento = (EditorMemento) memento;
        
        this.conteudo = editorMemento.getConteudo();
        this.fonte = editorMemento.getFonte();
        this.tamanhoFonte = editorMemento.getTamanhoFonte();
        
        System.out.println("Estado restaurado do memento: " + memento);
    }
    
    // Métodos de consulta
    public String getConteudo() {
        return conteudo;
    }
    
    public String getFonte() {
        return fonte;
    }
    
    public int getTamanhoFonte() {
        return tamanhoFonte;
    }
    
    public void mostrarEstado() {
        System.out.println("\n=== ESTADO ATUAL ===");
        System.out.println("Conteúdo: \"" + conteudo + "\"");
        System.out.println("Fonte: " + fonte);
        System.out.println("Tamanho: " + tamanhoFonte);
    }
    
    @Override
    public String toString() {
        return "Editor[conteudo=\"" + conteudo + "\", fonte=" + fonte + ", tamanho=" + tamanhoFonte + "]";
    }
}