package criacionais.prototype.compadrao.interfaces;

public interface ComponenteLabirinto extends Cloneable {
    void mostrar();
    ComponenteLabirinto clonar() throws CloneNotSupportedException;
}
