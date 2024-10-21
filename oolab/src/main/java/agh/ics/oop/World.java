package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystarowal!");
        run(OptionsParser.parse(args));
        System.out.println("System zakonczyl dzialanie!");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

    private static void run(MoveDirection[] directions) {
        for (MoveDirection direction : directions) {
            switch (direction) {
                case MoveDirection.FORWARD -> System.out.println("Zwierzak idzie do gory!");
                case MoveDirection.BACKWARD -> System.out.println("Zwierzak idzie w dol!");
                case MoveDirection.RIGHT -> System.out.println("Zwierzak skreca w prawo!");
                case MoveDirection.LEFT -> System.out.println("Zwierzak skreca w lewo!");
            }
        }
    }
}