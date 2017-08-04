package photo.processor.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayScale implements PhotoProcessor {

    private BufferedImage image;
    private BufferedImage resultImg;
    private int width;
    private int height;

    @Override
    public BufferedImage getTransformedImage(BufferedImage image) {
        init(image);
        transform();
        return resultImg;
    }

    private void init(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        resultImg = new BufferedImage(width, height, image.getType());
    }

    private void transform() {
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
