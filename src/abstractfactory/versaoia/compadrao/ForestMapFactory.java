package abstractfactory.versaoia.compadrao;


// Fábrica concreta para Floresta
class ForestMapFactory implements MapFactory {
    public Terrain createTerrain() {
        return new ForestTerrain();
    }

    public Obstacle createObstacle() {
        return new TreeObstacle();
    }
}
