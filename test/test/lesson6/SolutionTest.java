package test.lesson6;

import lesson6.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    void shouldMakeAnArrayAfterFourExpectedException() {
        assertThrows(RuntimeException.class, () -> Solution.makeAnArrayAfterFour(new int[]{0, 0, 0,}));
    }

    @ParameterizedTest
    @MethodSource("intsProvider")
    void shouldMakeAnArrayAfterFour(int[] expected, int[] actual) {
        assertArrayEquals(expected, Solution.makeAnArrayAfterFour(actual));
    }

    private static Stream<Arguments> intsProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}),
                Arguments.of(new int[]{3, 2, 1}, new int[]{4, 3, 2, 1}),
                Arguments.of(new int[]{3}, new int[]{1, 2, 4, 3}),
                Arguments.of(new int[]{1, 1, 4}, new int[]{1, 1, 4}),
                Arguments.of(new int[]{4}, new int[]{4}),
                Arguments.of(new int[]{2, 3, 5, 1, 7}, new int[]{1, 2, 4, 4, 2, 3, 5, 1, 7})
        );
    }

    @ParameterizedTest
    @MethodSource("shouldLookingForOneOrFourValue")
    void shouldLookingForOneOrFour(boolean expected, int[] input) {
        assertEquals(expected, Solution.lookingForOneOrFour(input));

    }

    private static Stream<Arguments> shouldLookingForOneOrFourValue() {
        return Stream.of(
                Arguments.of(false, new int[]{0, 0, 0}),
                Arguments.of(true, new int[]{3, 3, 4}),
                Arguments.of(false, new int[]{2, 2, 2}),
                Arguments.of(true, new int[]{3, 2, 1})
        );
    }
}