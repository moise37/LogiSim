package com.gamecodeschool.gatelogic;

class Switch implements Node{
    boolean state;
    int verticalTouched, horizontalTouched;
    public Switch(boolean state) {
        this.state = state;
    }
    public void toggle() {
        this.state = !this.state;
    }
    public boolean eval() {
        return state;
    }
}