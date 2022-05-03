package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args[0].length() != 1 || args[1].length() != 1){
            System.out.println("Bad arguments!");
            return;
        }

        InputStream inputStream =  Main.class.getResourceAsStream("/resources/it.bmp");

        Converter converter = new Converter(inputStream, args[0].charAt(0), args[1].charAt(0));
        converter.printImage();

    }
}
