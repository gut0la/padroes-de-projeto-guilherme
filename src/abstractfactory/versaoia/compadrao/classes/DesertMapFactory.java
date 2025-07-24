package abstractfactory.versaoia.compadrao.classes;


import abstractfactory.versaoia.compadrao.interfaces.MapFactory;
import abstractfactory.versaoia.compadrao.interfaces.Obstacle;
import abstractfactory.versaoia.compadrao.interfaces.Terrain;

// Fábrica concreta para Deserto
class DesertMapFactory implements MapFactory {
    public Terrain createTerrain() {
        return new DesertTerrain();
    }

    public Obstacle createObstacle() {
        return new CactusObstacle();
    }
}
