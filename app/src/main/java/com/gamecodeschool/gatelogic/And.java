package com.gamecodeschool.gatelogic;
import android.graphics.Bitmap;
import android.graphics.Canvas;

class And implements Node {
    float horizontalTouched,verticalTouched;
    Bitmap andGateFixed;Node a,b;
    public  And(float horizontalTouched, float verticalTouched,Bitmap andGateFixed){this.horizontalTouched = horizontalTouched;this.verticalTouched = verticalTouched; this.andGateFixed = andGateFixed;}
    public void draw(Canvas canvas){
        canvas.drawBitmap(andGateFixed,horizontalTouched,verticalTouched,null);
    }
    public void And(Node a, Node b) { this.setA(a); this.setB(b);}
    public void setA(Node n) { this.a=n;}
    public void setB(Node n) { this.b=n;}
    public boolean eval() {
        return a.eval() & b.eval(); }
}