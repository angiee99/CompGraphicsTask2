package rasterops.fill;

import java.util.function.Predicate;

public class TestBackground implements Predicate<Integer> {
    private Integer bgcolor;
    public TestBackground(Integer bgcolor){
        this.bgcolor = bgcolor;
    }
    @Override
    public boolean test(Integer currPixel) {
        return currPixel.equals(bgcolor);
    }
}
