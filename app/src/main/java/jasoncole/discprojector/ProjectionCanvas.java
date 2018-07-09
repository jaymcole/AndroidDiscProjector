package jasoncole.discprojector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;


/**
 * Created by Jason Cole on 7/8/2018.
 */




public abstract class ProjectionCanvas extends View {
    protected static final boolean TREES = true;
    protected static final float MAX_TURN_DEG = 40f;
    protected static final float MAX_FADE_DEG = 120f;

    public static final String TAG = "ProjectionCanvas";

    public static final int PATH_RESOLUTION = 1000;

    public ProjectionCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false);
        init();
    }

    public void init() {
        gridLines = new Paint();
        gridLines.setColor(Color.BLUE);

        gridText = new Paint();
        gridText.setTextSize(30);
        gridText.setColor(Color.GREEN);

        canvasBackground = new Paint();
        canvasBackground.setColor(Color.DKGRAY);

        projectionPaint = new Paint();
        projectionPaint.setStrokeWidth(5f);
        projectionPaint.setColor(Color.CYAN);
    }

    protected Paint gridLines, gridText, canvasBackground;
    protected Paint projectionPaint;

    protected int marginTop = 10, marginRight = 10, marginBot = 10, marginLeft = 10;



    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), gridLines);


    }

    protected void drawColumns(Canvas canvas, int columns, int inc, boolean text) {
        int increment = canvas.getWidth() / columns;
        int temp = 0;
        int value = 0;

        for (int row = 0; row < columns; row++) {
            temp+=increment;
            value+=inc;
            canvas.drawLine(temp, 0, temp, canvas.getHeight(), gridLines);

            if(text)
                canvas.drawText(value + "", temp, canvas.getHeight() - marginBot, gridText);
        }
    }

    protected void drawColumns3D(Canvas canvas, int columns, int inc, boolean text) {
        int increment = canvas.getWidth() / columns;
        int temp = 0;
        int value = 0;

        canvas.drawLine(canvas.getWidth()/2,(int)(canvas.getHeight()*0.65f), 0, canvas.getHeight(), gridLines);
        for (int row = 0; row < columns; row++) {
            temp+=increment;
            value+=inc;
            canvas.drawLine(canvas.getWidth()/2, (int)(canvas.getHeight() * 0.65f), temp, canvas.getHeight(), gridLines);

            if(text)
                canvas.drawText(value + "", temp, canvas.getHeight() - marginBot, gridText);
        }
    }


    protected void drawRows(Canvas canvas, int rows, int inc, boolean text) {
        int increment = canvas.getHeight() / rows;
        int temp = 0;
        int value = rows*inc;

        for (int row = 0; row < rows; row++) {
            temp+=increment;
            value-=inc;
            canvas.drawLine(0, temp, canvas.getWidth(), temp, gridLines);

            if(text)
                canvas.drawText(value + "", marginLeft, temp, gridText);
        }

    }

    protected static int[] flightValues = new int[4];
    public static void setSpeed(int speed) {
        flightValues[0] = (int)((speed / 14.0)*100);
    }

    public static void setGlide(int glide) {
        flightValues[1] = (int)((glide / 7.0)*100);
    }

    public static void setTurn(int turn) {
        flightValues[2] = Math.abs((int)(((turn-1) / 7.0)*100));
    }

    public static void setFade(int fade) {
        flightValues[3] = (int)((fade / 6.0)*100);
    }

    protected Path path;
    protected abstract void calculatePath();



}
