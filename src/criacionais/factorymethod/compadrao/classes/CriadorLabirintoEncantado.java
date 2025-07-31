package criacionais.factorymethod.compadrao.classes;

import criacionais.factorymethod.compadrao.abstracts.CriadorLabirinto;
import criacionais.factorymethod.compadrao.interfaces.Porta;
import criacionais.factorymethod.compadrao.interfaces.Sala;

/**
 * Implementação concreta do Factory Method para labirintos encantados
 * 
 * Esta classe implementa os factory methods para criar componentes
 * específicos de um labirinto encantado (com elementos mágicos).
 * Demonstra como diferentes implementações podem usar o mesmo
 * algoritmo base mas criar produtos diferentes.
 */
public class CriadorLabirintoEncantado extends CriadorLabirinto {
    
    /**
     * Factory method para criar salas encantadas
     * 
     * @return Uma nova instância de SalaEncantada
     */
    protected Sala criarSala() {
        return new SalaEncantada();
    }

    /**
     * Factory method para criar portas encantadas
     * 
     * @return Uma nova instância de PortaEncantada
     */
    protected Porta criarPorta() {
        return new PortaEncantada();
    }
}
