package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @ParameterizedTest
    @CsvSource({
            "6 3 5 + 8 * +, 70",
            "10 6 9 3 + -11 * / * 17 + 5 +, 22"
    })
    void test1(String input, int expected) {
        var result = Main.calculateRpn("6 3 5 + 8 * +");
        assertEquals(70, result);
    }

}
