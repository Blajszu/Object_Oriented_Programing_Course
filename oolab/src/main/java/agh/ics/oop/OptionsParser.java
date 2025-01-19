package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] directions) {
        return Stream.of(directions)
                .map(dir -> switch (dir) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(dir + " is not legal move specification!");
                })
                .toList();
    }
}