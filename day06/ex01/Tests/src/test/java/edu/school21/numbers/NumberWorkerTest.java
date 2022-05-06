package edu.school21.numbers;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class NumberWorkerTest {
    private static final NumberWorker NUMBER_WORKER = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 })
    @Tag("prime")
    void isPrimeForPrimes(int number) {
        assertTrue(NUMBER_WORKER.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 10, 24, 33, 39, 49, 54, 65, 68, 69, 77, 78, 81, 69, 84, 96 })
    @Tag("prime")
    void isPrimeForNotPrimes (int number) {
        assertFalse(NUMBER_WORKER.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0, -88, 1})
    @Tag("prime")
    void isPrimeForIncorrectNumbers(int number) {
       assertThrows(IllegalNumberException.class, () -> NUMBER_WORKER.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = ',', numLinesToSkip = 1)
    @Tag("sum")
    void checkDigitsSum(int number, int sum) {
        assertEquals(NUMBER_WORKER.digitsSum(number), sum);
    }
}