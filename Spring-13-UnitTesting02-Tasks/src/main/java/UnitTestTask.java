import java.util.ArrayList;
import java.util.List;

public class UnitTestTask {



    public String removePairs(String str){
        String result="";
        int index =0;
        result = str.substring(0, index+1);

        while(str.length() > 1 && index<str.length()-1){
            if(!str.substring(index, index+1).equals(str.substring(index+1, index+2))){
                result += str.substring(index+1, index+2);
            }
            index++;
        }

        return result;
    }

    public char[] everyNthChar(char[] chrArr, Integer n){
        String result="";
        if(n>chrArr.length){
            throw new ArrayIndexOutOfBoundsException();
        }
        while(n < chrArr.length){
            result+=chrArr[n-1];
            n+=n;
        }
        System.out.println("resultArr.toString() = " + result);
        return result.toCharArray();
    }


    public String nullIfOddLength(String str){
        return null;
    }

    public Long convert(Long a, Long b){
        return -1L;
    }




}
