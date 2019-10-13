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

    static float horizontalTouched = -100;
    static float verticalTouched = -100;
    float touchX,touchY;
    float touchX1,touchY1;
    int whatWasTouched;
    int horizontalGap;
    int verticalGap;
    int distance;

    State state= new State();
    ArrayList<Node> tree = new ArrayList<>();
    ArrayList<Node> tree1 = new ArrayList<>();
    ArrayList<Node> tree2 = new ArrayList<>();
    ArrayList<Node> tree3 = new ArrayList<>();

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
        canvas.drawRect(0,blockSize*22,blockSize*42,blockSize*35,paint);
        paint.setTextSize(blockSize*2);
        paint.setColor(Color.WHITE);
        canvas.drawText(
                "And  "+"Or  "+"Not  "+ "Tog  "+ "Out  "+"DEL "+"Wire "+" 1  "+"2  "+"3  "+"SAVE",
                0, blockSize * 24f,
                paint);
        draw();
    }

    //Button logic
    //below the vertical will lead to more conditionals of each class
    void touch(float touchX, float touchY){
        horizontalTouched = (int) touchX / blockSize;
        verticalTouched = (int) touchY / blockSize;
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
        Bitmap on = BitmapFactory.decodeResource(getResources(),R.drawable.on);
        Bitmap onFixed = Bitmap.createScaledBitmap(on,blockSize*3,blockSize*3,false);

        //Bitmap switchOffGate = BitmapFactory.decodeResource(getResources(),R.drawable.offswitch);
       // Bitmap switchOffGateFixed = Bitmap.createScaledBitmap(switchOffGate,blockSize*3,blockSize*3,false);

        if (whatWasTouched==1)
        {
            And and = new And(touchX,touchY,andGateFixed);
            and.draw(canvas);
            tree.add(and);
            draw();
        }

        if (whatWasTouched==2) {
            Or or = new Or();
            tree.add(or);
            canvas.drawBitmap(orGateFixed,touchX1,touchY1,null);
            draw();
        }
        if (whatWasTouched==3) {
            Not not = new Not();
            tree.add(not);
            canvas.drawBitmap(notGateFixed,touchX1,touchY1,null);
            draw();
        }
        if (whatWasTouched==4) {
            Switch tog = new Switch(touchX, touchY,onFixed);
            tog.setState(false);
            tog.draw(canvas);
            tree.add(tog);
            draw();
        }
        if (whatWasTouched==5) {
            canvas.drawBitmap(offGateFixed,touchX1,touchY1,null);
            Out out = new Out();
            tree.add(out);
            draw();
        }
        if (whatWasTouched==6){
            tree.remove(tree.size()-1);    //deletes the last object of the one tree structure
        }
        if (whatWasTouched==7){
            //wire
        }
        if(whatWasTouched==8){tree1 = tree;}
        if (whatWasTouched==9){tree2 = tree;}
        if (whatWasTouched==10){tree3 = tree;}
        if (whatWasTouched==11){
            //confirm the save of the current tree into the 3 slots
        }
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
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!state.getState()) {
            if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                //get the current touch
                touchX = motionEvent.getX();
                touchY = motionEvent.getY();
                touch(touchX, touchY);
            }
        }else if(state.getState()){
            if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                touchX1 = (int) motionEvent.getX() / blockSize;
                touchY1 = (int) motionEvent.getY() / blockSize;
                place();
                state.toggleState();
            }
        }
        return true;
    }
    class State{
        boolean state = true;
        boolean getState(){ return state;}
        void toggleState(){ state = !state;}
    }
}