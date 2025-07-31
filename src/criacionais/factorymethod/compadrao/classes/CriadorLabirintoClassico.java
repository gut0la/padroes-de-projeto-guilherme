package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.abstracts.CriadorLabirinto;
import criacionais.factorymethod.compadrao.interfaces.Porta;
import criacionais.factorymethod.compadrao.interfaces.Sala;

/**
 * Implementação concreta do Factory Method para labirintos clássicos
 * 
 * Esta classe herda de CriadorLabirinto e implementa os factory methods
 * para criar componentes específicos de um labirinto clássico.
 * 
 * O algoritmo de criação do labirinto (definido na classe pai) permanece
 * o mesmo, mas os tipos específicos dos objetos criados são definidos aqui.
 */
public class CriadorLabirintoClassico extends CriadorLabirinto {
    
    /**
     * Implementação do factory method para criar salas clássicas
     * 
     * @return Uma nova instância de SalaClassica
     */
    protected Sala criarSala() {
        return new SalaClassica();
    }

    /**
     * Implementação do factory method para criar portas clássicas
     * 
     * @return Uma nova instância de PortaClassica
     */
    protected Porta criarPorta() {
        return new PortaClassica();
    }
}
