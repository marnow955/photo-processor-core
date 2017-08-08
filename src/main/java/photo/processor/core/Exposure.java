package photo.processor.core;

import java.awt.image.BufferedImage;

public class Exposure extends PhotoProcessor {

    private double exposureRate;
    private LUTTable lutTable;

    public Exposure() {
        exposureRate = 1.0;
    }

    public Exposure(double EV) {
        exposureRate = Math.pow(2, EV);
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        lutTable = new LUTTable(this::exposureLUTCondition);
    }

    private double exposureLUTCondition(int index) {
        return exposureRate*index;
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {lutTable, lutTable, lutTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
