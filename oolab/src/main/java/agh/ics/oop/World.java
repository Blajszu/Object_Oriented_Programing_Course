package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystarowal!");
        run(OptionsParser.parse(args));
        System.out.println("System zakonczyl dzialanie!");
    }

    public static void run(MoveDirection[] directions) {
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