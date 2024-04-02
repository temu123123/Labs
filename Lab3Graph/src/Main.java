import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;

public class Main {
    public static void main(String[] args) {
           int[] rgb = new int[]{0,50,5};
           int[] cmyk = rgbToCmyk(rgb);
           for(int i = 0; i < 4; i++){
               System.out.println(cmyk[i]);
           }
           int[] rgb1 = cmykToRgb(cmyk);
        for(int i = 0; i < 3; i++){
            System.out.println(rgb1[i]);
        }
    }
    private static int[] rgbToCmyk(int[] rgb){
        double persentageR = rgb[0] / 255.0 * 100;
        double persentageG = rgb[1] / 255.0 * 100;
        double persentageB = rgb[2] / 255.0 * 100;

        double k = 100 - Math.max(Math.max(persentageR,persentageB), persentageG);

        int c = (int)((100 - persentageR - k) / (100 - k) * 100);
        int m = (int)((100 - persentageG - k) / (100 - k) * 100);
        int y = (int)((100 - persentageB - k) / (100 - k) * 100);

        return new int[]{c, m, y, (int)k };
    }
    private static int[] cmykToRgb(int[] cmyk){
        double black = 1 - cmyk[3] / 100.0;
        double red = 255 * (1 - cmyk[0] / 100.0) * black;
        double green = 255 * (1 - cmyk[1] / 100.0) * black;
        double blue = 255 * (1 - cmyk[2] / 100.0) * black;

        int R = (int) Math.round(red);
        int G = (int) Math.round(green);
        int B = (int) Math.round(blue);

        return new int[]{R , G , B};
    }
}
