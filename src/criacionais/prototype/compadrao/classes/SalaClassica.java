package criacionais.prototype.compadrao.classes;

import criacionais.prototype.compadrao.interfaces.ComponenteLabirinto;

/**
 * Protótipo concreto de uma sala clássica
 * 
 * Implementação concreta que pode ser clonada para criar
 * novas instâncias rapidamente. Evita o overhead de construtores
 * complexos e permite criação dinâmica de objetos.
 */
public class SalaClassica implements ComponenteLabirinto {
    
    /**
     * Exibe informações sobre a sala
     */
    public void mostrar() {
        System.out.println("Sala clássica");
    }

    /**
     * Cria uma cópia desta sala
     * 
     * @return Nova instância clonada
     * @throws CloneNotSupportedException Se a clonagem falhar
     */
    public ComponenteLabirinto clonar() throws CloneNotSupportedException {
        return (ComponenteLabirinto) super.clone();
    }
}
