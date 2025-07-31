package estruturais.adapter.compadrao.adapter;

import estruturais.adapter.compadrao.classes.PortaMagicaExterna;
import estruturais.adapter.compadrao.interfaces.Porta;

/**
 * Classe Adapter que permite usar PortaMagicaExterna como se fosse uma Porta
 * 
 * Esta é a classe "Adapter" no padrão Adapter.
 * Ela implementa a interface esperada pelo cliente (Porta) e
 * internamente delega as chamadas para o objeto adaptado (PortaMagicaExterna).
 * 
 * O Adapter resolve a incompatibilidade entre:
 * - Interface esperada: Porta.abrir()
 * - Interface existente: PortaMagicaExterna.desbloquearComMagia()
 */
public class AdapterPortaMagica implements Porta {
    
    // Referência para o objeto que será adaptado
    private PortaMagicaExterna portaExterna;

    /**
     * Construtor que recebe o objeto a ser adaptado
     * 
     * @param portaExterna - Instância da PortaMagicaExterna que será adaptada
     */
    public AdapterPortaMagica(PortaMagicaExterna portaExterna) {
        this.portaExterna = portaExterna;
    }

    /**
     * Implementação do método abrir() da interface Porta
     * 
     * Este método "traduz" a chamada abrir() para desbloquearComMagia()
     * Permite que PortaMagicaExterna seja usada onde se espera uma Porta
     */
    public void abrir() {
        // Delega a chamada para o método correspondente do objeto adaptado
        portaExterna.desbloquearComMagia();
    }
}
