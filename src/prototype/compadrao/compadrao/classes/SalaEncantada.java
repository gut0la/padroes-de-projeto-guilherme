package prototype.compadrao.compadrao.classes;


import prototype.compadrao.compadrao.interfaces.ComponenteLabirinto;

public class SalaEncantada implements ComponenteLabirinto {
    public void mostrar() {
        System.out.println("Sala encantada com itens mágicos");
    }

    public ComponenteLabirinto clonar() throws CloneNotSupportedException {
        return (ComponenteLabirinto) super.clone();
    }
}
