package nl.novi.first_demo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundingTest {

    @Test
    void roundTo() {
        // arrange
        double value = 7.124;

        // act
        double rounded = Rounding.roundTo(value, 2);

        // assert
        double expected = 7.12;
        assertEquals(expected, rounded);
    }

}