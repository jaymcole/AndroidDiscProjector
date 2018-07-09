package jasoncole.discprojector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Jason Cole on 7/8/2018.
 */

public class ProjectionCanvasTop extends ProjectionCanvas{
    public ProjectionCanvasTop(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.marginBot /= 2;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        canvas.drawRect(marginLeft, marginTop,canvas.getWidth()-marginRight,canvas.getHeight()-marginBot, canvasBackground);

        if (path != null)
            canvas.drawPath(path, projectionPaint);
        drawColumns(canvas, 10, 50, true);
        canvas.drawText(getResources().getString(R.string.canvas_top_view),marginLeft, gridText.getFontMetricsInt().bottom*5, gridText);
    }

    @Override
    protected void calculatePath() {

        Random rand = new Random();


        Path path = new Path();
        float x = 0, y = 0, speed = (this.getWidth() / PATH_RESOLUTION) * (flightValues[0] / 100.0f), degree = 0;
        float turn = (MAX_TURN_DEG * ((flightValues[2]-1) / 99.0f)) / PATH_RESOLUTION;
        float fade = (MAX_FADE_DEG * (flightValues[3] / 99.0f)) / PATH_RESOLUTION;
        int pivot = (int)(PATH_RESOLUTION * 0.60f);
        path.addCircle(x, y, projectionPaint.getStrokeWidth() * 3, Path.Direction.CCW);
        for(int i = 1; i < PATH_RESOLUTION; i++) {
            if (i < pivot)
                degree += turn;
             else
                 degree -= fade;


//            degree += (turn * (pivot/i)) - (fade * ((1-pivot)/i));

             if(TREES) {
                 if (rand.nextInt(PATH_RESOLUTION) == 10) {
                     path.addCircle(x, y, projectionPaint.getStrokeWidth() * 3, Path.Direction.CCW);
                     int temp = rand.nextInt(240) - 120;
                     degree += temp;
                     speed *= (temp / 120.0f);
                     turn *= (temp / 120.0f);
                     fade *= (temp / 120.0f);
                 }
             }

                    x += Math.cos(Math.toRadians(degree)) * speed;
            y += Math.sin(Math.toRadians(degree)) * speed;
            path.addCircle(x, y, projectionPaint.getStrokeWidth(), Path.Direction.CCW);
        }
        path.addCircle(x, y, projectionPaint.getStrokeWidth() * 3, Path.Direction.CCW);
        path.offset(marginLeft, this.getHeight()/2);
        this.path = path;
    }

}
