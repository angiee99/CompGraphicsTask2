package rasterops;

import model.Point;
import rasterization.Raster;
/**
 * Responsible for an interaction between user and raster
 * in case of rasterizing polygons
 */
public interface Polygoner {
    void drawPolygon();
    void drawEdge(Point p1, Point p2, int color);
}
