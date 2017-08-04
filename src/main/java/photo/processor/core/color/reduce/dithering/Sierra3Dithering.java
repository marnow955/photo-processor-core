package photo.processor.core.color.reduce.dithering;

import photo.processor.core.color.reduce.RGBErrors;

public class Sierra3Dithering extends Dithering {

    public Sierra3Dithering() {
        super();
    }

    public Sierra3Dithering(int numberOfColorsPaletteLevels) {
        super(numberOfColorsPaletteLevels);
    }

    @Override
    void propagation(int x, int y, RGBErrors currentError) {
        if (x<width-1)
            errorTab[x+1][y] = new RGBErrors(
                    errorTab[x+1][y].getRedError()+(int)((5./32)*currentError.getRedError()),
                    errorTab[x+1][y].getGreenError()+(int)((5./32)*currentError.getGreenError()),
                    errorTab[x+1][y].getBlueError()+(int)((5./32)*currentError.getBlueError())
            );

        if (x<width-2)
            errorTab[x+2][y] = new RGBErrors(
                    errorTab[x+2][y].getRedError()+(int)((3./32)*currentError.getRedError()),
                    errorTab[x+2][y].getGreenError()+(int)((3./32)*currentError.getGreenError()),
                    errorTab[x+2][y].getBlueError()+(int)((3./32)*currentError.getBlueError())
            );






        if (x>1 && y<height-1)
            errorTab[x-2][y+1] = new RGBErrors(
                    errorTab[x-2][y+1].getRedError()+(int)((2./32)*currentError.getRedError()),
                    errorTab[x-2][y+1].getGreenError()+(int)((2./32)*currentError.getGreenError()),
                    errorTab[x-2][y+1].getBlueError()+(int)((2./32)*currentError.getBlueError())
            );

        if (x>0 && y<height-1)
            errorTab[x-1][y+1] = new RGBErrors(
                    errorTab[x-1][y+1].getRedError()+(int)((4./32)*currentError.getRedError()),
                    errorTab[x-1][y+1].getGreenError()+(int)((4./32)*currentError.getGreenError()),
                    errorTab[x-1][y+1].getBlueError()+(int)((4./32)*currentError.getBlueError())
            );

        if (y<height-1)
            errorTab[x][y+1] = new RGBErrors(
                    errorTab[x][y+1].getRedError()+(int)((5./32)*currentError.getRedError()),
                    errorTab[x][y+1].getGreenError()+(int)((5./32)*currentError.getGreenError()),
                    errorTab[x][y+1].getBlueError()+(int)((5./32)*currentError.getBlueError())
            );

        if (x<width-1 && y<height-1)
            errorTab[x+1][y+1] = new RGBErrors(
                    errorTab[x+1][y+1].getRedError()+(int)((4./32)*currentError.getRedError()),
                    errorTab[x+1][y+1].getGreenError()+(int)((4./32)*currentError.getGreenError()),
                    errorTab[x+1][y+1].getBlueError()+(int)((4./32)*currentError.getBlueError())
            );

        if (x<width-2 && y<height-1)
            errorTab[x+2][y+1] = new RGBErrors(
                    errorTab[x+2][y+1].getRedError()+(int)((2./32)*currentError.getRedError()),
                    errorTab[x+2][y+1].getGreenError()+(int)((2./32)*currentError.getGreenError()),
                    errorTab[x+2][y+1].getBlueError()+(int)((2./32)*currentError.getBlueError())
            );







        if (x>0 && y<height-2)
            errorTab[x-1][y+2] = new RGBErrors(
                    errorTab[x-1][y+2].getRedError()+(int)((2./32)*currentError.getRedError()),
                    errorTab[x-1][y+2].getGreenError()+(int)((2./32)*currentError.getGreenError()),
                    errorTab[x-1][y+2].getBlueError()+(int)((2./32)*currentError.getBlueError())
            );

        if (y<height-2)
            errorTab[x][y+2] = new RGBErrors(
                    errorTab[x][y+2].getRedError()+(int)((3./32)*currentError.getRedError()),
                    errorTab[x][y+2].getGreenError()+(int)((3./32)*currentError.getGreenError()),
                    errorTab[x][y+2].getBlueError()+(int)((3./32)*currentError.getBlueError())
            );

        if (x<width-1 && y<height-2)
            errorTab[x+1][y+2] = new RGBErrors(
                    errorTab[x+1][y+2].getRedError()+(int)((2./32)*currentError.getRedError()),
                    errorTab[x+1][y+2].getGreenError()+(int)((2./32)*currentError.getGreenError()),
                    errorTab[x+1][y+2].getBlueError()+(int)((2./32)*currentError.getBlueError())
            );

    }
}
