package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void testParseMethod() {
        // given
        String[] validArguments = {"f", "b", "r", "l"};
        String[] invalidArguments = {"a", "x", "v", "m", "y"};
        String[] mixedArguments = {"a", "f", "f", "x", "y", "l", "z", "r", "p"};

        MoveDirection forward = MoveDirection.FORWARD;
        MoveDirection backward = MoveDirection.BACKWARD;
        MoveDirection left = MoveDirection.LEFT;
        MoveDirection right = MoveDirection.RIGHT;

        // when
        MoveDirection[] properResultValid = {forward, backward, right, left};
        MoveDirection[] properResultInvalid = {};
        MoveDirection[] properResultMixed = {forward, forward, left, right};

        MoveDirection[] resultValid = OptionsParser.parse(validArguments);
        MoveDirection[] resultInvalid = OptionsParser.parse(invalidArguments);
        MoveDirection[] resultMixed = OptionsParser.parse(mixedArguments);

        // then
        assertArrayEquals(resultValid, properResultValid);
        assertArrayEquals(resultInvalid, properResultInvalid);
        assertArrayEquals(resultMixed, properResultMixed);
    }
}