package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcolor.Attribute;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

@Parameters(separators = "=")
public class Converter {
    private BufferedImage bufferedImage;
    private final int height;
    private final int width;

    @Parameter(names = {"--white", "-white"}, required = true)
    private String white;
    @Parameter(names = {"--black", "-black"}, required = true)
    private String black;

    public Converter(InputStream path) {
        try {
            this.bufferedImage = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
    }

    public int getColor(String color){
        switch (color){
            case "RED":
                return 160;
            case "WHITE":
                return 255;
            case "GREEN":
                return 2;
            case "BLUE":
                return 27;
            case "YELLOW":
                return 11;
            case "ORANGE":
                return 202;
            case "PURPLE":
                return 57;
            default: return 0;
        }
    }

    public void printImage()  {


        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (bufferedImage.getRGB(j,i) == -1){
                    Attribute bkgColor = BACK_COLOR(getColor(white));
                    System.out.print(colorize("   ", bkgColor));
                } else {
                    Attribute bkgColor = BACK_COLOR(getColor(black));
                    System.out.print(colorize("   ", bkgColor));
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

    public int getWidth() {
        return width;
    }

}