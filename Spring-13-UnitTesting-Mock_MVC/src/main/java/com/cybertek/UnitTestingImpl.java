package com.cybertek;

import java.util.Arrays;

public class UnitTestingImpl {
    public int calculateSum(int[] data){
        int sum = 0;
        return Arrays.stream(data).sum();
    }
}
