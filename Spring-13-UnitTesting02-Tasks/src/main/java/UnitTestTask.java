import java.util.ArrayList;
import java.util.List;

public class UnitTestTask {



    public String removePairs(String str){
        String result="";
        int index =0;
        if (str==null)
            throw new NullPointerException();
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
            throw new ArrayIndexOutOfBoundsException("nth number passed in arg is larger than char array.");
        }
        while(n < chrArr.length){
            result+=chrArr[n-1];
            n+=n;
        }
        System.out.println("resultArr.toString() = " + result);
        return result.toCharArray();
    }


    public String nullIfOddLength(String str){

        return (str.length()%2!=0)?null:str;
    }

    public Long convert(Long a, Long b){
        if (b==0){
            throw new ArithmeticException("cannot be divided by 0");
        }

        return (a/b)*150;
    }




}
