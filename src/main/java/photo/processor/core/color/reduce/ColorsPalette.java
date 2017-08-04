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

    public Color findNearestColor(Color color){
        int nearestColorIndex = 0;
        for (int i=1; i<palette.length; i++) {
            if (diff(palette[i],color)<diff(palette[nearestColorIndex],color))
                nearestColorIndex = i;
        }
        return palette[nearestColorIndex];
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

    private int diff(Color paletteColor, Color color) {
        return  (int)(
                Math.pow(paletteColor.getRed()-color.getRed(),2) +
                        Math.pow(paletteColor.getGreen()-color.getGreen(),2) +
                        Math.pow(paletteColor.getBlue()-color.getBlue(),2)
        );
    }

}
