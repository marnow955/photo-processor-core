package photo.processor.core;

import photo.processor.core.color.reduce.RGBValuesValidator;

import java.awt.*;

public class BilinearInterpolationImageSize extends NearestNeighbourImageSize {

    public BilinearInterpolationImageSize(int newWidth, int newHeight) {
        super(newWidth, newHeight);
    }

    @Override
    protected void transform() {
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int originX = (int) (x*ratioX);
                int originY = (int) (y*ratioY);
                double a = x*ratioX - originX;
                double b = y*ratioY - originY;
                int[][][] factors = getInterpolationFactors(originX, originY);
                int[] interpolationResult = getInterpolationResult(a, b, factors);

                Color resultColor = new Color(interpolationResult[0], interpolationResult[1], interpolationResult[2]);
                resultImg.setRGB(x, y, resultColor.getRGB());
            }
        }
    }

    protected int[][][] getInterpolationFactors(int originX, int originY) {
        int[][][] F = new int[2][2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Color color = new Color(image.getRGB((originX + i)%width, (originY + j)%height));
                F[i][j][0] = color.getRed();
                F[i][j][1] = color.getGreen();
                F[i][j][2] = color.getBlue();
            }
        }
        return F;
    }

    protected int[] getInterpolationResult(double a, double b, int[][][] f) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            double Fa0 = (1 - a)*f[0][0][i] + a*f[1][0][i];
            double Fa1 = (1 - a)*f[0][1][i] + a*f[1][1][i];
            int Fab = (int) ((1 - b)*Fa0 + b*Fa1);
            result[i] = RGBValuesValidator.validate(Fab);
        }
        return result;
    }
}
