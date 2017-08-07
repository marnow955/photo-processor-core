package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramStretching extends PhotoProcessor {

    private int minRValue;
    private int maxRValue;
    private LUTTable redLUTTable;
    private int minGValue;
    private int maxGValue;
    private LUTTable greenLUTTable;
    private int minBValue;
    private int maxBValue;
    private LUTTable blueLUTTable;

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        findMinMaxRGBValues();
        redLUTTable = new LUTTable(this::redLUTCondition);
        greenLUTTable = new LUTTable(this::greenLUTCondition);
        blueLUTTable = new LUTTable(this::blueLUTCondition);
    }

    private void findMinMaxRGBValues() {
        minRValue = 255;
        minGValue = 255;
        minBValue = 255;
        maxRValue = 0;
        maxGValue = 0;
        maxBValue = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                if (r < minRValue) {
                    minRValue = r;
                }
                if (r > maxRValue) {
                    maxRValue = r;
                }
                if (g < minGValue) {
                    minGValue = g;
                }
                if (g > maxGValue) {
                    maxGValue = g;
                }
                if (b < minBValue) {
                    minBValue = b;
                }
                if (b > maxBValue) {
                    maxBValue = b;
                }
            }
        }
    }

    private double redLUTCondition(int index) {
        return (255.0/(maxRValue - minRValue))*(index - minRValue);
    }

    private double greenLUTCondition(int index) {
        return (255.0/(maxGValue - minGValue))*(index - minGValue);
    }

    private double blueLUTCondition(int index) {
        return (255.0/(maxBValue - minBValue))*(index - minBValue);
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {redLUTTable, greenLUTTable, blueLUTTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
