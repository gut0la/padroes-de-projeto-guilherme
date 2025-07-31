package criacionais.prototype.compadrao.classes;


import criacionais.prototype.compadrao.interfaces.ComponenteLabirinto;

public class PortaClassica implements ComponenteLabirinto {
    public void mostrar() {
        System.out.println("Porta clássica");
    }

    public ComponenteLabirinto clonar() throws CloneNotSupportedException {
        return (ComponenteLabirinto) super.clone();
    }
}
