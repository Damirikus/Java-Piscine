package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;
import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter(Main.class.getResourceAsStream("/resources/it.bmp"));
        JCommander.newBuilder()
                .addObject(converter)
                .build()
                .parse(args);

        converter.printImage();
    }
}

