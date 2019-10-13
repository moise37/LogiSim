//Author: Moises Pantoja
//Built on top the "Subhunter" code by PacktPublishing
//drawLines function was included because of "gustav boyd"
//Professor Posnett provided the Logic for each gate
 
package com.gamecodeschool.gatelogic;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.widget.ImageView;

import java.util.ArrayList;
public class MainActivity extends Activity {

    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;

    int horizontalTouched, verticalTouched,touchX,touchY,touchX1,touchY1;
    int whatWasTouched;
    int horizontalGap;
    int verticalGap;
    int distance;
    State state= new State();
    ArrayList<Node> tree = new ArrayList<>();
    ArrayList<Node> tree1 = new ArrayList<>(tree); //create switches and out eval
        //canvas.drawBitmap(onFixed,horizontalTouched,verticalTouched,null);

    ArrayList<Node> tree2 = new ArrayList<>(tree);//create switches and out eval
    ArrayList<Node> tree3 = new ArrayList<>(tree);//create switches and out eval

    // Here are all the objects(instances)
    // of classes that we need to do some drawing
    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the current device's screen resolution
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        state = new State();

        // Initialize our size based variables based on the screen resolution
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        // Initialize all the objects ready for drawing
        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels,
                numberVerticalPixels,
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawColor(Color.WHITE);
        gameView.setImageBitmap(blankBitmap);
        paint.setColor(Color.BLACK);
        paint.setTextSize(blockSize*2);
        if (tree.size()==0){
            canvas.drawText(
                    "double tap A B C",
                    blockSize*25, blockSize*2f,
                    paint);
        }
        canvas.drawRect(0,blockSize*22,blockSize*42,blockSize*35,paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(
                "And  "+"Or  "+"Not  "+ "Tog  "+ "Out  "+"DEL "+"Wire "+" A  "+"B  "+"C  "+"SAVE",
                0, blockSize * 24f,
                paint);
        draw();
    }

    //Button logic
    //below the vertical will lead to more conditionals of each class
    void touch(int touchX, int touchY){
        horizontalTouched = touchX;
        verticalTouched = touchY;
        if(verticalTouched>=22)
        {
            if (horizontalTouched <4) {whatWasTouched =1;}
            if (horizontalTouched >= 4 && horizontalTouched <7) {whatWasTouched = 2;}
            if (horizontalTouched >= 7 && horizontalTouched < 12) {whatWasTouched =3;}
            if (horizontalTouched >= 12 && horizontalTouched < 15) {whatWasTouched =4;}
            if (horizontalTouched >= 15 && horizontalTouched <= 19) {whatWasTouched =5;}
            if (horizontalTouched >19 && horizontalTouched<= 23){whatWasTouched =6;}//del
            if (horizontalTouched>23 && horizontalTouched<=28){whatWasTouched =7;}
            if (horizontalTouched==29|| horizontalTouched==30){whatWasTouched =8;}
            if (horizontalTouched==31|| horizontalTouched ==32){whatWasTouched =9;}
            if (horizontalTouched==33|| horizontalTouched==34){whatWasTouched=10;}
            if (horizontalTouched>34){whatWasTouched=11;}
        }
        state.toggleState();
    }
    void place(){
        Bitmap andGate = BitmapFactory.decodeResource(getResources(),R.drawable.and);
        Bitmap andGateFixed = Bitmap.createScaledBitmap(andGate,blockSize*3,blockSize*3,false);
        Bitmap orGate = BitmapFactory.decodeResource(getResources(),R.drawable.orgate);
        Bitmap orGateFixed = Bitmap.createScaledBitmap(orGate,blockSize*3,blockSize*3,false);
        Bitmap off = BitmapFactory.decodeResource(getResources(),R.drawable.off);
        Bitmap offGateFixed = Bitmap.createScaledBitmap(off,blockSize*3,blockSize*3,false);
        Bitmap notGate = BitmapFactory.decodeResource(getResources(),R.drawable.not);
        Bitmap notGateFixed = Bitmap.createScaledBitmap(notGate,blockSize*3,blockSize*3,false);
        Bitmap switchOffGate = BitmapFactory.decodeResource(getResources(),R.drawable.offswitch);
        Bitmap switchOffGateFixed = Bitmap.createScaledBitmap(switchOffGate,blockSize*3,blockSize*3,false);
        Bitmap switchOnGate = BitmapFactory.decodeResource(getResources(),R.drawable.onswitch);
        Bitmap switchOnGateFixed = Bitmap.createScaledBitmap(switchOnGate,blockSize*3,blockSize*3,false);
        Bitmap on = BitmapFactory.decodeResource(getResources(),R.drawable.on);
        Bitmap onFixed = Bitmap.createScaledBitmap(on,blockSize*3,blockSize*3,false);
        Switch a = new Switch();Switch b = new Switch();
        a.setState(true); b.setState(true);//broken pics have issuws

        //determined where the touch was for the respective class
        if (whatWasTouched==1)
        {
            And and = new And(touchX1*blockSize,touchY1*blockSize,andGateFixed);

            tree.add(and);
            and.draw(canvas);//tree.lastIndexOf();
            and.And(a,b);
            if (and.eval()){
                //canvas.drawBitmap(onFixed,horizontalTouched,verticalTouched,null);
            }
            draw();
        }

        if (whatWasTouched==2) {
            Or or = new Or(touchX1*blockSize,blockSize*touchY1,orGateFixed);
            or.draw(canvas);
            tree.add(or);
            draw();
        }
        if (whatWasTouched==3) {
            Not not = new Not(touchX1*blockSize,blockSize*touchY1,notGateFixed);
            not.draw(canvas);
            tree.add(not);
            draw();
        }
        if (whatWasTouched==4) {
            Switch tog = new Switch(blockSize*touchX1, blockSize*touchY1,switchOffGateFixed,switchOnGateFixed); //change to proper off state toggle switch
            tog.setState(false);
            tog.drawOff(canvas);
            tree.add(tog);
            draw();
        }
        if (whatWasTouched==5) {
            Out out = new Out(blockSize*touchX1, blockSize*touchY1,offGateFixed, onFixed);
            out.drawOff(canvas);
            tree.add(out);
            draw();
        }
        if (whatWasTouched==6){
            tree.remove(tree.size()-1);    //deletes the last object of the one tree structure
            draw();
        }
        if (whatWasTouched==7){
            //click click click
              // this may not work with the current implementation
                // the current implementation is just alternating on a state of click for board or waiting for the action button
            //wire
            //state.toggleState();
            //state.toggleState();
        }

        //the saved arraylist are hardcoded to show logic functionality



        if(whatWasTouched==8){
            Switch tog = new Switch(blockSize*3, blockSize*12,switchOnGateFixed,switchOnGateFixed);
            tog.setState(true);
            tog.draw(canvas);
            Switch tog1 = new Switch(blockSize*3, blockSize*17,switchOnGateFixed,switchOnGateFixed);
            tog1.setState(true);
            tog1.draw(canvas);
            And and = new And(8*blockSize,15*blockSize,andGateFixed);
            tree1.add(tog);
            tree1.add(tog1);
            tree1.add(and);
            and.draw(canvas);//tree.lastIndexOf();
            and.And(tog,tog1);
            Out out = new Out(blockSize*12, blockSize*15,offGateFixed, onFixed);
            out.setA(and);
            if (out.eval()){
                out.draw(canvas);
            }
            tree = tree1;
            draw();
        } //hard coded circuit 1
        if (whatWasTouched==9){
            Switch tog = new Switch(blockSize*14, blockSize*0,switchOnGateFixed,switchOffGateFixed);
            tog.setState(true);
            tog.draw(canvas);
            Switch tog1 = new Switch(blockSize*14, blockSize*5,switchOnGateFixed,switchOffGateFixed);
            tog1.setState(false);
            tog1.drawOff(canvas);
            Or or = new Or(18*blockSize,3*blockSize,orGateFixed);
            tree2.add(tog);
            tree2.add(tog1);
            tree2.add(or);
            or.draw(canvas);
            or.Or(tog,tog1);
            Out out = new Out(blockSize*22, blockSize*3,offGateFixed, onFixed);
            out.setA(or);
            if(out.eval()){
                out.draw(canvas);
            }
            tree = tree2;
            draw();
        }
        if (whatWasTouched==10){
            Switch tog = new Switch(blockSize*19, blockSize*15,switchOnGateFixed,switchOffGateFixed);
            tog.setState(false);
            tog.draw(canvas);
            Not not = new Not(24*blockSize,15*blockSize,notGateFixed);
            tree3.add(tog);
            tree3.add(not);
            not.draw(canvas);
            not.Not(tog);
            Out out = new Out(blockSize*28, blockSize*15,offGateFixed, onFixed);
            out.setA(not);
            if (out.eval()){
                out.draw(canvas);
            }
            tree = tree3;
        }//hard coded circuit 1
        if (whatWasTouched==11){
            //change the state of where the prev 3 buttons would change to the current one
            //confirm the save of the current tree into the 3 slots
        }
        state.toggleState();
    }
    void draw() {
        setContentView(gameView);
        paint.setColor(Color.BLACK);
        //Draw the vertical lines of the grid
        for(int i = 0; i < gridWidth; i++){
            canvas.drawLine(blockSize * i, 0,
                    blockSize * i, numberVerticalPixels,
                    paint);
        }
        // Draw the horizontal lines of the grid
        for(int i = 0; i < gridHeight*.95; i++){
            canvas.drawLine(0, blockSize * i,
                    numberHorizontalPixels, blockSize * i,
                    paint);
        }
    }
    /*user taps*/
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        paint.setColor(Color.BLACK);


        if(state.getState()){
            if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                touchX1 = (int) motionEvent.getX() / blockSize;
                touchY1 = (int) motionEvent.getY() / blockSize;
                place();
                draw();
            }
        }else{
            if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                //get the current touch
                touchX = (int)motionEvent.getX()/blockSize;
                touchY = (int)motionEvent.getY()/blockSize;
                touch(touchX, touchY);
                draw();
            }
        }return true;
    }
    class State{
        boolean state = false;
        boolean getState(){ return state;}
        void toggleState(){ state = !state;}
    }
}