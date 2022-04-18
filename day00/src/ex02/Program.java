package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int res = 0;
        while (scanner.hasNext()){
            int d = scanner.nextInt();
            if (d == 42){
                System.out.println("Count of coffee-request - " + res);
                return;
            }
            int dig = digit(d);
            if (isPrime(dig)){
                res++;
            }
        }
    }

    private static boolean isPrime(int d){
        if (d <= 1 || (d & 1) == 0){
            return false;
        }
        for (int i = 3; i*i <= d; i +=2){
            if (d % i == 0){
                return false;
            }
        }
        return true;
    }

    private static int digit(int a){
        int res = 0;
        while (a != 0){
            int t = a % 10;
            res += t;
            a /= 10;
        }
        return res;
    }
}
