package photo.processor.core.size;

import photo.processor.core.PhotoProcessor;

import java.awt.image.BufferedImage;

public class Crop extends PhotoProcessor {

    private int resultWidth;
    private int resultHeight;
    private int originXStart;
    private int originYStart;

    private Crop(int originXStart, int originYStart) {
        this.originXStart = originXStart;
        this.originYStart = originYStart;
    }

    public static Crop CropXY(int originXStart, int originYStart, int originXEnd, int originYEnd) {
        Crop crop = new Crop(originXStart, originYStart);
        crop.setSizeFromCoordinates(originXEnd, originYEnd);
        return crop;
    }

    public static Crop CropSize(int originX, int originY, int width, int height) {
        Crop crop = new Crop(originX, originY);
        crop.setSizeFromDimensions(width, height);
        return crop;
    }

    private void setSizeFromCoordinates(int originXEnd, int originYEnd) {
        resultWidth = originXEnd - originXStart;
        resultHeight = originYEnd - originYStart;
    }

    private void setSizeFromDimensions(int width, int height) {
        resultWidth = width;
        resultHeight = height;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        resultImg = new BufferedImage(resultWidth, resultHeight, image.getType());
    }

    @Override
    protected void transform() {
        for (int y = 0; y < resultHeight; y++) {
            for (int x = 0; x < resultWidth; x++) {
                resultImg.setRGB(x, y, image.getRGB(originXStart + x, originYStart + y));
            }
        }
    }
}
