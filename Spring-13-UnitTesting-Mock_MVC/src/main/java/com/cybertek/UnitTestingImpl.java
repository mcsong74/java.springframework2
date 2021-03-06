package com.cybertek;

import com.cybertek.repository.DataRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@NoArgsConstructor
@Setter
@Getter
public class UnitTestingImpl {

    DataRepository dataRepository;

    public UnitTestingImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    public int calculateSum(int[] data){
        int sum = 0;
        return Arrays.stream(data).sum();
    }

    public int calculateSumUsingDtaService(){
        int sum = 0;
        return Arrays.stream(dataRepository.findAll()).sum();
    }
}
