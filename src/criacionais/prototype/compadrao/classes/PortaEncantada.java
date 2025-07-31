package criacionais.prototype.compadrao.classes;


import criacionais.prototype.compadrao.interfaces.ComponenteLabirinto;

public class PortaEncantada implements ComponenteLabirinto {
    public void mostrar() {
        System.out.println("Porta encantada com feitiço");
    }

    public ComponenteLabirinto clonar() throws CloneNotSupportedException {
        return (ComponenteLabirinto) super.clone();
    }
}

