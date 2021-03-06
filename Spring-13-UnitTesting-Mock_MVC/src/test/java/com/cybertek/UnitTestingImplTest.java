package com.cybertek;

import com.cybertek.repository.DataRepository;
import com.cybertek.repository.DataRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnitTestingImplTest {

    @Test
    void calculateSum() {
        UnitTestingImpl unitTesting = new UnitTestingImpl();
        int actual = unitTesting.calculateSum(new int[]{1,2,3});
        assertEquals(6, actual);
    }

    @Test
    void calculateSumUsingDataService(){
        // this has data layer + method  testing, Unit testing is simplest say possible, so need to sepearte layer
        DataRepositoryImpl dataRepository = new DataRepositoryImpl();
        UnitTestingImpl unitTesting = new UnitTestingImpl(dataRepository);
        int actual = unitTesting.calculateSumUsingDtaService();
        assertEquals(6, actual);
    }

    @Mock
    DataRepository dataRepository;
    @InjectMocks
    UnitTestingImpl unitTesting;

    @Test
    void calculateSumUsingDataServiceUsingMock(){
        //separated data layer using Mock to test unitTesting.calculateSumUsingDtaService()
        int sum = 0;
        when(dataRepository.findAll()).thenReturn(new int[] {1,2,3});
        int actual = unitTesting.calculateSumUsingDtaService(); //goal for unit testing here
        assertEquals(6, actual);
    }

}