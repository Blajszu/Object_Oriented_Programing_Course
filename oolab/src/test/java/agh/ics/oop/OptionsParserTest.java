package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void testParseMethod() {
        // given
        final String[] validArguments = {"f", "b", "r", "l"};
        final String[] mixedArguments = {"f", "f", "l", "r"};

        final MoveDirection forward = MoveDirection.FORWARD;
        final MoveDirection backward = MoveDirection.BACKWARD;
        final MoveDirection left = MoveDirection.LEFT;
        final MoveDirection right = MoveDirection.RIGHT;

        // when
        List<MoveDirection> properResultValid = List.of(forward, backward, right, left);
        List<MoveDirection> properResultMixed = List.of(forward, forward, left, right);

        List<MoveDirection> resultValid = OptionsParser.parse(validArguments);
        List<MoveDirection> resultMixed = OptionsParser.parse(mixedArguments);

        // then
        assertEquals(properResultValid, resultValid);
        assertEquals(properResultMixed, resultMixed);
    }

    @Test
    void testParseMethodWithInvalidArguments() {
        // given
        final String[] invalidArguments = {"a", "x", "v", "m", "y"};
        final String[] mixedArgumentsWithInvalid = {"f", "l", "z", "r"};

        // when & then
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(invalidArguments));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(mixedArgumentsWithInvalid));
    }
}