package rasterops.fill.test;

import java.util.function.Predicate;

/**
 * Predicate for testing if the color is different from border color and fill color
 */
public class TestBorder implements Predicate<Integer> {
    private Integer borderColor;
    private Integer fillColor;
    public TestBorder(Integer borderColor, Integer fillColor){
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    /**
     * Tests if currPixel is different from border color and fill color
     * @param currPixel the input argument
     * @return true if different
     */
    @Override
    public boolean test(Integer currPixel) {

        return !currPixel.equals(borderColor)&& !currPixel.equals(fillColor);
    }
}
