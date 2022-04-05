package TP2;

public class Direction extends Unite {

    Unite parent;

    public Direction(int num, String nom) {
        super(num, nom);
    }

    public Unite getParent() {
        return parent;
    }

    public void setParent(Unite parent) {
        this.parent = parent;
    }
}

