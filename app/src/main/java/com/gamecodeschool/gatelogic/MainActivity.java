//Author: Moises Pantoja
//Built on top the "Subhunter" code by PacktPublishing
//drawLines function was included because of "gustav boyd"
//Professor Posnett provided the Logic for each gate
 
package com.gamecodeschool.gatelogic;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.util.Log;
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

    float horizontalTouched = -100;
    float verticalTouched = -100;
    float touchX1 = -100;
    float touchY1 = -100;
    float touchX,touchY;
    int horizontalGap;
    int verticalGap;
    int distance;
    State state;
    ArrayList<Node> tree = new ArrayList<>();
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
                "+And " +"+Or "+"+Not "+ "+Switch "+ "+Out  "+"DEL "+"1 "+"2 "+"3 "+"SAVE",
                0, blockSize * 24f,
                paint);
        draw();


    }
    //Button logic
    //below the vertical will lead to more conditionals of each class
    void touch(float touchX, float touchY){
        horizontalTouched = (int) touchX / blockSize;
        verticalTouched = (int) touchY / blockSize;
    }
    void draw() {
        setContentView(gameView);
        paint.setColor(Color.BLACK);
        // Draw the vertical lines of the grid
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
    /*
        user taps
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("Debugging", "In onTouchEvent");
        if (state.getState()==false)
        {
            //get the current touch
            touchX=motionEvent.getX() ;touchY=motionEvent.getY();
            touch(touchX,touchY);

            if(verticalTouched>=22)
            {
                if (horizontalTouched <4)
                {
                    //Switch A = new Switch(true); Switch B = new Switch(true);
                    And and = new And();
                    tree.add(and);
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.and),horizontalTouched,verticalTouched,null);
                    draw();
                }

                if (horizontalTouched > 4 && horizontalTouched < 8) {
                    Or or = new Or();
                    tree.add(or);
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.orgate),horizontalTouched,verticalTouched,null);
                    draw();
                }
                if (horizontalTouched > 8 && horizontalTouched < 15) {
                    Not not = new Not();
                    tree.add(not);
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.not),horizontalTouched,verticalTouched,null);
                    draw();
                }
                if (horizontalTouched > 15.0 && horizontalTouched < 20.0) {
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.switchoff),horizontalTouched,verticalTouched,null);
                    Switch tog = new Switch(false);
                    tree.add(tog);
                    draw();
                }
                if (horizontalTouched > 20.0 && horizontalTouched < 25.0) {
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.off),horizontalTouched,verticalTouched,null);
                    Out out = new Out();
                    tree.add(out);
                    draw();
                }
                if (horizontalTouched>25 && horizontalTouched<30){
                    //delete function maybe
                    tree.remove(tree.size());    //you cant delete anything from the tree without it breaking
                    draw();
                }
            }
            state.toggleState();
        }else if(state.getState()==true){
            touchX1 = (int)motionEvent.getX()/blockSize;
            touchY1 = (int)motionEvent.getY()/blockSize;
            state.toggleState();
        }
        return true;
    }
    class State{
        boolean state = true;
        int which;
        boolean getState(){
            return state;
        }
        void toggleState(){
            state = !state;
        }
    }
}















































//no mans land



/*
// Use Pythagoras's theorem to get the
// distance travelled in a straight line
            horizontalGap = 20;
                    verticalGap = 10;
                    distance = (int)Math.sqrt(
                    ((horizontalGap * horizontalGap) +
                    (verticalGap * verticalGap)));
                    paint.setColor(Color.BLACK);
                    //canvas.drawColor(Color.argb(255, 255, 0, 0));
//canvas.drawLine(7*blockSize, 6*blockSize, 25*blockSize, 11*blockSize, paint);
  //      canvas.drawLine(7*blockSize, 19*blockSize, 25   *blockSize, 12*blockSize, paint);

 */


/*
            canvas.drawColor(Color.argb(255, 255, 0, 0)); //red
            if(and.eval()){
                canvas.drawColor(Color.argb(255, 255, 0, 0));
                canvas.drawRect(4 * blockSize,
                        4 * blockSize,
                        (4 * blockSize) + blockSize*3,
                        (4 * blockSize)+ blockSize*3,
                        paint );
            }
            paint.setColor(Color.argb(255, 0, 255, 0));
            canvas.drawRect(4 * blockSize,
                    4 * blockSize,
                    (4 * blockSize) + blockSize*3,
                    (4 * blockSize)+ blockSize*3,
                    paint );
            canvas.drawRect(4* blockSize,
                    17 * blockSize,
                    (4 * blockSize) + blockSize*3,
                    (17 * blockSize)+ blockSize*3,
                    paint );
            paint.setColor(Color.argb(255,0,0,0));
            canvas.drawRect(25* blockSize,
                    10 * blockSize,
                    (25 * blockSize) + blockSize*3,
                    (10 * blockSize)+ blockSize*3,
                    paint );

 */

/*
Author: Moises Pantoja
Built on top the "Subhunter" code by PacktPublishing
drawLines function was included because of "gustav boyd"
There is clear "reeks" of bad code


package com.gamecodeschool.gatelogic;
        import android.app.Activity;
        import android.content.Context;
        import android.net.wifi.WifiManager;
        import android.os.Bundle;
        import android.view.MotionEvent;
        import android.util.Log;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Point;
        import android.view.Display;
        import android.widget.ImageView;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Vector;

public class MainActivity extends Activity {
    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;
    float horizontalTouched = -100;
    float verticalTouched = -100;
    Grid grid;
    int distanceFromSub;
    int horizontalGap;
    int verticalGap;
    int distance;
    //Grid grid;
    ArrayList<Node> tree = new ArrayList<>();


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

        // Initialize our size based variables based on the screen resolution
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels,
                numberVerticalPixels,
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();
        grid.drawGrid(canvas,paint);
        // Tell Android to set our drawing
        // as the view for this app
        //setContentView();
        draw();
        //grid.drawGrid(canvas, paint);

        //draw();

        //setContentView(gameView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("Debugging", "In onTouchEvent");

        // Has the player removed their finger from the screen?
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            touch(motionEvent.getX(),
                    motionEvent.getY());
        }
        return true;
    }



    //Button logic
    //below the vertical will lead to more conditionals of each class
    void touch(float touchX, float touchY) {
        // Convert the float screen coordinates into int grid coordinates
        //distanceFromSub = grid.takeShot(touchX, touchY);

        horizontalTouched = (int) touchX / blockSize;
        verticalTouched = (int) touchY / blockSize;
        if (verticalTouched < 2) {

            if (horizontalTouched < 8) {
                //Switch A = new Switch(true); Switch B = new Switch(true);
                And and = new And();
                tree.add(and);
            }

            if (horizontalTouched > 6.0 && horizontalTouched < 10.0) {
                Or or = new Or(blockSize);
                tree.add(or);
            }
            if (horizontalTouched > 10.0 && horizontalTouched < 15.0) {
                Not not = new Not();
                tree.add(not);
            }
            if (horizontalTouched > 15.0 && horizontalTouched < 20.0) {
                Switch tog = new Switch(false);
                tree.add(tog);
            }
            if (horizontalTouched > 20.0 && horizontalTouched < 25.0) {
                //Out out = new Out();
                //tree.add(out);
                //placement of the "out" button on the grid
            }
        }
    }



    void draw() {
        gameView.setImageBitmap(blankBitmap);

        // Wipe the screen with a white color
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        // Draw the vertical lines of the grid
        for(int i = 0; i < gridWidth; i++){
            canvas.drawLine(blockSize * i, 0,
                    blockSize * i, numberVerticalPixels,
                    paint);
        }

        // Draw the horizontal lines of the grid
        for(int i = 0; i < gridHeight; i++){
            canvas.drawLine(0, blockSize * i,
                    numberHorizontalPixels, blockSize * i,
                    paint);
        }

        paint.setTextSize(blockSize*2);
        paint.setColor(Color.argb(255, 0, 0, 255));
    }


    abstract class AbstractGridCell {
        private int x, y, w, h;

        public AbstractGridCell(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public AbstractGridCell(AbstractGridCell myCell) {
            this(myCell.x, myCell.y, myCell.w, myCell.h);
        }

        public void drawGrid(Canvas canvas, Paint paint, int fillColor) {

            //draw grid with x,y,w,h
        }

        public void drawGrid(Canvas canvas, Paint paint) {
            drawGrid(canvas, paint, Color.WHITE);
        }

        public AbstractGridCell takeShot() {
            return this;
        }

        public AbstractGridCell clearShot() {
            return this;
        }

    }

    class EmptyGridCell extends AbstractGridCell {
        public EmptyGridCell(int x, int y, int w, int h) {
            super(x, y, w, h);
        }

        public EmptyGridCell(AbstractGridCell myCell) {
            super(myCell);
        }

        public AbstractGridCell takeShot() {
            return new Shot(this);
        }
    }

    class Sub extends AbstractGridCell {
        public Sub(AbstractGridCell myCell) {
            super(myCell);
        }

        public AbstractGridCell takeShot() {
            return new Shot(this);
        }
    }

    class SunkSub extends AbstractGridCell {
        SunkSub(AbstractGridCell myCell) {
            super(myCell);
        }

        public void drawGrid(Canvas canvas, Paint paint) {
            super.drawGrid(canvas, paint, Color.RED);
        }
    }

    class Shot extends AbstractGridCell {
        Shot(AbstractGridCell myCell) {
            super(myCell);
        }

        public void drawGrid(Canvas canvas, Paint paint) {
            super.drawGrid(canvas, paint, Color.BLUE);
        }

        public AbstractGridCell clearShot() {
            return new EmptyGridCell(this);
        }
    }

    class Grid {
        private class GridPosition {
            int x, y;
            public GridPosition(int x, int y) {this.x = x;this.y = y;}
        }

        private Vector<AbstractGridCell> gridCells;
        public Grid(int x, int y) {

            reset();
        }

        public void reset() {

            //rand =new URandomK(gridHeight*gridWidth);
            gridCells = new Vector<AbstractGridCell>(gridHeight * gridWidth);
            for (int h = 0; h < gridWidth; h++) {
                for (int v = 0; v < gridHeight; v++) {
                    gridCells.add(new EmptyGridCell(h * blockSize, v * blockSize, blockSize, blockSize));
                }
            }
        }

        public void spawnNewSub() {
            int subCell = 3;
            gridCells.set(subCell, new Sub(gridCells.get(subCell)));
        }

        public int sunkSubCount() {
            int sunkSubs = 0;
            for (AbstractGridCell agc : gridCells) {
                if (agc instanceof SunkSub) {
                    sunkSubs++;
                }
            }
            return sunkSubs;
        }

        private int gridCellN(GridPosition p) {
            return (gridHeight * p.x + p.y);
        }

        private int distanceToClosestSubFrom(GridPosition shotP) {
            int subD = gridWidth * gridHeight;
            for (int i = 0; i < gridCells.size(); i++) {
                //if (agc instanceof Sub) {
                //distance = (int)Math.sqrt(GridPosition.agc*grid
                //      ((horizontalGap * horizontalGap) +
                //            (verticalGap * verticalGap)));
                //}
            }
            return subD;
        }

        public int takeShot(float touchX, float touchY) {
            //int lastShot;

            GridPosition tP = new GridPosition((int) touchX / blockSize, (int) touchY / blockSize);
            int lastShot = gridCellN(tP);
            gridCells.set(lastShot, gridCells.get(lastShot).clearShot());
            gridCells.set(gridCellN(tP), gridCells.get(gridCellN(tP)).takeShot());
            return distanceToClosestSubFrom(tP);
        }

        public void drawGrid(Canvas canvas, Paint paint) {
            for (AbstractGridCell agc : gridCells) {
                agc.drawGrid(canvas, paint);
            }
        }

        public int getBlockSize() {
            return getBlockSize();
        }
    }
}

































































//no mans land



/*
// Use Pythagoras's theorem to get the
// distance travelled in a straight line
           horizontalGap = 20;
                   verticalGap = 10;
                   distance = (int)Math.sqrt(
                   ((horizontalGap * horizontalGap) +
                   (verticalGap * verticalGap)));
                   paint.setColor(Color.BLACK);
                   //canvas.drawColor(Color.argb(255, 255, 0, 0));
//canvas.drawLine(7*blockSize, 6*blockSize, 25*blockSize, 11*blockSize, paint);
 //      canvas.drawLine(7*blockSize, 19*blockSize, 25   *blockSize, 12*blockSize, paint);

*/


/*
           canvas.drawColor(Color.argb(255, 255, 0, 0)); //red
           if(and.eval()){
               canvas.drawColor(Color.argb(255, 255, 0, 0));
               canvas.drawRect(4 * blockSize,
                       4 * blockSize,
                       (4 * blockSize) + blockSize*3,
                       (4 * blockSize)+ blockSize*3,
                       paint );
           }
           paint.setColor(Color.argb(255, 0, 255, 0));
           canvas.drawRect(4 * blockSize,
                   4 * blockSize,
                   (4 * blockSize) + blockSize*3,
                   (4 * blockSize)+ blockSize*3,
                   paint );
           canvas.drawRect(4* blockSize,
                   17 * blockSize,
                   (4 * blockSize) + blockSize*3,
                   (17 * blockSize)+ blockSize*3,
                   paint );
           paint.setColor(Color.argb(255,0,0,0));
           canvas.drawRect(25* blockSize,
                   10 * blockSize,
                   (25 * blockSize) + blockSize*3,
                   (10 * blockSize)+ blockSize*3,
                   paint );

*/

