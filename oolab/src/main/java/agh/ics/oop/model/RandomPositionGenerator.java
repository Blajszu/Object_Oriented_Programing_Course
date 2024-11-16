package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {

    private final int[] listOfNumbers;
    private final int numberOfGrass;
    private final int width;
    private final int height;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int GrassCount) {
        numberOfGrass = GrassCount;
        listOfNumbers = new int[maxWidth * maxHeight];
        width = maxWidth;
        height = maxHeight;

        for(int i = 0; i < maxWidth * maxHeight; i++) {
            listOfNumbers[i] = i;
        }
    }

    public int getNumberOfGrass() {
        return numberOfGrass;
    }

    public int[] getListOfNumbers() {
        return listOfNumbers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new vectorIterator<Vector2d>(this);
    }
}

class vectorIterator<V> implements Iterator<Vector2d> {

    private final RandomPositionGenerator generator;
    private int howManyElements = 0;
    private final Random random = new Random();

    public vectorIterator(RandomPositionGenerator generator) {
        this.generator = generator;
    }

    @Override
    public boolean hasNext() {
        return howManyElements < generator.getNumberOfGrass();
    }

    @Override
    public Vector2d next() {

        int index = random.nextInt((generator.getHeight() * generator.getWidth()) - howManyElements);
        int element = generator.getListOfNumbers()[index];

        int x = element / generator.getWidth();
        int y = element % generator.getHeight();

        generator.getListOfNumbers()[index] = generator.getListOfNumbers()[(generator.getHeight() * generator.getWidth()) - howManyElements -1];
        generator.getListOfNumbers()[(generator.getHeight() * generator.getWidth()) - howManyElements - 1] = element;

        howManyElements++;
        return new Vector2d(x, y);
    }
}