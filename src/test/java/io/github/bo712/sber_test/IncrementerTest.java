package io.github.bo712.sber_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncrementerTest {

    Incrementer incrementer;

    @BeforeEach
    void init() {
        incrementer = new Incrementer();
    }

    /* Testing of getNumber() method */
    @Test
    void getNumber_current0_returns0() {
        int expected = 0;
        int actual = incrementer.getNumber();
        assertEquals(expected, actual);
    }

    @Test
    void getNumber_current20_returns20() {
        incrementer.setCurrentNum(20);
        int expected = 20;
        int actual = incrementer.getNumber();
        assertEquals(expected, actual);
    }

    /* Testing of incrementNumber() method */
    @Test
    void incrementNumber_current20_returns21() {
        incrementer.setCurrentNum(20);
        incrementer.incrementNumber();
        int expected = 21;
        int actual = incrementer.getNumber();
        assertEquals(expected, actual);
    }

    @Test
    void incrementNumber_currentMaxInt_returns0() {
        incrementer.setCurrentNum(Integer.MAX_VALUE);
        incrementer.incrementNumber();
        int expected = 0;
        int actual = incrementer.getNumber();
        assertEquals(expected, actual);
    }

    /* Testing of setMaximumValue() method */
    @Test
    void setMaximumValue500_Current550_newCurrent0() {
        incrementer.setCurrentNum(550);
        incrementer.setMaximumValue(500);

        int expectedNewMax = 500;
        int actualNewMax = incrementer.getMaxCurrentNum().intValue();
        assertEquals(expectedNewMax, actualNewMax);

        int expectedCurrentNum  = 0;
        int actualCurrentNum = incrementer.getNumber();
        assertEquals(expectedCurrentNum, actualCurrentNum);
    }

    @Test
    void setMaximumValue_TryingSetNegative_MaximumValueNotChanged() {
        incrementer.setMaximumValue(-20);

        int expectedNewMax = Integer.MAX_VALUE;
        int actualNewMax = incrementer.getMaxCurrentNum().intValue();
        assertEquals(expectedNewMax, actualNewMax);
    }
}