package rasterops.fill;

import rasterization.Raster;

import java.awt.*;
import java.util.function.Predicate;

public interface SeedFill {

    public void fill(Raster img, int c, int r, Color fillColor, Predicate<Integer> isInArea);
}
