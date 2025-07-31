package criacionais.prototype.compadrao.classes;

import criacionais.prototype.compadrao.interfaces.ComponenteLabirinto;

/**
 * Protótipo concreto de uma porta clássica
 * 
 * Esta é uma implementação concreta do padrão Prototype.
 * Pode ser clonada para criar novas instâncias sem usar construtores.
 * Útil quando a criação de objetos é custosa ou complexa.
 */
public class PortaClassica implements ComponenteLabirinto {
    
    /**
     * Exibe informações sobre a porta
     */
    public void mostrar() {
        System.out.println("Porta clássica");
    }

    /**
     * Clona esta instância criando uma cópia
     * 
     * @return Uma nova instância idêntica a esta
     * @throws CloneNotSupportedException Se a clonagem falhar
     */
    public ComponenteLabirinto clonar() throws CloneNotSupportedException {
        return (ComponenteLabirinto) super.clone();
    }
}
