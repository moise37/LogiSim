package com.gamecodeschool.gatelogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Not implements Node {
    Node n;
    float verticalTouched, horizontalTouched;
    Bitmap notGateFixed;
    public Not(float horizontalTouched, float verticalTouched,Bitmap notGateFixed) {this.verticalTouched = verticalTouched; this.horizontalTouched =horizontalTouched; this.notGateFixed = notGateFixed; }
    public void draw(Canvas canvas){
        canvas.drawBitmap(notGateFixed,horizontalTouched,verticalTouched,null);
    }
    public Not(Node n) { this.setSource(n); }
    public void setSource(Node n) {
        this.n=n;
    }
    public boolean eval() {
        return !n.eval();
    }
}