package abstractfactory.versaoia.compadrao.classes;


import abstractfactory.versaoia.compadrao.interfaces.MapFactory;
import abstractfactory.versaoia.compadrao.interfaces.Obstacle;
import abstractfactory.versaoia.compadrao.interfaces.Terrain;

// Classe cliente
public class MapGameWithAbstractFactory {
    public static void createMap(MapFactory factory) {
        Terrain terrain = factory.createTerrain();
        Obstacle obstacle = factory.createObstacle();
        terrain.display();
        obstacle.display();
    }

    public static void main(String[] args) {
        System.out.println("Criando mapa de Floresta:");
        createMap(new ForestMapFactory());

        System.out.println("\nCriando mapa de Deserto:");
        createMap(new DesertMapFactory());
    }
}
