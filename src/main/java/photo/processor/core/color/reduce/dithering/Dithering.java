package photo.processor.core.color.reduce.dithering;

import photo.processor.core.color.reduce.NearestColor;
import photo.processor.core.color.reduce.RGBErrors;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Dithering extends NearestColor {

    protected RGBErrors[][] errorTab;

    @Override
    protected void init(BufferedImage image) {
        super.init(image);

        errorTab = new RGBErrors[width][height];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                errorTab[x][y] = new RGBErrors(Color.BLACK);
    }

    @Override
    protected void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color rgbWithErrors = calculateColor(image.getRGB(x, y), errorTab[x][y]);
                Color resultColor = palette.findNearestColor(rgbWithErrors);
                resultImg.setRGB(x, y, resultColor.getRGB());
                RGBErrors currentError = subColors(rgbWithErrors, resultColor);

                propagation(x, y, currentError);
            }
        }
    }

    private Color calculateColor(int colorRGB, RGBErrors currentError) {
        Color color = new Color(colorRGB);
        int r = validate(color.getRed() + currentError.getRedError());
        int g = validate(color.getGreen() + currentError.getGreenError());
        int b = validate(color.getBlue() + currentError.getBlueError());

        return new Color(r, g, b);
    }

    private int validate(int rgbValue) {
        if (rgbValue < 0)
            return 0;
        else if (rgbValue > 255)
            return 255;
        else
            return rgbValue;
    }

    private RGBErrors subColors(Color minuend, Color subtrahend) {
        return new RGBErrors(
                minuend.getRed() - subtrahend.getRed(),
                minuend.getGreen() - subtrahend.getGreen(),
                minuend.getBlue() - subtrahend.getBlue()
        );
    }

    abstract void propagation(int x, int y, RGBErrors currentError);
}
