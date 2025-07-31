package criacionais.abstractfactory.versaoia.compadrao.interfaces;


// Interface da fábrica abstrata
public interface MapFactory {
    Terrain createTerrain();
    Obstacle createObstacle();
}
