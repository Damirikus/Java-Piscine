package ex05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
//        Menu menu = new Menu();
        if (args.length == 0 || !args[0].equals("--profile=dev") && !args[0].equals("--profile=standard")){
            System.out.println("Incorrect argument!");
            System.out.println("Add '--profile=standard' or '--profile=dev'");
        } else {
            if (args[0].equals("--profile=dev")){
                devExecution();
            } else {
                standardExecution();
            }
        }
    }

    public static void devExecution(){

        while (true){
            Scanner scanner = new Scanner(System.in);
            Menu.printMenuDev();
            int index = 0;
            try {
                index = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a value from 1 to 7");
                continue;
            }
            if (index <= 0 || index > 7){
                System.out.println("Please enter a value from 1 to 7");
            }

            switch (index){
                case 1:
                    System.out.println(Menu.addUser());
                    break;
                case 2:
                    System.out.println(Menu.getBalanceById());
                    break;
                case 3:
                    System.out.println(Menu.transfer());
                    break;
                case 4:
                    Menu.getAllTransactions();
                    break;
                case 5:
                    Menu.remove();
                    break;
                case 6:
                    Menu.checkTransfers();
                    break;
                case 7:
                    return;

            }
            System.out.println("--------------------------------------------------");

        }
    }

    public static void standardExecution(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            Menu.printMenuStandard();
            int index = 0;
            try {
                index = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a value from 1 to 7");
                continue;
            }
            if (index <= 0 || index > 7){
                System.out.println("Please enter a value from 1 to 7");
            }

            switch (index){
                case 1:
                    System.out.println(Menu.addUser());
                    break;
                case 2:
                    System.out.println(Menu.getBalanceById());
                    break;
                case 3:
                    System.out.println(Menu.transfer());
                    break;
                case 4:
                    Menu.getAllTransactions();
                    break;
                case 5:
                    return;
            }
            System.out.println("--------------------------------------------------");

        }
    }
}
