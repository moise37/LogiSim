package com.gamecodeschool.gatelogic;

import android.graphics.Bitmap;

class Out implements Node{
    float horizontalTouched,verticalTouched;
    Bitmap off,on;
    Node a;
    public Out(){}
    public Out(Node a) { this.setA(a);}
    public void setA(Node n) { this.a=n;}
    public boolean eval() {return a.eval();}
}
