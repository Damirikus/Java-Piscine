package ex01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 1){
            String[] arr = args[0].split("=");
            if (arr.length > 2){
                return;
            }
            if (!arr[0].equals("--count")){
                return;
            }
            int count = Integer.parseInt(arr[1]);

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        produce(count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        consumer(count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

        }

    }

    private static void produce(int count) throws InterruptedException {

        for (int i = 0; i < count; i++){
            queue.put(1);
            System.out.println("Egg");

        }
    }

    private static void consumer(int count) throws InterruptedException {
        for (int i = 0; i < count; i++){
            Thread.sleep(1);
            queue.take();
            System.out.println("Hen");
        }
    }
}

