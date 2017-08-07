package photo.processor.core.color.reduce.dithering;

import photo.processor.core.color.reduce.RGBErrors;

public class SierraFilterLiteDithering extends Dithering {

    public SierraFilterLiteDithering() {
        super();
    }

    public SierraFilterLiteDithering(int numberOfColorsPaletteLevels) {
        super(numberOfColorsPaletteLevels);
    }

    @Override
    void propagation(int x, int y, RGBErrors currentError) {
        if (x < width - 1)
            errorTab[x + 1][y] = new RGBErrors(
                    errorTab[x + 1][y].getRedError() + (int) ((2./4)*currentError.getRedError()),
                    errorTab[x + 1][y].getGreenError() + (int) ((2./4)*currentError.getGreenError()),
                    errorTab[x + 1][y].getBlueError() + (int) ((2./4)*currentError.getBlueError())
            );
        if (x > 0 && y < height - 1)
            errorTab[x - 1][y + 1] = new RGBErrors(
                    errorTab[x - 1][y + 1].getRedError() + (int) ((1./4)*currentError.getRedError()),
                    errorTab[x - 1][y + 1].getGreenError() + (int) ((1./4)*currentError.getGreenError()),
                    errorTab[x - 1][y + 1].getBlueError() + (int) ((1./4)*currentError.getBlueError())
            );

        if (y < height - 1)
            errorTab[x][y + 1] = new RGBErrors(
                    errorTab[x][y + 1].getRedError() + (int) ((1./4)*currentError.getRedError()),
                    errorTab[x][y + 1].getGreenError() + (int) ((1./4)*currentError.getGreenError()),
                    errorTab[x][y + 1].getBlueError() + (int) ((1./4)*currentError.getBlueError())
            );
    }
}
