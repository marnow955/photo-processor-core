package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Contrast implements PhotoProcessor {

    private BufferedImage image;
    private BufferedImage resultImg;
    private int width;
    private int height;

    private double lutContrastRate;
    private int[] LUTTable;

    public Contrast() {
        lutContrastRate = 1.0;
    }

    public Contrast(double lutContrastRate) {
        this.lutContrastRate = lutContrastRate;
    }

    @Override
    public BufferedImage getTransformedImage(BufferedImage image) {
        init(image);
        transform();
        return resultImg;
    }

    private void init(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        resultImg = new BufferedImage(width, height, image.getType());
        makeLUTTable();
    }

    private void makeLUTTable() {
        LUTTable = new int[256];
        for(int i=0; i<256; i++) {
            double value = lutContrastRate*(i - 255/2) + (255/2);
            if (value> 255){
                LUTTable[i] = 255;
            } else if (value < 0) {
                LUTTable[i] = 0;
            } else {
                LUTTable[i] = (int) value;
            }
        }
    }

    private void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = LUTTable[color.getRed()];
                int g = LUTTable[color.getGreen()];
                int b = LUTTable[color.getBlue()];
                Color resultColor = new Color(r, g, b);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }
}
