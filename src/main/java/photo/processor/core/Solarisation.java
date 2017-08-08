package photo.processor.core;

import java.awt.image.BufferedImage;

public class Solarisation extends PhotoProcessor {

    private int rgbThreshold;
    private LUTTable lutTable;

    public Solarisation() {
        rgbThreshold = 127;
    }

    public Solarisation(int rgbThreshold) {
        this.rgbThreshold = rgbThreshold;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        lutTable = new LUTTable(this::solarisationLUTCondition);
    }

    private double solarisationLUTCondition(int index) {
        if (index < rgbThreshold)
            return index;
        else
            return 255 - index;
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {lutTable, lutTable, lutTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
