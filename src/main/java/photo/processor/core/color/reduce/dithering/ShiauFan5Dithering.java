package photo.processor.core.color.reduce.dithering;

import photo.processor.core.color.reduce.RGBErrors;

public class ShiauFan5Dithering extends Dithering {

    public ShiauFan5Dithering() {
        super();
    }

    public ShiauFan5Dithering(int numberOfColorsPaletteLevels) {
        super(numberOfColorsPaletteLevels);
    }

    @Override
    void propagation(int x, int y, RGBErrors currentError) {
        if (x < width - 1)
            errorTab[x + 1][y] = new RGBErrors(
                    errorTab[x + 1][y].getRedError() + (int) ((8./16)*currentError.getRedError()),
                    errorTab[x + 1][y].getGreenError() + (int) ((8./16)*currentError.getGreenError()),
                    errorTab[x + 1][y].getBlueError() + (int) ((8./16)*currentError.getBlueError())
            );


        if (x > 2 && y < height - 1)
            errorTab[x - 3][y + 1] = new RGBErrors(
                    errorTab[x - 3][y + 1].getRedError() + (int) ((1./16)*currentError.getRedError()),
                    errorTab[x - 3][y + 1].getGreenError() + (int) ((1./16)*currentError.getGreenError()),
                    errorTab[x - 3][y + 1].getBlueError() + (int) ((1./16)*currentError.getBlueError())
            );

        if (x > 1 && y < height - 1)
            errorTab[x - 2][y + 1] = new RGBErrors(
                    errorTab[x - 2][y + 1].getRedError() + (int) ((1./16)*currentError.getRedError()),
                    errorTab[x - 2][y + 1].getGreenError() + (int) ((1./16)*currentError.getGreenError()),
                    errorTab[x - 2][y + 1].getBlueError() + (int) ((1./16)*currentError.getBlueError())
            );

        if (x > 0 && y < height - 1)
            errorTab[x - 1][y + 1] = new RGBErrors(
                    errorTab[x - 1][y + 1].getRedError() + (int) ((2./16)*currentError.getRedError()),
                    errorTab[x - 1][y + 1].getGreenError() + (int) ((2./16)*currentError.getGreenError()),
                    errorTab[x - 1][y + 1].getBlueError() + (int) ((2./16)*currentError.getBlueError())
            );

        if (y < height - 1)
            errorTab[x][y + 1] = new RGBErrors(
                    errorTab[x][y + 1].getRedError() + (int) ((4./16)*currentError.getRedError()),
                    errorTab[x][y + 1].getGreenError() + (int) ((4./16)*currentError.getGreenError()),
                    errorTab[x][y + 1].getBlueError() + (int) ((4./16)*currentError.getBlueError())
            );
    }
}
