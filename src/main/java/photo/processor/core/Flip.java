package photo.processor.core;

public class Flip extends PhotoProcessor {

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    private Orientation orientation;

    public Flip(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    protected void transform() {
        int originX;
        int originY;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (orientation == Orientation.HORIZONTAL) {
                    originX = width - 1 - x;
                    originY = y;
                } else {
                    originX = x;
                    originY = height - 1 - y;
                }
                int rgb = image.getRGB(originX, originY);
                resultImg.setRGB(x, y, rgb);
            }
        }
    }
}
