package jasoncole.discprojector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Jason Cole on 7/8/2018.
 */

public class ProjectionCanvasSide extends ProjectionCanvas{
    public ProjectionCanvasSide(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.marginTop /= 2;
    }



    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawRect(marginLeft, marginTop,canvas.getWidth()-marginRight,canvas.getHeight()-marginBot, canvasBackground);


        drawColumns3D(canvas, 10, 10,true);
        if (path != null)
            canvas.drawPath(path, projectionPaint);
        drawRows(canvas, 4, 25,true);
        canvas.drawText(getResources().getString(R.string.canvas_side_view),marginLeft, gridText.getFontMetricsInt().bottom*5, gridText);

    }

    @Override
    protected void calculatePath() {
        Random rand = new Random();
        Path path = new Path();
        float x = 0, y = 0, speed = (this.getWidth() / PATH_RESOLUTION) * (flightValues[0] / 100.0f), degree = -25;
        float glide = (((7.0f * PATH_RESOLUTION) / flightValues[1]) / (PATH_RESOLUTION + 0.0f)) ;
        path.addCircle(x, y, projectionPaint.getStrokeWidth() * 3, Path.Direction.CCW);
        for(int i = 1; i < PATH_RESOLUTION; i++) {
            degree += glide;

            x +=  speed;
            y += Math.sin(Math.toRadians(degree)) * speed;
            path.addCircle(x, y, projectionPaint.getStrokeWidth(), Path.Direction.CCW);
        }
        path.addCircle(x, y, projectionPaint.getStrokeWidth() * 3, Path.Direction.CCW);
        path.offset(marginLeft, this.getHeight());
        this.path = path;
    }
}
