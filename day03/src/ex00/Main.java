package ex00;

public class Main {
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

            Thread thread1 = new Thread(new Printer("Egg", count));
            Thread thread2 = new Thread(new Printer("Hen", count));

            thread1.start();
            thread2.start();


            thread1.join();
            thread2.join();

            for (int i = 0; i < count; i++){
                System.out.println("Human");
            }

        }

    }

    static class Printer implements Runnable{

        private final String name;
        private final int count;

        public Printer(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < this.count; i++){
                System.out.println(this.name);
            }
        }
    }
}


