package photo.processor.core;

import java.awt.image.BufferedImage;

public abstract class PhotoProcessor {

    protected BufferedImage image;
    protected BufferedImage resultImg;
    protected int width;
    protected int height;

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

    protected abstract void transform();
}
