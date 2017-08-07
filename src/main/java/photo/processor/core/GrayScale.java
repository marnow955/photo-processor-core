package photo.processor.core;

import java.awt.*;

public class GrayScale extends PhotoProcessor {

    @Override
    protected void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int rgb = (color.getRed()+color.getGreen()+color.getBlue())/3;
//YUV:          int rgb = (int) (0.299*color.getRed() + 0.587*color.getGreen() + 0.114*color.getBlue());
                Color resultColor = new Color(rgb, rgb, rgb);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }
}
