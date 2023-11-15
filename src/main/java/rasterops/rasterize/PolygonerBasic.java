package rasterops.rasterize;

import model.Point;
import model.Polygon;
import model.Rectangle;
import rasterization.Raster;

/**
 * Polygoner implementation for drawing polygon, adding new vertices and deleting existent
 */
public class PolygonerBasic implements Polygoner{

    private Liner liner;
    private int color;
    private Raster raster;
    public PolygonerBasic(Raster raster, int color){
        liner = new LinerDDAII();
        this.raster = raster;
        this.color = color;
    }

    /**
     * Draws a polygon based on saved vertices
     */
    @Override
    public void drawPolygon(Polygon polygon){
        for (int i = 0; i < polygon.getVertexCount() -1; i++) {
            drawEdge(polygon.getVertex(i), polygon.getVertex(i+1), color);
        }
        if(polygon.getVertexCount() > 2){
            drawEdge(polygon.getVertex(polygon.getVertexCount() -1),
                    polygon.getVertex(0), color);
        }
    }
    /**
     * draws a rectangle, but the logic is same as for any other polygon
     * @param other rectangle
     */
    public void drawPolygon(Rectangle other){
        for (int i = 0; i < other.getVertexCount() -1; i++) {
            drawEdge(other.getVertex(i), other.getVertex(i+1), this.color);
        }
        if(other.getVertexCount() > 2){
            drawEdge(other.getVertex(other.getVertexCount() -1),
                    other.getVertex(0), this.color);
        }
    }

    /**
     * Draws the edge between two given points
     * @param p1
     * @param p2
     * @param color
     */
    public void drawEdge( Point p1, Point p2, int color) {
        liner.drawLine(this.raster, p1.x, p1.y, p2.x, p2.y, color);
    }



    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
