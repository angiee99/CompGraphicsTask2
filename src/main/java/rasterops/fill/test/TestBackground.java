package rasterops.fill.test;

import java.util.function.Predicate;

/**
 * Predicate for testing if the color is the same as background color
 */
public class TestBackground implements Predicate<Integer> {
    private Integer bgcolor;
    public TestBackground(Integer bgcolor){
        this.bgcolor = bgcolor;
    }

    /**
     * Tests if currPixel is the same as background color
     * @param currPixel the input argument
     * @return true if currPixel is the same as background color
     */
    @Override
    public boolean test(Integer currPixel) {
        return currPixel.equals(bgcolor);
    }
}
