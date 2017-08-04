package photo.processor.core.color.reduce;

import java.awt.*;

public abstract class SignalNoiseApproximation extends NearestColor {

    @Override
    protected void transform() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color calculatedColorWithNoise = calculateColorWithNoise(new Color(image.getRGB(x,y)), x, y);
                Color resultColor = palette.findNearestColor(calculatedColorWithNoise);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }

    abstract Color calculateColorWithNoise(Color color, int x, int y);

}
