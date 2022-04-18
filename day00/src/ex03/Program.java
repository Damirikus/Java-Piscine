package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String week = "Week ";
        int d = 1;
        long all = 0;
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            if (str.equals("42")){
                break;
            }
            String temp = week + d;
            if (!str.equals(temp)){
                System.out.println("IllegalArgument");
                System.exit(-1);
            }
            int res = 9;
            for (int i = 0; i < 5; i++){
                int t = scanner.nextInt();
                if (t < res)
                    res = t;
            }
            scanner.nextLine();
            long rr = res;
            all += full(rr, d);

            d++;
        }

        for (int i =1; i <= 18; i ++){
            long rem = all % 10;
            if (rem == 0){
                break;
            }
            System.out.print("Week " + i);
            for (int k = 0; k < rem; k++){
                System.out.print("=");
            }
            System.out.println(">");
            all /= 10;
        }
    }

    static long full(long a, int d){
        for (int i = 1; i < d; i++){
            a *= 10;
        }
        return a;
    }
}
