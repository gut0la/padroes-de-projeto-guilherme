package criacionais.abstractfactory.versaoia.compadrao.classes;


import criacionais.abstractfactory.versaoia.compadrao.interfaces.Obstacle;

class TreeObstacle implements Obstacle {
    public void display() {
        System.out.println("Obstáculo: Árvore");
    }
}
