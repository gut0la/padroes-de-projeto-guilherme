package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.interfaces.Sala;

/**
 * Implementação concreta de uma sala encantada
 * 
 * Variação da interface Sala que inclui elementos mágicos.
 * Produto concreto criado pelo CriadorLabirintoEncantado.
 * Demonstra polimorfismo - mesmo método, comportamento diferente.
 */
public class SalaEncantada implements Sala {
    
    /**
     * Implementação específica para entrar em uma sala encantada
     * Inclui elementos mágicos na experiência
     */
    public void entrar() {
        System.out.println("Entrou em uma sala encantada com itens mágicos");
    }
}

