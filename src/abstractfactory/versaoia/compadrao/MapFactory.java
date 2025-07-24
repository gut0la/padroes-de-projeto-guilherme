package abstractfactory.versaoia.compadrao;


// Interface da fábrica abstrata
interface MapFactory {
    Terrain createTerrain();
    Obstacle createObstacle();
}
