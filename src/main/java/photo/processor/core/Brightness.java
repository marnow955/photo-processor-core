package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brightness extends PhotoProcessor{

    private int lutBrightnessRate;
    private LUTTable lutTable;

    public Brightness() {
        lutBrightnessRate = 0;
    }

    public Brightness(int lutBrightnessRate) {
        this.lutBrightnessRate = lutBrightnessRate;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        lutTable = new LUTTable(this::lutCondition);
    }

    private double lutCondition(int index) {
        return index + lutBrightnessRate;
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {lutTable, lutTable, lutTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
