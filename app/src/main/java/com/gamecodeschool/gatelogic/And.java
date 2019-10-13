package com.gamecodeschool.gatelogic;

import android.graphics.BitmapFactory;

class And implements Node {
    float horizontalTouched,verticalTouched;
    Node a,b;
    public And(float horizontalTouched, float verticalTouched) {
        this.horizontalTouched = horizontalTouched;this.verticalTouched = verticalTouched;
        //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.and),horizontalTouched,verticalTouched,null);
    }
    public And(Node a, Node b) { this.setA(a); this.setB(b);}
    public void setA(Node n) { this.a=n;}
    public void setB(Node n) { this.b=n;}
    public boolean eval() {
        return a.eval() & b.eval(); }
}
