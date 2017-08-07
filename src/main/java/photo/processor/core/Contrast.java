package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Contrast extends PhotoProcessor {

    private double lutContrastRate;
    private LUTTable lutTable;

    public Contrast() {
        lutContrastRate = 1.0;
    }

    public Contrast(double lutContrastRate) {
        this.lutContrastRate = lutContrastRate;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        lutTable = new LUTTable(this::lutCondition);
    }

    private double lutCondition(int index) {
        return lutContrastRate*(index-255/2) + (255/2);
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {lutTable, lutTable, lutTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
