package photo.processor.core;

import java.awt.image.BufferedImage;

public class NearestNeighbourImageSize extends PhotoProcessor {

    private int width;
    private int height;
    private double ratioX;
    private double ratioY;

    public NearestNeighbourImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void init(BufferedImage image) {
        this.image = image;
        resultImg = new BufferedImage(width, height, image.getType());
        ratioX = (double) image.getWidth()/width;
        ratioY = (double) image.getHeight()/height;
    }

    @Override
    protected void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originX = (int) (x*ratioX);
                int originY = (int) (y*ratioY);
                int rgb = image.getRGB(originX, originY);
                resultImg.setRGB(x, y, rgb);
            }
        }
    }
}
