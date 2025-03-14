package edu.luc.etl.cs313.android.shapes.model;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle)

    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }

    @Override
    public Location onFill(final Fill f) {
        return f.getShape().accept(this);
    }

    @Override
    public Location onGroup(final Group g) {
        if (g.getShapes().isEmpty()) {
            return new Location(0, 0, new Rectangle(0, 0));
        }
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Shape shape : g.getShapes()) {
            Location boundingBox = shape.accept(this);
            int x = boundingBox.getX();
            int y = boundingBox.getY();
            Rectangle rect = (Rectangle) boundingBox.getShape();

        return null;
    }

    @Override
    public Location onLocation(final Location l) {

        return null;
    }

    @Override
    public Location onRectangle(final Rectangle r) {
        return null;
    }

    @Override
    public Location onStrokeColor(final StrokeColor c) {
        return null;
    }

    @Override
    public Location onOutline(final Outline o) {
        return null;
    }

    @Override
    public Location onPolygon(final Polygon s) {
        return null;
    }
}
