package photo.processor.core.color.reduce;

import java.awt.*;

class RGBErrors {
    private int r;
    private int g;
    private int b;

    RGBErrors(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    RGBErrors(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    int getRedError() {
        return r;
    }

    int getGreenError() {
        return g;
    }

    int getBlueError() {
        return b;
    }
}
