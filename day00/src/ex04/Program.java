package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] ex = scanner.nextLine().toCharArray();
        if (ex.length == 0){
            return;
        }
        int[] res = new int[65635];
        int[] chars = new int[1000];
        int[] numbers = new int[1000];
        for (char c : ex) {
            res[(int) c]++;
        }
        int len = 0;
        for (int i = 0; i < res.length; i++){
            if (res[i] > 0){
                chars[len] = i;
                numbers[len] = res[i];
                len++;
            }
        }
        int maxNumber = findMax(numbers, len);
        int max = maxNumber;
        int[] resC = new int[10];
        int[] resN = new int[10];

        int countR = 0;

        while (maxNumber > 0){
            for (int i = 0; i < len && countR < 10; i++){
                if (numbers[i] == maxNumber){
                    resC[countR] = chars[i];
                    resN[countR] = numbers[i];
                    countR++;
                }
            }
            if (countR == 10)
                break;
            maxNumber--;
        }

        ftPrintAll(resN, resC, max);
    }


    static void ftPrintAll(int[] resN, int[] resC, int max){



        int len = 0;
        while (len < 10) {
            if (resN[len] == 0)
                break;
            len++;
        }
        int[][] array = new int[11][len];

        float unit = (float) resN[0] / 10;

        array[0][0] = resN[0];
        for (int i = 1; i < 11; i++){
            array[i][0] = -1;
        }

        for (int i = 1; i < len; i++){
            int k = 0;
            float blocks = (float) resN[i]/ unit;
            k += 10 - (int)blocks;
            array[k][i] = resN[i];
            k++;
            while ( k < 11){
                array[k][i] = -1;
                k++;
            }
        }

        for (int i = 0; i < 11; i++){
            for (int k = 0; k < len; k++){
                if (array[i][k] == -1){
                    System.out.printf("%4c", '#');
                } else if (array[i][k] != 0){
                    System.out.printf("%4d", array[i][k]);
                }
            }
            System.out.println();
        }
        for (int i = 0; i < len; i++){
            System.out.printf("%4c", (char)resC[i]);
        }
    }

    static int findMax(int[] num, int m){
        int max = 0;
        max = num[0];
        for (int i =1; i < m; i++){
            if (num[i] > max)
                max = num[i];
        }
        return max;
    }
}
