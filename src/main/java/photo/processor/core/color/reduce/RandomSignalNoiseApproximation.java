package photo.processor.core.color.reduce;

import java.awt.*;

import static photo.processor.core.color.reduce.RGBValuesValidator.validate;

public class RandomSignalNoiseApproximation extends SignalNoiseApproximation {

    private double noiseRange = 0.1;

    public RandomSignalNoiseApproximation() {
        super();
    }

    public RandomSignalNoiseApproximation(int numberOfColorsPaletteLevels) {
        super(numberOfColorsPaletteLevels);
    }

    @Override
    Color calculateColorWithNoise(Color color, int x, int y) {
        double random = Math.random();
        int r = validate((int) (color.getRed() - noiseRange * color.getRed() + random * (color.getRed() + noiseRange * color.getRed())));
        int g = validate((int) (color.getGreen() - noiseRange * color.getGreen() + random * (color.getGreen() + noiseRange * color.getGreen())));
        int b = validate((int) (color.getBlue() - noiseRange * color.getBlue() + random * (color.getBlue() + noiseRange * color.getBlue())));
        return new Color(r, g, b);
    }
}
