package ex01;

import java.io.*;
import java.util.*;

public class Program {

    public static void main(String[] args) throws IOException {

        Set<String> set = new HashSet<>();
        List<String> list = null;
        int [] res1 = null;
        int [] res2 = null;

        if (args.length != 2){
            System.out.println("Bad arguments!");
        }

        try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])))) {

            while (reader1.ready()){
                String[] arr = reader1.readLine().split(" ");
                set.addAll(Arrays.asList(arr));
            }

            while (reader2.ready()){
                String[] arr = reader2.readLine().split(" ");
                set.addAll(Arrays.asList(arr));
            }

        } catch (IOException e) {
            System.out.println("Bad arguments!");
        }

        try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
             BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])))) {

            list = new ArrayList<>(set);

            res1 = new int[set.size()];
            res2 = new int[set.size()];

            while (reader1.ready()){
                String[] arr = reader1.readLine().split(" ");
                for (String s : arr){
                    if (list.contains(s)){
                        int index = list.indexOf(s);
                        res1[index]++;
                    }
                }
            }

            while (reader2.ready()){
                String[] arr = reader2.readLine().split(" ");
                for (String s : arr){
                    if (list.contains(s)){
                        int index = list.indexOf(s);
                        res2[index]++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Bad arguments!");
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Dictionary.txt")))) {
            for (String str : list){
                writer.write(str);
                writer.write("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long sum = 0;
        long powSum1 = 0;
        long powSum2 = 0;
        for (int i = 0; i < res1.length; i++){
            sum += ((long) res1[i] * res2[i]);
            powSum1 += ((long) res1[i] * res1[i]);
            powSum2 += ((long) res2[i] * res2[i]);
        }

        double denominator = (Math.sqrt(powSum1) * Math.sqrt(powSum2));
        double similarity = sum / denominator;

        String result = String.format("Similarity = %.2f", ((int)(similarity * 100) / 100.0));
        System.out.println(result);
    }
}
