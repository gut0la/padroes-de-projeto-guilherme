package abstractfactory.versaoia.compadrao;



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
