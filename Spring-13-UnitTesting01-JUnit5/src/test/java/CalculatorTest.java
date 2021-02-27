import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    //For Unit testing here, generated by IntelliJ-Junit5

    @Test
    void testCase1(){
        assertEquals(20, 20);
    }
    @Test
    void testCase2(){
        assertTrue(Calculator.operator.endsWith("add"));
    }
    @Test
    void testCase3(){
        assertArrayEquals(new int[]{1,2,3}, new int[]{1,2,3}, "Array Equal Test");
    }
    @Test
    void testCase4(){
        String nullString = null;
        String notNullString="Cybertek:";
        assertNotNull(notNullString, "Null test");
//        assertNotNull(nullString, "Null test");
        assertNull(nullString, "Null testing");

    }
    @Test
    void add() {
        int actual = Calculator.add(2,3);
        assertEquals(5, actual);
    }
}