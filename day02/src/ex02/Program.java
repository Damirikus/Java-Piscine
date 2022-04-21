package ex02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) throws IOException {
        String [] arr = args[0].split("=");
        Path path = Paths.get(arr[1]);
        if (!Files.isDirectory(path)){
            System.out.println("Bad argument!");
            return;
        }
        String current = arr[1];
        List<File> listOfFiles = new ArrayList<>();
        System.out.println(current);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File fileStart = new File(current);
        listOfFiles.addAll(Arrays.asList(fileStart.listFiles()));

        while (true){
            String command = reader.readLine();

            if (command.equals("ls")){
                listOfFiles.clear();
                File file = new File(current);
                for ( File f : file.listFiles() ){
                    listOfFiles.add(file);
                    System.out.println(f.getName() + " " + Files.size(f.toPath()) + " KB");
                }
            } else if (command.equals("exit")){
                reader.close();
                return;
            }
            else {
                String[] commands = command.split(" ");
                if (commands.length == 2){
                    if (commands[0].equals("cd")){
                        Path path1 = Paths.get(commands[1]);
                        if (path1.isAbsolute()){
                            if (Files.isDirectory(Paths.get(commands[1])) && Files.isReadable(Paths.get(commands[1]))) {
                                current = commands[1];
                                System.out.println(current);
                            }
                            else {
                                System.out.println(commands[1]);
                                System.out.println("Is not directory!");
                            }
                        } else {
                            if (Files.isDirectory(Paths.get(current + "/" + commands[1])) && Files.isReadable(Paths.get(current + "/" + commands[1]))) {
                                current = current + "/" + commands[1];
                                System.out.println(Paths.get(current).normalize());
                            }
                            else {
                                System.out.println(current + "/" + commands[1]);
                                System.out.println("Is not directory!");
                            }
                        }
                    }
                } else if (commands.length == 3){
                    if (commands[0].equals("mv")){
                        Path path1;
                        Path path2;
                        if (Paths.get(commands[1]).isAbsolute()){
                            path1 = Paths.get(commands[1]);
                        } else {
                            path1 = Paths.get(current + "/" + commands[1]).normalize();
                        }
                        if (Paths.get(commands[2]).isAbsolute()){
                            path2 = Paths.get(commands[2]);
                        } else {
                            path2 = Paths.get(current + "/" + commands[2]).normalize();
                        }
                        if (Files.isDirectory(path2)){
                            path2 = Paths.get(path2 + "/" + path1.getFileName()) ;
                        }
                        try {
                            Files.move(path1, path2);
                        } catch (Exception e){
                            System.out.println("Bad arguments!");
                        }
                    }
                } else {
                    System.out.println("Command not found!");
                }
            }
        }
    }
}
