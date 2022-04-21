package ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        String filename = "src/ex00/signatures.txt";
        String outFile = "result.txt";
        File file = new File(filename);
        Map<String, String> signatures = new HashMap<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String str = scanner.nextLine();

            String arr[] = str.split(",");
            String[] dig = arr[1].split(" ");

            StringBuilder builder = new StringBuilder();

            for (int i = 1; i < dig.length; i++){
                if (dig[i].charAt(0) == '0'){
                    String t = dig[i].substring(1);
                    dig[i] = t;
                }
                builder.append(dig[i]);
            }
            signatures.put(arr[0], builder.toString());
        }
        scanner.close();

        Scanner scanner1 = new Scanner(System.in);
        while (true){
            String str = scanner1.nextLine();
            if (str.equals("42")){
                break;
            }
            try(InputStream inputStream = new FileInputStream(str);
                OutputStream outputStream = new FileOutputStream(outFile, true)) {

                byte[] b = new byte[20];
                int real = inputStream.read(b);

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < real; i++){
                    builder.append(Integer.toHexString(b[i]).toUpperCase());
                }
                boolean flag = false;
                for (String key : signatures.keySet()){
                    flag = builder.toString().contains(signatures.get(key));
                    if (flag){
                        System.out.println("PROCESSED");
                        outputStream.write(key.getBytes());
                        outputStream.write("\n".getBytes());
                        break;
                    }
                }
                if (!flag)
                    System.out.println("UNDEFINED");
            } catch (FileNotFoundException e) {
                System.out.println("File not exists!");
            }
        }
    }
}
