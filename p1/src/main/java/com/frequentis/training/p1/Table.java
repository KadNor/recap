package com.frequentis.training.p1;

public class Table extends Furniture {

    private boolean extensible;

    public Table() {
    }

    public Table(boolean extensible) {
        this.extensible = extensible;
    }

    public boolean isExtensible() {
        return extensible;
    }

    public void setExtensible(boolean extensible) {
        this.extensible = extensible;
    }
}
