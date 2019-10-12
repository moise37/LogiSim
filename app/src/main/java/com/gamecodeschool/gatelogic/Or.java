package com.gamecodeschool.gatelogic;

class Or implements Node {
    Node a,b;
    public Or() {}
    public Or(Node a, Node b) { this.setA(a); this.setB(b);}
    public void setA(Node n) { this.a=n;}
    public void setB(Node n) { this.b=n;}
    public boolean eval() { return a.eval() | b.eval(); }
}