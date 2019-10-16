package com.gamecodeschool.gatelogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Switch implements Node{
    boolean state;
    float horizontalTouched,verticalTouched;
    Bitmap switchOffGateFixed, switchOnGateFixed;
    public Switch(){}
    //public boolean Switch(Boolean state){
        //return state;
    //}
    public Switch(float horizontalTouched,float verticalTouched,Bitmap switchOffGateFixed, Bitmap switchOnGateFixed){this.horizontalTouched =horizontalTouched;this.verticalTouched = verticalTouched;this.switchOffGateFixed = switchOffGateFixed; this.switchOnGateFixed = switchOnGateFixed;}
    public void draw(Canvas canvas){
        //canvas.drawBitmap(switchOffGateFixed,horizontalTouched,verticalTouched,null);
        canvas.drawBitmap(switchOnGateFixed,horizontalTouched,verticalTouched,null);
    }
    public void drawOff(Canvas canvas){
        canvas.drawBitmap(switchOffGateFixed,horizontalTouched,verticalTouched,null);}
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