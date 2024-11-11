package agh.ics.oop.model;

import java.util.LinkedList;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String, Integer> {

    private final List<String> textAtPositions = new LinkedList<>();

    @Override
    public boolean place(String objectToPlace) {
        textAtPositions.add(objectToPlace);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        int position = textAtPositions.indexOf(object);
        if(position < 0) return;

        int newPosition = switch (direction) {
            case RIGHT, FORWARD -> position + 1;
            case LEFT, BACKWARD -> position - 1;
        };

        if(!canMoveTo(newPosition)) return;

        String objectToMove = textAtPositions.set(newPosition, object);
        textAtPositions.remove(position);
        textAtPositions.add(position, objectToMove);
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && position < textAtPositions.size();
    }

    @Override
    public String objectAt(Integer position) {
        if(isOccupied(position)) return textAtPositions.get(position);
        return null;
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position >= 0 && position < textAtPositions.size();
    }

    @Override
    public String toString() {
        return textAtPositions + "\n";
    }
}
