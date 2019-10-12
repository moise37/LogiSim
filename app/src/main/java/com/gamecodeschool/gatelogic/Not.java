package com.gamecodeschool.gatelogic;

class Not implements Node {
    Node n;
    int verticalTouched, horizontalTouched;
    public Not() {
    }
    public Not(Node n) {
        this.setSource(n);
    }
    public void setSource(Node n) {
        this.n=n;
    }
    public boolean eval() {
        return !n.eval();
    }
}