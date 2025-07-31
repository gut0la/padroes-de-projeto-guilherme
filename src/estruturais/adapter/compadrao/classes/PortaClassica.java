package estruturais.adapter.compadrao.classes;

import estruturais.adapter.compadrao.interfaces.Porta;

/**
 * Implementação concreta da interface Porta
 * 
 * Esta classe representa uma porta tradicional que já implementa
 * a interface Porta do sistema. Não precisa de adapter.
 */
public class PortaClassica implements Porta {
    
    /**
     * Implementação do método abrir() da interface Porta
     * Define o comportamento específico para abrir uma porta clássica
     */
    public void abrir() {
        System.out.println("Abriu uma porta clássica");
    }
}
