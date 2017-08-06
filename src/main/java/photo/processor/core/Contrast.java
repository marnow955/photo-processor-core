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
