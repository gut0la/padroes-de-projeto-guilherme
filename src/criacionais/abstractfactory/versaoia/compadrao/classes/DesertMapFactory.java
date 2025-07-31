package criacionais.abstractfactory.versaoia.compadrao.classes;


import criacionais.abstractfactory.versaoia.compadrao.interfaces.MapFactory;
import criacionais.abstractfactory.versaoia.compadrao.interfaces.Obstacle;
import criacionais.abstractfactory.versaoia.compadrao.interfaces.Terrain;

// Fábrica concreta para Deserto
class DesertMapFactory implements MapFactory {
    public Terrain createTerrain() {
        return new DesertTerrain();
    }

    public Obstacle createObstacle() {
        return new CactusObstacle();
    }
}
