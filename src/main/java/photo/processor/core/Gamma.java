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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = lutTable.getValue(color.getRed());
                int g = lutTable.getValue(color.getGreen());
                int b = lutTable.getValue(color.getBlue());
                Color resultColor = new Color(r, g, b);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }
}
