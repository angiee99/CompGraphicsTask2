package rasterops.fill;

import rasterization.Raster;

import java.awt.*;
import java.util.function.Predicate;


public class SeedFill4 implements SeedFill {

    @Override
    public void fill(Raster img, int c, int r, Color fillColor, Predicate<Integer> isInArea) {

        if( img.getColor(c, r).isEmpty())
            return;
        int pixelColor = img.getColor(c, r).get();

        // comparing colors
        if(!isInArea.test(pixelColor)){
            return;
        }

        // draw pixel on x, y
        img.setColor( fillColor.getRGB(), c, r);

        // recursion
        fill(img, c + 1, r, fillColor, isInArea);
        fill(img, c - 1, r, fillColor, isInArea);
        fill(img, c, r+1, fillColor, isInArea);
        fill(img, c, r-1, fillColor, isInArea);
    }
    public void fillPattern(Raster img, int c, int r, Color fillColor, Predicate<Integer> isInArea) {

        if( img.getColor(c, r).isEmpty())
            return;
        int pixelColor = img.getColor(c, r).get();

        // comparing colors
        if(!isInArea.test(pixelColor)){
            return;
        }
        PatternFill pattern = new PatternFill();
        // draw pixel on x, y
        img.setColor( pattern.paint(c, r), c, r);

        // recursion
        fillPattern(img, c + 1, r, fillColor, isInArea);
        fillPattern(img, c - 1, r, fillColor, isInArea);
        fillPattern(img, c, r+1, fillColor, isInArea);
        fillPattern(img, c, r-1, fillColor, isInArea);
    }
}
