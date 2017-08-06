package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brightness implements PhotoProcessor{

    private BufferedImage image;
    private BufferedImage resultImg;
    private int width;
    private int height;

    private int lutBrightnessRate;
    private int[] LUTTable;

    public Brightness() {
        lutBrightnessRate = 0;
    }

    public Brightness(int lutBrightnessRate) {
        this.lutBrightnessRate = lutBrightnessRate;
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
            double value = i + lutBrightnessRate;
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
