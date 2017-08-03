package photo.processor.core.color.reduce;

import java.awt.*;

public class ColorsPalette {

    private int numberOfLevels;
    private Color[] palette;

    public ColorsPalette(int numberOfLevels) throws IllegalArgumentException {
        setNumberOfLevels(numberOfLevels);
    }

    public Color[] getPalette() {
        return palette;
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }

    public void setNumberOfLevels(int numberOfLevels) throws IllegalArgumentException {
        if (!numberOfLevelsValidate(numberOfLevels)) {
            throw new IllegalArgumentException("ColorsPalette can have only 2-256 levels");
        }
        this.numberOfLevels = numberOfLevels;
        generatePalette();
    }

    private boolean numberOfLevelsValidate(int numberOfLevels) {
        return numberOfLevels >= 2 && numberOfLevels <= 256;
    }

    private void generatePalette() {
        int numberOfColors = (int) Math.pow(numberOfLevels, 3);
        int increment = 255 / (numberOfLevels - 1);
        palette = new Color[numberOfColors];
        int index = 0;
        for (int i = 0; i < numberOfLevels; i++) {
            int r = i == numberOfLevels - 1 ? 255 : i * increment;
            for (int j = 0; j < numberOfLevels; j++) {
                int g = j == numberOfLevels - 1 ? 255 : j * increment;
                for (int k = 0; k < numberOfLevels; k++) {
                    int b = k == numberOfLevels - 1 ? 255 : k * increment;
                    palette[index] = new Color(r, g, b);
                    index++;
                }
            }
        }
    }

}
