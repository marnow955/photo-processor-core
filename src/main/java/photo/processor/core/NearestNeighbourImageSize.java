package photo.processor.core;

import java.awt.image.BufferedImage;

public class NearestNeighbourImageSize extends PhotoProcessor {

    protected int newWidth;
    protected int newHeight;
    protected double ratioX;
    protected double ratioY;

    public NearestNeighbourImageSize(int newWidth, int newHeight) {
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        resultImg = new BufferedImage(newWidth, newHeight, image.getType());
        ratioX = (double) image.getWidth()/newWidth;
        ratioY = (double) image.getHeight()/newHeight;
    }

    @Override
    protected void transform() {
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int originX = (int) (x*ratioX);
                int originY = (int) (y*ratioY);
                int rgb = image.getRGB(originX, originY);
                resultImg.setRGB(x, y, rgb);
            }
        }
    }
}
