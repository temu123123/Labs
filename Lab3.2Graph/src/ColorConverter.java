import java.util.Random;

public class ColorConverter {
    public static void main(String[] args) {
        // Генерация случайного цвета RGB

        int r = 150;
        int g = 150;
        int b = 150;

        // Преобразование RGB в XYZ
        double[] xyz = RGBtoXYZ.convert(r, g, b);
        System.out.println("RGB: (" + r + ", " + g + ", " + b + ")");
        System.out.println("XYZ: (" + xyz[0] + ", " + xyz[1] + ", " + xyz[2] + ")");

        // Преобразование XYZ обратно в RGB
        int[] rgb = XYZtoRGB.convert(xyz[0], xyz[1], xyz[2]);
        System.out.println("Converted back to RGB: (" + rgb[0] + ", " + rgb[1] + ", " + rgb[2] + ")");
    }
}

class RGBtoXYZ {
    public static double[] convert(int r, int g, int b) {
        double rr = r / 255.0;
        double gg = g / 255.0;
        double bb = b / 255.0;

        // Преобразование в линейные значения RGB
        rr = rr > 0.04045 ? Math.pow((rr + 0.055) / 1.055, 2.4) : rr / 12.92;
        gg = gg > 0.04045 ? Math.pow((gg + 0.055) / 1.055, 2.4) : gg / 12.92;
        bb = bb > 0.04045 ? Math.pow((bb + 0.055) / 1.055, 2.4) : bb / 12.92;

        // Преобразование в XYZ
        double x = rr * 0.4124 + gg * 0.3576 + bb * 0.1805;
        double y = rr * 0.2126 + gg * 0.7152 + bb * 0.0722;
        double z = rr * 0.0193 + gg * 0.1192 + bb * 0.9505;

        return new double[]{x, y, z};
    }
}

class XYZtoRGB {
    public static int[] convert(double x, double y, double z) {
        double rr = x * 3.2406 + y * -1.5372 + z * -0.4986;
        double gg = x * -0.9689 + y * 1.8758 + z * 0.0415;
        double bb = x * 0.0557 + y * -0.2040 + z * 1.0570;

        // Преобразование в нелинейные значения RGB
        rr = rr > 0.0031308 ? 1.055 * Math.pow(rr, 1 / 2.4) - 0.055 : 12.92 * rr;
        gg = gg > 0.0031308 ? 1.055 * Math.pow(gg, 1 / 2.4) - 0.055 : 12.92 * gg;
        bb = bb > 0.0031308 ? 1.055 * Math.pow(bb, 1 / 2.4) - 0.055 : 12.92 * bb;

        // Преобразование в целочисленные значения RGB
        int r = (int) Math.round(rr * 255);
        int g = (int) Math.round(gg * 255);
        int b = (int) Math.round(bb * 255);

        return new int[]{r, g, b};
    }
}
