package photo.processor.core.size;

import photo.processor.core.color.reduce.RGBValuesValidator;

import java.awt.*;

public class BiquadraticInterpolationImageSize extends BilinearInterpolationImageSize {

    public BiquadraticInterpolationImageSize(int newWidth, int newHeight) {
        super(newWidth, newHeight);
    }

    @Override
    protected int[][][] getInterpolationFactors(int originX, int originY) {
        int[][][] F = new int[3][3][3];
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Color color = new Color(image.getRGB(Math.floorMod(originX + i, width), Math.floorMod(originY + j, height)));
                F[i + 1][j + 1][0] = color.getRed();
                F[i + 1][j + 1][1] = color.getGreen();
                F[i + 1][j + 1][2] = color.getBlue();
            }
        }
        return F;
    }

    @Override
    protected int[] getInterpolationResult(double a, double b, int[][][] f) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            double Fa0 = f[1][0][i] + (f[2][0][i] - f[0][0][i])*a + (f[0][0][i] - 2*f[1][0][i] + f[2][0][i])*a*a;
            double Fa1 = f[1][1][i] + (f[2][1][i] - f[0][1][i])*a + (f[0][1][i] - 2*f[1][1][i] + f[2][1][i])*a*a;
            double Fa2 = f[1][2][i] + (f[2][2][i] - f[0][2][i])*a + (f[0][2][i] - 2*f[1][2][i] + f[2][2][i])*a*a;
            int Fab = (int) (Fa1 + (Fa2 - Fa0)*b + (Fa0 - 2*Fa1 + Fa2)*b*b);
            result[i] = RGBValuesValidator.validate(Fab);
        }
        return result;
    }
}
