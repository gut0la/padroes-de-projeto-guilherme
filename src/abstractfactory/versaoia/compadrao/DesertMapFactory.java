package abstractfactory.versaoia.compadrao;


// Fábrica concreta para Deserto
class DesertMapFactory implements MapFactory {
    public Terrain createTerrain() {
        return new DesertTerrain();
    }

    public Obstacle createObstacle() {
        return new CactusObstacle();
    }
}
