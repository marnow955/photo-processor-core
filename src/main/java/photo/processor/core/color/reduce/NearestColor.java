package photo.processor.core.color.reduce;

import photo.processor.core.PhotoProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NearestColor implements PhotoProcessor {

    private ColorsPalette palette;

    private BufferedImage image;
    private BufferedImage resultImg;

    private int width;
    private int height;

    public NearestColor(){
        palette = new ColorsPalette(3);
    }

    public NearestColor(int numberOfColorsPaletteLevels){
        palette = new ColorsPalette(numberOfColorsPaletteLevels);
    }

    @Override
    public BufferedImage getTransformedImage(BufferedImage image) {
        init(image);
        transform();
        return resultImg;
    }

    private void init(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        resultImg = new BufferedImage(width,height,image.getType());
    }

    private void transform() {
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color resultColor = palette.findNearestColor(new Color(image.getRGB(x,y)));
                resultImg.setRGB(x,y,resultColor.getRGB());
            }
        }
    }
}
