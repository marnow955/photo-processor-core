package photo.processor.core.color.reduce;

public class RGBValuesValidator {

    public static int validate(int rgbValue){
        if (rgbValue < 0)
            return 0;
        else if (rgbValue > 255)
            return 255;
        else
            return rgbValue;
    }
}
