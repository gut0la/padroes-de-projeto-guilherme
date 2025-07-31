package criacionais.abstractfactory.versaoia.compadrao.classes;


import criacionais.abstractfactory.versaoia.compadrao.interfaces.MapFactory;
import criacionais.abstractfactory.versaoia.compadrao.interfaces.Obstacle;
import criacionais.abstractfactory.versaoia.compadrao.interfaces.Terrain;

// Fábrica concreta para Floresta
class ForestMapFactory implements MapFactory {
    public Terrain createTerrain() {
        return new ForestTerrain();
    }

    public Obstacle createObstacle() {
        return new TreeObstacle();
    }
}
