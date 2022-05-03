package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    NumberWorker numberWorker = new NumberWorker();


    @ParameterizedTest(name = "{index} - {0} is a prime")
    @ValueSource(ints = {7, 31, 127})
    void isPrimeForPrimes(int number){
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest(name = "{index} - {1} is a prime")
    @ValueSource(ints = {12, 38 , 169})
    void isPrimeForNotPrimes(int number){
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest(name = "{index} - {2} is a prime")
    @ValueSource(ints = {-44, 0, 1})
    void isPrimeForIncorrectNumbers(int number){
        assertThrows(NumberWorker.IllegalNumberException.class, ()-> numberWorker.isPrime(number));
    }


    @ParameterizedTest(name = "{index} - {3} is a prime")
    @CsvFileSource(resources = "/data.csv")
    void digitSum(int number, int res) {
        assertEquals(res, numberWorker.digitsSum(number));
    }
}
