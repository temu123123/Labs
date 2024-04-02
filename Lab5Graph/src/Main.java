import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\temu1\\IdeaProjects\\Lab5Graph\\src\\examle.bmp";
        String outputFilePathFor45 = "C:\\Users\\temu1\\IdeaProjects\\Lab5Graph\\src\\output45_2.bmp";
        String outputFilePathFor90 = "C:\\Users\\temu1\\IdeaProjects\\Lab5Graph\\src\\output90_2.bmp";

        try {
            BufferedImage image = ImageIO.read(new File(inputFilePath));


            double angle45 = Math.toRadians(45);
            BufferedImage result45 = rotateImage(image, angle45);
            ImageIO.write(result45, "bmp", new File(outputFilePathFor45));
            System.out.println("Файл успешно обработан и сохранен: " + outputFilePathFor45);


            double angle90 = Math.toRadians(90);
            BufferedImage result90 = rotateImage(image, angle90);
            ImageIO.write(result90, "bmp", new File(outputFilePathFor90));
            System.out.println("Файл успешно обработан и сохранен: " + outputFilePathFor90);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage rotateImage(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int w = image.getWidth();
        int h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin);
        int newh = (int)Math.floor(h*cos+w*sin);
        BufferedImage result = new BufferedImage(neww, newh, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.translate((neww-w)/2, (newh-h)/2);
        g.rotate(angle, w/2, h/2);
        g.drawRenderedImage(image, null);
        return result;
    }
}
