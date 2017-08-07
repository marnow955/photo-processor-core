package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramEqualization extends PhotoProcessor {

    private ImageProbabilityDistribution redDistribution;
    private LUTTable redLUTTable;
    private ImageProbabilityDistribution greenDistribution;
    private LUTTable greenLUTTable;
    private ImageProbabilityDistribution blueDistribution;
    private LUTTable blueLUTTable;

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        ImageHistogram histogram = new ImageHistogram(image);
        redDistribution = new ImageProbabilityDistribution(histogram.getRedHistogram(), height * width);
        greenDistribution = new ImageProbabilityDistribution(histogram.getGreenHistogram(), height * width);
        blueDistribution = new ImageProbabilityDistribution(histogram.getBlueHistogram(), height * width);
        redLUTTable = new LUTTable(this::redLUTCondition);
        greenLUTTable = new LUTTable(this::greenLUTCondition);
        blueLUTTable = new LUTTable(this::blueLUTCondition);
    }

    private double redLUTCondition(int index) {
        double firstNonZeroDistribution;
        int i = 0;
        do {
            firstNonZeroDistribution = redDistribution.getValue(i++);
        } while (firstNonZeroDistribution == 0);
        return ((redDistribution.getValue(index) - firstNonZeroDistribution) / (1 - firstNonZeroDistribution)) * 255;
    }

    private double greenLUTCondition(int index) {
        double firstNonZeroDistribution;
        int i = 0;
        do {
            firstNonZeroDistribution = greenDistribution.getValue(i++);
        } while (firstNonZeroDistribution == 0);
        return ((greenDistribution.getValue(index) - firstNonZeroDistribution) / (1 - firstNonZeroDistribution)) * 255;
    }

    private double blueLUTCondition(int index) {
        double firstNonZeroDistribution;
        int i = 0;
        do {
            firstNonZeroDistribution = blueDistribution.getValue(i++);
        } while (firstNonZeroDistribution == 0);
        return ((blueDistribution.getValue(index) - firstNonZeroDistribution) / (1 - firstNonZeroDistribution)) * 255;
    }

    @Override
    protected void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = redLUTTable.getValue(color.getRed());
                int g = greenLUTTable.getValue(color.getGreen());
                int b = blueLUTTable.getValue(color.getBlue());
                Color resultColor = new Color(r, g, b);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }
}
