import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BMPImageProcessor {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\temu1\\IdeaProjects\\Lab2Graph\\out\\production\\Lab2Graph\\examle.bmp"; // Путь к входному BMP-файлу
        String outputFilePath = "C:\\Users\\temu1\\IdeaProjects\\Lab2Graph\\out\\production\\Lab2Graph\\output.bmp"; // Путь к выходному BMP-файлу

        try {

            byte[] bmpData = Files.readAllBytes(Paths.get(inputFilePath));


            int centerX = bmpData.length / 2;
            int centerY = bmpData.length / 2;
            int newColor = 0xFF00FF;


            for (int i = 0; i < 100; i++) {
                bmpData[centerX + i] = (byte) (newColor & 0xFF);
                bmpData[centerY + i] = (byte) ((newColor >> 8) & 0xFF);
                bmpData[centerX + i + 1] = (byte) ((newColor >> 16) & 0xFF);
            }


            Files.write(Paths.get(outputFilePath), bmpData);
            System.out.println("Файл успешно обработан и сохранен: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
