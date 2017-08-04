package photo.processor.core.color.reduce;

import java.awt.*;

import static photo.processor.core.color.reduce.RGBValuesValidator.validate;

public class SineSignalNoiseApproximation extends SignalNoiseApproximation {

    private int aRate = 52;
    private int alfaRate = 128;
    private int betaRate = 128;

    @Override
    Color calculateColorWithNoise(Color color, int x, int y) {
        double noiseRate = aRate * Math.sin(alfaRate * x) * Math.sin(betaRate * y);
        int r = validate((int)(color.getRed() + noiseRate));
        int g = validate((int)(color.getGreen() + noiseRate));
        int b = validate((int)(color.getBlue() + noiseRate));
        return new Color(r, g, b);
    }
}
