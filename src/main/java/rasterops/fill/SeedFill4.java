package rasterops.fill;

import rasterization.Raster;

import javax.swing.*;
import java.awt.*;

//import java.util.function.Predicate;

public class SeedFill4 implements SeedFill {
    @Override
    public void fill(Raster img, int c, int r, int fillColor, int bgcolor) {

        if( img.getColor(c, r).isEmpty())
            return;
        int pixelColor = img.getColor(c, r).get();

        // comparing colors
        if(pixelColor != bgcolor || pixelColor == fillColor){
            System.out.println("found sth else than bgcolor");
            return;
        }

        // draw pixel on x, y
        boolean changed = img.setColor( new Color(255, 255, 0).getRGB(), c, r);
        // recursion
        fill(img, c + 1, r, fillColor, bgcolor);
        fill(img, c - 1, r, fillColor, bgcolor);
        fill(img, c, r+1, fillColor, bgcolor);
        fill(img, c, r-1, fillColor, bgcolor);

    }
}
