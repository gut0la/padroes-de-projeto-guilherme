package criacionais.factorymethod.compadrao.abstracts;

import criacionais.factorymethod.compadrao.interfaces.Porta;
import criacionais.factorymethod.compadrao.interfaces.Sala;

/**
 * Classe abstrata que implementa o padrão Factory Method
 * 
 * O padrão Factory Method define uma interface para criar objetos,
 * mas permite que as subclasses decidam qual classe instanciar.
 * 
 * Esta classe define o "template" para criar um labirinto,
 * mas delega para as subclasses a decisão de que tipos específicos
 * de sala e porta criar.
 */
public abstract class CriadorLabirinto {
    
    /**
     * Método template que define o algoritmo para criar um labirinto
     * 
     * Este método usa os factory methods (criarSala e criarPorta)
     * para criar os componentes do labirinto. O algoritmo é fixo,
     * mas os tipos específicos dos objetos criados dependem da subclasse.
     */
    public void criarLabirinto() {
        // Usa os factory methods para criar os componentes
        Sala sala1 = criarSala();    // Factory method
        Porta porta = criarPorta();  // Factory method
        Sala sala2 = criarSala();    // Factory method

        // Executa o algoritmo padrão usando os objetos criados
        sala1.entrar();
        porta.abrir();
        sala2.entrar();
    }

    /**
     * Factory Method para criar salas
     * 
     * Método abstrato que deve ser implementado pelas subclasses
     * para definir que tipo específico de sala criar.
     * 
     * @return Uma instância de Sala (tipo específico definido pela subclasse)
     */
    protected abstract Sala criarSala();
    
    /**
     * Factory Method para criar portas
     * 
     * Método abstrato que deve ser implementado pelas subclasses
     * para definir que tipo específico de porta criar.
     * 
     * @return Uma instância de Porta (tipo específico definido pela subclasse)
     */
    protected abstract Porta criarPorta();
}
