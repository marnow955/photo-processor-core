package photo.processor.core;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ColorsPaletteTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNrOfLevelsValidation() {
        ColorsPalette tester = new ColorsPalette(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNrOfLevelsValidation2() {
        ColorsPalette tester = new ColorsPalette(257);
    }

    @Test
    public void testNrOfLevelsValidation3() {
        ColorsPalette tester = new ColorsPalette(2);
        assertEquals(2, tester.getNumberOfLevels());
    }

    @Ignore //Take a while
    @Test
    public void testNrOfLevelsValidation4() {
        ColorsPalette tester = new ColorsPalette(256);
        assertEquals(256, tester.getNumberOfLevels());
    }

    @Test
    public void testPaletteGeneration() {
        ColorsPalette tester = new ColorsPalette(2);
        Color[] expected = {
                Color.BLACK, // 0 0 0
                Color.BLUE, // 0 0 255
                Color.GREEN, // 0 255 0
                Color.CYAN, // 0 255 255
                Color.RED, // 255 0 0
                Color.MAGENTA, // 255 0 255
                Color.YELLOW, // 255 255 0
                Color.WHITE // 255 255 255
        };
        assertArrayEquals(expected, tester.getPalette());
        tester.setNumberOfLevels(3);
        Color[] expected2 = {
                new Color(0, 0, 0),
                new Color(0, 0, 127),
                new Color(0, 0, 255),
                new Color(0, 127, 0),
                new Color(0, 127, 127),
                new Color(0, 127, 255),
                new Color(0, 255, 0),
                new Color(0, 255, 127),
                new Color(0, 255, 255),
                new Color(127, 0, 0),
                new Color(127, 0, 127),
                new Color(127, 0, 255),
                new Color(127, 127, 0),
                new Color(127, 127, 127),
                new Color(127, 127, 255),
                new Color(127, 255, 0),
                new Color(127, 255, 127),
                new Color(127, 255, 255),
                new Color(255, 0, 0),
                new Color(255, 0, 127),
                new Color(255, 0, 255),
                new Color(255, 127, 0),
                new Color(255, 127, 127),
                new Color(255, 127, 255),
                new Color(255, 255, 0),
                new Color(255, 255, 127),
                new Color(255, 255, 255)
        };
        assertArrayEquals(expected2, tester.getPalette());
    }

    ;
}