package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.interfaces.Porta;

/**
 * Implementação concreta de uma porta encantada
 * 
 * Esta é uma variação da interface Porta que adiciona
 * elementos mágicos ao comportamento básico.
 * Produto concreto criado pelo CriadorLabirintoEncantado.
 */
public class PortaEncantada implements Porta {
    
    /**
     * Implementação específica para abrir uma porta encantada
     * Adiciona elementos mágicos ao processo
     */
    public void abrir() {
        System.out.println("Abriu uma porta encantada com um feitiço");
    }
}