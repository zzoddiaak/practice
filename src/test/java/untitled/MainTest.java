package untitled;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private CalculateVolume calculateVolume;

    @BeforeEach
    void setUp() {
        calculateVolume = new CalculateVolume();
    }

    @Test
    void testTwoElements() {
        int[] heights = {1, 2};
        int expected = 1;

        assertEquals(expected, calculateVolume.calculateMaxArea(heights));
    }

    @Test
    void testExampleFromDescription() {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int expected = 49;

        assertEquals(expected, calculateVolume.calculateMaxArea(heights));
    }

    @Test
    void testNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculateVolume.calculateMaxArea(null);
        });

        assertEquals("Массив не может быть null", exception.getMessage());
    }

    @Test
    void testZeroElement() {
        int[] heights = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculateVolume.calculateMaxArea(heights);
        });

        assertEquals("Массив не может быть пустым", exception.getMessage());

    }

    @Test
    void testOneElement() {
        int[] heights = {1};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculateVolume.calculateMaxArea(heights);
        });

        assertEquals("Для создания контейнера необходимо минимум 2 линии", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void parameterizedTest(int[] heights, int expected) {
        assertEquals(expected, calculateVolume.calculateMaxArea(heights));
    }


    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 1}, 1),
                Arguments.of(new int[]{2, 3, 4, 5, 18, 17, 6}, 17),
                Arguments.of(new int[]{1, 3, 2, 5, 25, 24, 5}, 24),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 25, 24, 3, 4}, 24),
                Arguments.of(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49)
        );
    }

}
