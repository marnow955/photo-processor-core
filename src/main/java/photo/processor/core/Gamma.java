package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gamma extends PhotoProcessor {

    private double gammaRate;
    private LUTTable lutTable;

    public Gamma() {
        gammaRate = 1.0;
    }

    public Gamma(double gammaRate) {
        this.gammaRate = gammaRate;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        lutTable = new LUTTable(this::lutCondition);
    }

    private int lutCondition(int index) {
        return (int) (255 * Math.pow((index / 255.0), (1 / gammaRate)));
    }

    @Override
    protected void transform() {
        LUTTable[] rgbLUTTable = {lutTable, lutTable, lutTable};
        resultImg = LUTTable.getTransformedImage(image, rgbLUTTable);
    }
}
