package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] directions) {
        List<MoveDirection> result = new ArrayList<>();

        for (String dir : directions) {
            MoveDirection res =  switch (dir) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
            if(res != null)
                result.add(res);
        }

        return result;
    }
}