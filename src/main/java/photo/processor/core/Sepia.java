package photo.processor.core;

import photo.processor.core.color.reduce.RGBValuesValidator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sepia extends PhotoProcessor {

    private int fillFactor; // 20 - 40

    public Sepia() {
        fillFactor = 30;
    }

    public Sepia(int fillFactor) {
        this.fillFactor = fillFactor;
    }

    @Override
    protected void transform() {
        GrayScale grayScale = new GrayScale();
        BufferedImage grayImage = grayScale.getTransformedImage(image);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(grayImage.getRGB(x, y));
                int r = RGBValuesValidator.validate(color.getRed() + 2*fillFactor);
                int g = RGBValuesValidator.validate(color.getGreen() + fillFactor);
                int b = color.getBlue();
                Color resultColor = new Color(r, g, b);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }

    }
}
