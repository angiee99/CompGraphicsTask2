package rasterops;

import rasterization.Raster;
/**
 * Responsible for an interaction between user and raster
 * in case of rasterizing lines
 */
public interface Liner {
    public void drawLine(Raster rastr, double x1, double y1, double x2, double y2, int color);
}
