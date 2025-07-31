package prototype.compadrao.compadrao.interfaces;

public interface ComponenteLabirinto extends Cloneable {
    void mostrar();
    ComponenteLabirinto clonar() throws CloneNotSupportedException;
}
