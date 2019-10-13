package com.gamecodeschool.gatelogic;

class Out implements Node{
    int horizontalTouched,verticalTouched;
    Node a;
    public Out(){}
    public Out(Node a) { this.setA(a);}
    public void setA(Node n) { this.a=n;}
    public boolean eval() {return a.eval();}
}
