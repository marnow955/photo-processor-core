package photo.processor.core.color.reduce;

import photo.processor.core.PhotoProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NearestColor implements PhotoProcessor {

    protected ColorsPalette palette;

    protected BufferedImage image;
    protected BufferedImage resultImg;

    protected int width;
    protected int height;

    public NearestColor() {
        palette = new ColorsPalette(3);
    }

    public NearestColor(int numberOfColorsPaletteLevels) {
        palette = new ColorsPalette(numberOfColorsPaletteLevels);
    }

    @Override
    public BufferedImage getTransformedImage(BufferedImage image) {
        init(image);
        transform();
        return resultImg;
    }

    protected void init(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        resultImg = new BufferedImage(width, height, image.getType());
    }

    protected void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color resultColor = palette.findNearestColor(new Color(image.getRGB(x, y)));
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }
}
