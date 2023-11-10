package rasterops;

import model.Point;
import model.Polygon;
import rasterization.Raster;

import java.util.Optional;

/**
 * Polygoner implementation for drawing polygon, adding new vertices and deleting existent
 */
public class PolygonerBasic implements Polygoner{
    private Polygon polygon;
    private Liner liner;
    private int color;
    private Raster raster;
    public PolygonerBasic(Raster raster, int color){
        polygon = new Polygon();
        liner = new LinerDDAII();
        this.raster = raster;
        this.color = color;
    }

    /**
     * Draws a polygon based on saved vertices
     */
    @Override
    public void drawPolygon(){
        for (int i = 0; i < polygon.getVertexCount() -1; i++) {
            drawEdge(polygon.getVertex(i), polygon.getVertex(i+1), this.color);
        }
        if(polygon.getVertexCount() > 2){
            drawEdge(polygon.getVertex(polygon.getVertexCount() -1),
                    polygon.getVertex(0), this.color);
        }
    }

    /**
     * Draws the edge between two given points
     * @param p1
     * @param p2
     * @param color
     */
    @Override
    public void drawEdge( Point p1, Point p2, int color) {
        liner.drawLine(this.raster, p1.x, p1.y, p2.x, p2.y, color);
    }

    /**
     * Adds a new vertex
     * @param p point
     */
    public void addVertex(Point p){ //dont need raster
        polygon.addVertex(p);
    }

    /**
     * Determines if a passed point is close enough to existent vertex
     * @param p
     * @return Optional of closest existent vertex
     */
    public Optional<Point> isPolVertex(Point p){
        for (int i = 0; i < polygon.getVertexCount(); i++) {
            Point curr = polygon.getVertex(i);
            if(polygon.isCloseEnough(p,curr)){
                return Optional.of(curr);
            }
        }
        return  Optional.empty();
    }

    /**
     * Removes a passed point from vertices
     * @param p
     */
    public void deleteVertex(Point p){
        polygon.removeVertex(p);
    }

    /**
     * Resets the polygon
     */
    public void resetPolygon(){
        polygon.clear();
    }
    public Polygon getPolygon(){
        return this.polygon;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
