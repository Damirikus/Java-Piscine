package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int d = scanner.nextInt();

        if (d <= 1){
            System.err.println("IllegalArgument");
            System.exit(1);
        }
        if ((d & 1) == 0){
            System.out.println("false 0");
            return;
        }
        int count = 0;
        for (int i = 3; i*i <= d; i +=2){
            count++;
            if (d % i == 0){
                System.out.println("false " + count);
                return;
            }
        }
        System.out.println("true " + count);
    }
}


