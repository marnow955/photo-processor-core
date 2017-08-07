package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

class ImageHistogram {

    private LUTTable redHistogram;
    private LUTTable greenHistogram;
    private LUTTable blueHistogram;

    ImageHistogram(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        redHistogram = new LUTTable(this::histogramCondition);
        greenHistogram = new LUTTable(this::histogramCondition);
        blueHistogram = new LUTTable(this::histogramCondition);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                redHistogram.setValue(r, redHistogram.getValue(r) + 1);
                greenHistogram.setValue(g, greenHistogram.getValue(g) + 1);
                blueHistogram.setValue(b, blueHistogram.getValue(b) + 1);
            }
        }
    }

    private double histogramCondition(int index) {
        return 0;
    }

    LUTTable getRedHistogram() {
        return redHistogram;
    }

    LUTTable getGreenHistogram() {
        return greenHistogram;
    }

    LUTTable getBlueHistogram() {
        return blueHistogram;
    }

}
