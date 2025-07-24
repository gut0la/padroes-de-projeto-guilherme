package abstractfactory.versaoia.compadrao.classes;


import abstractfactory.versaoia.compadrao.interfaces.Terrain;

// Produtos concretos para Deserto
class DesertTerrain implements Terrain {
    public void display() {
        System.out.println("Terreno: Areia Quente");
    }
}
