package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] directions) {
        List<MoveDirection> result = new ArrayList<>();

        for (String dir : directions) {
            switch (dir) {
                case "f" -> result.add(MoveDirection.FORWARD);
                case "b" -> result.add(MoveDirection.BACKWARD);
                case "r" -> result.add(MoveDirection.RIGHT);
                case "l" -> result.add(MoveDirection.LEFT);
            }
        }
        return result;
    }
}