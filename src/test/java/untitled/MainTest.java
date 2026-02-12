package untitled;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private CalculateVolume calculateVolume;

    @BeforeEach
    void setUp(){
        calculateVolume = new CalculateVolume();
    }

    @Test
    void testTwoElements(){
        int[] heights = {1, 2};

        int expected = 1;

        assertEquals(expected, calculateVolume.maxArea(heights));
    }

    @Test
    void testExampleFromDescription(){
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int expected = 49;

        assertEquals(expected, calculateVolume.maxArea(heights));
    }

    @Test
    void testZeroElement() {
        int[] heights = {};

        int expected = 0;

        assertEquals(expected, calculateVolume.maxArea(heights));

    }

    @Test
    void testOneElement(){
        int[] heights = {0};

        int expected = 0;

        assertEquals(expected, calculateVolume.maxArea(heights));
    }



}