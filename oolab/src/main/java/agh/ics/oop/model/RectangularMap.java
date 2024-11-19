package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d leftDownMapCorner = new Vector2d(0,0);
    private final Vector2d rightUpMapCorner;

    public RectangularMap(int height, int width) {

        rightUpMapCorner = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (super.canMoveTo(position) &&
                position.follows(leftDownMapCorner) &&
                position.precedes(rightUpMapCorner));
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(leftDownMapCorner, rightUpMapCorner);
    }

    @Override
    public String toString() {
        return visualizer.draw(leftDownMapCorner, rightUpMapCorner);
    }
}