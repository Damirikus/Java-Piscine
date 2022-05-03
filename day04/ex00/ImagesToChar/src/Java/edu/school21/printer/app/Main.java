package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3){
            System.out.println("Bad arguments!");
            return;
        }
        if (args[0].length() != 1 || args[1].length() != 1 || !Files.exists(Paths.get(args[2]))){
            System.out.println("Bad arguments!");
            return;
        }
        File file = new File(args[2]);
        if (file.isDirectory()){
            System.out.println("Bad arguments!");
            return;
        }
        Converter converter = new Converter(file, args[0].charAt(0), args[1].charAt(0));
        converter.printImage();

    }
}
