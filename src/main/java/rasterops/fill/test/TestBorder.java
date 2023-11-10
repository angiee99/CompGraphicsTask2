package rasterops.fill.test;

import java.util.function.Predicate;

public class TestBorder implements Predicate<Integer> {
    private Integer borderColor;
    private Integer fillColor;
    public TestBorder(Integer borderColor, Integer fillColor){
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }


    @Override
    public boolean test(Integer currPixel) {

        return !currPixel.equals(borderColor)&& !currPixel.equals(fillColor);
    }
}
