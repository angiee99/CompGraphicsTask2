package rasterops.rasterize;

import model.Point;
import model.Polygon;

/**
 * Responsible for an interaction between user and raster
 * in case of rasterizing polygons
 */
public interface Polygoner {
    void drawPolygon(Polygon polygon);
}
