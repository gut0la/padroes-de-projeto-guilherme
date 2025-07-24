package abstractfactory.versaoia.compadrao.classes;


import abstractfactory.versaoia.compadrao.interfaces.Terrain;

// Produtos concretos para Floresta
class ForestTerrain implements Terrain {
    public void display() {
        System.out.println("Terreno: Floresta Verde");
    }
}
