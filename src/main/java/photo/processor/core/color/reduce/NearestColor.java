package photo.processor.core.color.reduce;

import photo.processor.core.PhotoProcessor;

import java.awt.*;

public class NearestColor extends PhotoProcessor {

    protected ColorsPalette palette;

    public NearestColor() {
        palette = new ColorsPalette(3);
    }

    public NearestColor(int numberOfColorsPaletteLevels) {
        palette = new ColorsPalette(numberOfColorsPaletteLevels);
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
