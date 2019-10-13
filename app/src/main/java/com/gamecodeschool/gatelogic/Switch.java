package com.gamecodeschool.gatelogic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

class Switch implements Node{
    boolean state;
    float horizontalTouched,verticalTouched;
    Bitmap switchOffGateFixed;
    public Switch(){}
    //public boolean Switch(Boolean state){
        //return state;
    //}
    public Switch(float horizontalTouched,float verticalTouched,Bitmap switchOffGateFixed){this.horizontalTouched =horizontalTouched;this.verticalTouched = verticalTouched;this.switchOffGateFixed = switchOffGateFixed; }
    public void draw(Canvas canvas){
        canvas.drawBitmap(switchOffGateFixed,horizontalTouched,verticalTouched,null);
    }
    public void setState(boolean state){
        this.state = state;
    }
    public void toggle() {
        this.state = !this.state;
    }
    public boolean eval() {
        return state;
    }
}