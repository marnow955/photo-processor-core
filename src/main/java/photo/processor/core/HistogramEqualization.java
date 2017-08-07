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
        LUTTable[] rgbLUTTable = {redLUTTable, greenLUTTable, blueLUTTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
