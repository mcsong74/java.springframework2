import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class CalculatorParameterizedTest {



    @ParameterizedTest
    @ValueSource(strings = {"Java", "JS", "TS"})
    void testCase1(String args){
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {3,6,9})
    void testCase2(int num){
        Assertions.assertEquals(0, num%3);
    }

    @ParameterizedTest
    @ValueSource(strings={"Java", "JS", "TS"})
//    @EmptySource //checking empty
//    @NullSource //checking null
    @NullAndEmptySource //checking null and empty
    void testCase3(String args){
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testCase4(String args){
        Assertions.assertNotNull(args);
    }

    //provider for MethodSource
    static String[] stringProvider(){
        String[] strArr= {"Java", "JS", "TS"};
        return strArr;
    }

    @ParameterizedTest
    @CsvSource({"10,20,30", "20, 20, 40", "30, 20, 100"})
    void testCase5(int num1, int num2, int result){
        Assertions.assertEquals(result, Calculator.add(num1, num2));
    }

    void testCase6(){

    }



}
