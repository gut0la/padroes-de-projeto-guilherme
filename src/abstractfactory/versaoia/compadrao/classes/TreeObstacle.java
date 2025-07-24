package abstractfactory.versaoia.compadrao.classes;


import abstractfactory.versaoia.compadrao.interfaces.Obstacle;

class TreeObstacle implements Obstacle {
    public void display() {
        System.out.println("Obstáculo: Árvore");
    }
}
