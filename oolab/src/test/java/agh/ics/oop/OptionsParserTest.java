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
        final String[] invalidArguments = {"a", "x", "v", "m", "y"};
        final String[] mixedArguments = {"a", "f", "f", "x", "y", "l", "z", "r", "p"};

        final MoveDirection forward = MoveDirection.FORWARD;
        final MoveDirection backward = MoveDirection.BACKWARD;
        final MoveDirection left = MoveDirection.LEFT;
        final MoveDirection right = MoveDirection.RIGHT;

        // when
        List<MoveDirection> properResultValid = List.of(forward, backward, right, left);
        List<MoveDirection> properResultInvalid = List.of();
        List<MoveDirection> properResultMixed = List.of(forward, forward, left, right);

        List<MoveDirection> resultValid = OptionsParser.parse(validArguments);
        List<MoveDirection> resultInvalid = OptionsParser.parse(invalidArguments);
        List<MoveDirection> resultMixed = OptionsParser.parse(mixedArguments);

        // then
        assertEquals(properResultValid, resultValid);
        assertEquals(properResultInvalid, resultInvalid);
        assertEquals(properResultMixed, resultMixed);
    }
}