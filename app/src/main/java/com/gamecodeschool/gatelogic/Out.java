package com.gamecodeschool.gatelogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Out implements Node{
    float horizontalTouched,verticalTouched;
    Bitmap off,on;
    Node a;
    public Out(float horizontalTouched, float verticalTouched, Bitmap off,Bitmap on){
        this.horizontalTouched = horizontalTouched;this.verticalTouched = verticalTouched;this.off = off;this.on = on;
    }
    public void draw(Canvas canvas){
        //if (eval()){
            canvas.drawBitmap(on, horizontalTouched, verticalTouched, null);
        //}else {
           // canvas.drawBitmap(off, horizontalTouched, verticalTouched, null);
        //}
    }
    public Out(Node a) { this.setA(a);}
    public void setA(Node n) { this.a=n;}
    public boolean eval() {return a.eval();}
}
