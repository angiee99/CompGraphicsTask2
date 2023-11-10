package rasterops.rasterize;

import model.Point;
/**
 * Responsible for an interaction between user and raster
 * in case of rasterizing polygons
 */
public interface Polygoner {
    void drawPolygon();
    void drawEdge(Point p1, Point p2, int color);
}
