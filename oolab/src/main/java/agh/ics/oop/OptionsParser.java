package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int counter = 0;
        MoveDirection[] result = new MoveDirection[args.length];

        for (String arg : args) {
            MoveDirection res =  switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
            if (res != null) {
                result[counter++] = res;
            }
        }

        return Arrays.copyOfRange(result, 0, counter);
    }
}