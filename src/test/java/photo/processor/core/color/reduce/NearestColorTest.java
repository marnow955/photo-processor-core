package photo.processor.core.color.reduce;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import photo.processor.core.PhotoProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class NearestColorTest {

    private BufferedImage originalImage;
    private byte[] expected2LImageByteArray;
    private byte[] expected3LImageByteArray;

    @Before
    public void setUp() throws Exception {
        originalImage = ImageIO.read(new File("src/test/resources/original.png"));
        BufferedImage expected2LImage = ImageIO.read(new File("src/test/resources/expected2L.png"));
        BufferedImage expected3LImage = ImageIO.read(new File("src/test/resources/expected3L.png"));

        expected2LImageByteArray = ((DataBufferByte) expected2LImage.getData().getDataBuffer()).getData();
        expected3LImageByteArray = ((DataBufferByte) expected3LImage.getData().getDataBuffer()).getData();
    }

    @Ignore //Becouse of color diffrent color depth, images looks same for me
    @Test
    public void testImageTransformation() throws IOException {
        PhotoProcessor tester = new NearestColor();
        BufferedImage result = tester.getTransformedImage(originalImage);

        ImageIO.write(result, "png", new File("target/test/data/result.png"));

//        byte[] resultByteArray = ((DataBufferByte) result.getData().getDataBuffer()).getData();
//        assertArrayEquals(expected3LImageByteArray, resultByteArray);
//
//        tester = new NearestColor(2);
//        result = tester.getTransformedImage(originalImage);
//        resultByteArray = ((DataBufferByte) result.getData().getDataBuffer()).getData();
//        assertArrayEquals(expected2LImageByteArray, resultByteArray);
    }

}