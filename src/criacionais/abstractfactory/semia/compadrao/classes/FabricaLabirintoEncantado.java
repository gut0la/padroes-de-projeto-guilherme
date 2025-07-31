package criacionais.abstractfactory.semia.compadrao.classes;

import criacionais.abstractfactory.semia.compadrao.interfaces.FabricaLabirinto;
import criacionais.abstractfactory.semia.compadrao.interfaces.Porta;
import criacionais.abstractfactory.semia.compadrao.interfaces.Sala;

/**
 * Fábrica concreta para criar família de produtos encantados
 * 
 * Esta é uma "ConcreteFactory" no padrão Abstract Factory.
 * Cria uma família consistente de produtos relacionados (encantados).
 * Garante que todos os produtos criados sejam compatíveis entre si.
 */
public class FabricaLabirintoEncantado implements FabricaLabirinto {
    
    /**
     * Cria uma sala da família "encantada"
     * 
     * @return SalaEncantada - produto da família encantada
     */
    public Sala criarSala() {
        return new SalaEncantada();
    }

    /**
     * Cria uma porta da família "encantada"
     * 
     * @return PortaEncantada - produto da família encantada
     */
    public Porta criarPorta() {
        return new PortaEncantada();
    }
}
