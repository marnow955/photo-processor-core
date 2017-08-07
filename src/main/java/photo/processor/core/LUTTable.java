package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

class LUTTable {
    private int[] lutTable;

    LUTTable(LUTEquation equationValue) {
        lutTable = new int[256];
        for(int i=0; i<256; i++) {
            double value = equationValue.getLUTEqationValue(i);
            if (value> 255){
                lutTable[i] = 255;
            } else if (value < 0) {
                lutTable[i] = 0;
            } else {
                lutTable[i] = (int) value;
            }
        }
    }

    int getValue(int index) {
        return lutTable[index];
    }

    void setValue(int index, int value) {
        lutTable[index] = value;
    }

    static BufferedImage getTransformedImage(BufferedImage image, LUTTable[] rgbLUTTable) {
        BufferedImage resultImg = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = rgbLUTTable[0].getValue(color.getRed());
                int g = rgbLUTTable[1].getValue(color.getGreen());
                int b = rgbLUTTable[2].getValue(color.getBlue());
                Color resultColor = new Color(r, g, b);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
        return resultImg;
    }
}
