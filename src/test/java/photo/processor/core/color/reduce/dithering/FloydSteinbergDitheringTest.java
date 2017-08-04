package photo.processor.core.color.reduce.dithering;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import photo.processor.core.PhotoProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FloydSteinbergDitheringTest {

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

    @Ignore //same as NearestColorTest
    @Test
    public void testImageTransformation() throws IOException {
        PhotoProcessor tester = new FloydSteinbergDithering();
        BufferedImage result = tester.getTransformedImage(originalImage);

        ImageIO.write(result, "png", new File("result.png"));
    }
}