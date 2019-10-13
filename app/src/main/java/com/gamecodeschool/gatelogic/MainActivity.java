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
                "+And " +"+Or "+"+Not "+ "+Switch "+ "+Out  "+"DEL "+"1  "+"2 "+"3 "+"SAVE",
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
            if (horizontalTouched > 4 && horizontalTouched < 8) {whatWasTouched = 2;}
            if (horizontalTouched > 8 && horizontalTouched < 13) {whatWasTouched =3;}
            if (horizontalTouched > 13 && horizontalTouched < 19) {whatWasTouched =4;}
            if (horizontalTouched > 19 && horizontalTouched < 26) {whatWasTouched =5;}
            if (horizontalTouched>26 && horizontalTouched<30){whatWasTouched =6;}
            if(horizontalTouched==30 || horizontalTouched==31){whatWasTouched =7;}
            if (horizontalTouched==32 || horizontalTouched ==33){whatWasTouched =8;}
            if (horizontalTouched== 34 || horizontalTouched==35){whatWasTouched=9;}
            if (horizontalTouched>35){whatWasTouched=10;}
        }
        state.toggleState();
    }
    void place(){
        if (whatWasTouched==1)
        {
            And and = new And(touchX1,touchY1);
            tree.add(and);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.and),touchX1,touchY1,null);
            draw();
        }

        if (whatWasTouched==2) {
            Or or = new Or();
            tree.add(or);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.orgate),touchX1,touchY1,null);
            draw();
        }
        if (whatWasTouched==3) {

            Not not = new Not();
            tree.add(not);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.not),touchX1,touchY1,null);
            draw();
        }
        if (whatWasTouched==4) {
            Switch tog = new Switch(false);
            tree.add(tog);
            draw();
        }
        if (whatWasTouched==5) {

            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.off),touchX1,touchY1,null);
            Out out = new Out();
            tree.add(out);
            draw();
        }
        if (whatWasTouched==6){
            tree.remove(tree.size()-1);    //deletes the last object of the one tree structure
            draw();
        }
        if(whatWasTouched==7){
            tree1 = tree;
        }
        if (whatWasTouched==8){//33 34
            tree2 = tree;
        }
        if (whatWasTouched==9){
            tree3 = tree;
        }
        if (whatWasTouched==10){
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
        boolean state = false;
        boolean getState(){ return state;}
        void toggleState(){ state = !state;}
    }
}