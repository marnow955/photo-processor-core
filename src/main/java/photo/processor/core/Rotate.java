package photo.processor.core;

import java.awt.image.BufferedImage;

public class Rotate extends PhotoProcessor {

    public enum Direction {
        RIGHT, LEFT
    }

    private int resultWidth;
    private int resultHeight;
    private Direction direction;

    public Rotate(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected void init(BufferedImage image) {
        super.init(image);
        resultHeight = width;
        resultWidth = height;
        resultImg = new BufferedImage(resultWidth, resultHeight, image.getType());
    }

    @Override
    protected void transform() {
        int originX;
        int originY;
        for (int y = 0; y < resultHeight; y++) {
            for (int x = 0; x < resultWidth; x++) {
                if (direction == Direction.RIGHT) {
                    originX = y;
                    originY = height - 1 - x;
                } else {
                    originX = width - 1 - y;
                    originY = x;
                }
                int rgb = image.getRGB(originX, originY);
                resultImg.setRGB(x, y, rgb);
            }
        }
    }
}
