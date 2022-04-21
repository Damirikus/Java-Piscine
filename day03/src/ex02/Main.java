package ex02;

public class Main {

    private static int[] res;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2){
            System.out.println("Bad arguments!");
            return;
        }
        String[] str1 = args[0].split("=");
        String[] str2 = args[1].split("=");
        if (str1.length != 2 || str2.length != 2){
            System.out.println("Bad arguments!");
            return;
        }
        int size = Integer.parseInt(str1[1]);
        int countOfThreads = Integer.parseInt(str2[1]);
        if (size < 1 || size > 2000000){
            System.out.println("Bad array size!");
            return;
        }
        if (countOfThreads < 0 || countOfThreads > size){
            System.out.println("Bad number of threads");
            return;
        }

        int[] array = new int[size];
        for (int i = 0; i < size; i ++){
            array[i] = (int) (Math.random() * 1000) + 1;
        }
        long mainResult = 0;
        for (int j : array) {
            mainResult += j;
        }
        System.out.println("Sum: " + mainResult);

        int range = size / countOfThreads;
        int remain = size % countOfThreads;
        int last = range + remain;
        int sumOfRange = 0;

        res = new int[countOfThreads];

        Thread[] threads = new Thread[countOfThreads];
        int t = 0;
        for (int i = 0; i < countOfThreads; i++){

            if (i + 1 == countOfThreads){
                threads[i] = new Thread(new Range(sumOfRange, last, i, array));
                t = i;
            } else {
                threads[i] = new Thread(new Range(sumOfRange, range, i, array));
                sumOfRange += range;
            }
            threads[i].start();

        }
        threads[t].join();

        mainResult = 0;
        int sumOfRange2 = 0;
        for (int r = 0; r < res.length; r++) {
            mainResult += res[r];
            if (r + 1 == res.length){
                System.out.println("Thread " + (r + 1) + ": from " + sumOfRange2 + " to " +
                        (sumOfRange2 + last) + " sum is " + res[r]);
                break;
            }
            System.out.println("Thread " + (r + 1) + ": from " + sumOfRange2 + " to " +
                    (sumOfRange2 + range) + " sum is " + res[r]);
            sumOfRange2 += range;
        }
        System.out.println("Sum by threads: " + mainResult);


    }

    public static class Range implements Runnable {
        int sumOfRange;
        int last;
        int i;
        int[] array;

        public Range(int sumOfRange, int last, int i, int[] array) {
            this.sumOfRange = sumOfRange;
            this.last = last;
            this.i = i;
            this.array = array;
        }

        @Override
        public void run() {
            for (int k = sumOfRange; k < (sumOfRange + last); k ++){
                res[i] += array[k];
            }
        }
    }

}


