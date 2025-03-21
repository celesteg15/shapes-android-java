package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle)

    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas; // FIXME
        this.paint = paint; // FIXME
        paint.setStyle(Style.STROKE);
    }

    @Override
    public Void onCircle(final Circle c) {
        Log.d("Draw", "Drawing Circle");
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }

    @Override
    public Void onStrokeColor(final StrokeColor c) {
        Log.d("Draw", "Applying Stroke Color");
        int oColor = paint.getColor();
        paint.setColor(c.getColor());
        c.getShape().accept(this);
        paint.setColor(oColor);
        return null;
    }

    @Override
    public Void onFill(final Fill f) {
        Log.d("Draw", "Applying Fill");
        Style oStyle = paint.getStyle();
        paint.setStyle(Style.FILL_AND_STROKE);
        f.getShape().accept(this);
        paint.setStyle(oStyle);
        return null;
    }

    @Override
    public Void onGroup(final Group g) {
        Log.d("Draw", "Drawing Group");
        for (Shape shape : g.getShapes()){
            shape.accept(this);
        }

        return null;
    }

    @Override
    public Void onLocation(final Location l) {
        Log.d("Draw", "Translating Location");
        canvas.save();
        canvas.translate(l.getX(), l.getY());
        l.getShape().accept(this);
        canvas.restore();
        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {
        Log.d("Draw", "Drawing Rectangle");
        canvas.drawRect(0,0, r.getWidth(), r.getHeight(), paint);
        return null;
    }

    @Override
    public Void onOutline(Outline o) {
        Style oStyle = paint.getStyle();
        paint.setStyle(Style.STROKE);
        o.getShape().accept(this);
        paint.setStyle(oStyle);
        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {

        final float[] pts = new float[s.getPoints().size() * 2];
        for (int i = 0; i< s.getPoints().size(); i++){
            pts[i * 2] = s.getPoints().get(i).getX();
            pts[i * 2 + 1] = s.getPoints().get(i).getY();
        }
        canvas.drawLines(pts, paint);
        return null;
    }
}
