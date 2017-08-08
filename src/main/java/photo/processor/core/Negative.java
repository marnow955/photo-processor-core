package photo.processor.core;

import java.awt.image.BufferedImage;

public class Negative extends PhotoProcessor {

    private LUTTable lutTable;

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        lutTable = new LUTTable(this::negativeLUTCondition);
    }

    private double negativeLUTCondition(int index) {
        return 255 - index;
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {lutTable, lutTable, lutTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
