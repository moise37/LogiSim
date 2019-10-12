package com.gamecodeschool.gatelogic;

class And implements Node {
    Node a,b;
    public And() { }
    public And(Node a, Node b) { this.setA(a); this.setB(b);}
    public void setA(Node n) { this.a=n;}
    public void setB(Node n) { this.b=n;}
    public boolean eval() {
        return a.eval() & b.eval(); }
}
