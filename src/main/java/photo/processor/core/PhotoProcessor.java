package photo.processor.core;

import java.awt.image.BufferedImage;

public interface PhotoProcessor {

    public BufferedImage getTransformedImage(BufferedImage image);
}
