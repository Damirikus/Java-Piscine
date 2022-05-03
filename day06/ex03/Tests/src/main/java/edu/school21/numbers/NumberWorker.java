package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {

        if (number < 2){
            throw new IllegalNumberException();
        }
        if ((number & 1) == 0){
            return false;
        }
        for (int i = 3; i*i <= number; i +=2){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {

        if (number < 0)
            return 0;
        int res = 0;
        while (number != 0){
            int t = number % 10;
            res += t;
            number /= 10;
        }
        return res;
    }



    public static class IllegalNumberException extends RuntimeException{
        public IllegalNumberException(){}
    }
}
