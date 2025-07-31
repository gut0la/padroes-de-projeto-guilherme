package criacionais.prototype.compadrao.classes;

import criacionais.prototype.compadrao.interfaces.ComponenteLabirinto;

import java.util.HashMap;
import java.util.Map;

public class GerenciadorPrototipos {
    private Map<String, ComponenteLabirinto> prototipos = new HashMap<>();

    public void adicionarPrototipo(String chave, ComponenteLabirinto prototipo) {
        prototipos.put(chave, prototipo);
    }

    public ComponenteLabirinto getPrototipo(String chave) throws CloneNotSupportedException {
        return prototipos.get(chave).clonar();
    }
}
