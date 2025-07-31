package prototype.compadrao.compadrao.classes;

import prototype.compadrao.compadrao.interfaces.ComponenteLabirinto;

public class SalaClassica implements ComponenteLabirinto {
    public void mostrar() {
        System.out.println("Sala clássica");
    }

    public ComponenteLabirinto clonar() throws CloneNotSupportedException {
        return (ComponenteLabirinto) super.clone();
    }
}
