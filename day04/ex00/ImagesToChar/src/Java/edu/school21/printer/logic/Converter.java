package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Converter {
    private BufferedImage bufferedImage;
    private int height;
    private int width;
    private char black;
    private char white;



    public Converter(File path, char white, char black) {
        try {
            this.bufferedImage = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.black = black;
        this.white = white;

    }

    public void printImage()  {

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (bufferedImage.getRGB(j,i) == -1){
                    System.out.print(white);
                } else {
                    System.out.print(black);
                }
            }
            System.out.println();
        }

    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char getBlack() {
        return black;
    }

    public void setBlack(char black) {
        this.black = black;
    }

    public char getWhite() {
        return white;
    }

    public void setWhite(char white) {
        this.white = white;
    }
}
