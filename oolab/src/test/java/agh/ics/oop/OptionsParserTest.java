package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void testParseMethod() {
        // given
        final String[] validArguments = {"f", "b", "r", "l"};
        final String[] invalidArguments = {"a", "x", "v", "m", "y"};
        final String[] mixedArguments = {"a", "f", "f", "x", "y", "l", "z", "r", "p"};

        final MoveDirection forward = MoveDirection.FORWARD;
        final MoveDirection backward = MoveDirection.BACKWARD;
        final MoveDirection left = MoveDirection.LEFT;
        final MoveDirection right = MoveDirection.RIGHT;

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