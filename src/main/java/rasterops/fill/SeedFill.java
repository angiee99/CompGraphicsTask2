package rasterops.fill;

import rasterization.Raster;

import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;

public interface SeedFill {
    public void fill(Raster img, int c, int r, int fillColor, int bgcolor);
}
