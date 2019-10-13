package com.gamecodeschool.gatelogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Or implements Node {
    Node a,b;
    float horizontalTouched,verticalTouched;
    Bitmap orGateFixed;
    public Or(float horizontalTouched,float verticalTouched,Bitmap orGateFixed) {this.horizontalTouched =horizontalTouched;this.verticalTouched = verticalTouched;this.orGateFixed = orGateFixed; }
    public void draw(Canvas canvas){
        canvas.drawBitmap(orGateFixed,horizontalTouched,verticalTouched,null);
    }
    public Or(Node a, Node b) { this.setA(a); this.setB(b);}
    public void setA(Node n) { this.a=n;}
    public void setB(Node n) { this.b=n;}
    public boolean eval() { return a.eval() | b.eval(); }
}