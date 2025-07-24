package abstractfactory.semia.compadrao.interfaces;

public interface FabricaLabirinto {
    // interface para criar os componentes do labirinto
    Sala criarSala();
    Porta criarPorta();
}
