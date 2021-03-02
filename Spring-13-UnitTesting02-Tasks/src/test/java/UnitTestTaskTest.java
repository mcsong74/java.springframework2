import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UnitTestTaskTest {

    UnitTestTask unitTestTask;

    @ParameterizedTest
    @CsvSource({"AABCDDEFF, ABCDEF", "ABCCABDEEF, ABCABDEF"})
    @Order(2)
    void removePairsTest(String strInput, String expResult) {
        assertEquals(expResult, unitTestTask.removePairs(strInput));
    }

    @ParameterizedTest
    @CsvSource({"hello, 2, el"})
    @Order(3)
    void everyNthCharTest(String str, int nth, String expResult) {
        assertEquals(expResult, unitTestTask.everyNthChar(str, nth));
    }

    @Test
    @Order(4)
    void everyNthCharTest2() {
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            unitTestTask.everyNthChar("Hello", 7);
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"Ozzy"})
    @Order(5)
    void nullIfOddLengthTest(String args) {
        assertNull(unitTestTask.nullIfOddLength(args));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Hello"})
    @Order(6)
    void nullIfOddLengthTest2(String args) {
        assertNotNull(unitTestTask.nullIfOddLength(args));
    }


    @ParameterizedTest
    @CsvSource({"10, 5, 300"})
    @Order(7)
    void convertTest(long  a, long b, long expResult) {
        assertEquals(expResult, unitTestTask.convert(a, b));
    }
    @ParameterizedTest
    @CsvSource({"10, 0, 300"})
    @Order(8)
    void convertTest2(long  a, long b, long expResult) {
        assertThrows(ArithmeticException.class, ()->{
            unitTestTask.convert(a, b);
        });
    }

}