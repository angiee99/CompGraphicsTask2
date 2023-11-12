package model;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Polygon{
    private ArrayList<Point> vertices;
    public Rectangle(){
        vertices = new ArrayList<>();
    }

    /**
     * Constructs a rectangle based on 2 anchor points
     * @param upperLeft point
     * @param lowerRight point
     */
    public void constructRectangle(Point upperLeft, Point lowerRight){
        Point lowerLeft = new Point(upperLeft.x, lowerRight.y);
        Point upperRight = new Point(lowerRight.x, upperLeft.y);
        vertices.add(upperLeft);
        vertices.add(lowerLeft);
        vertices.add(lowerRight);
        vertices.add(upperRight);
    }

    @Override
    public int getVertexCount() {
        return vertices.size(); //always should be 4
    }

    public void addVertex(Point p) {
        if (getVertexCount() < 2) {
            vertices.add(p);
        } else if (getVertexCount() == 2) {
           vertices.add(1, p);
        } else {
            int[] edge = super.closestEdge(p);
            vertices.add(edge[0] + 1, p);
//            addVertexAtIndex(p, edge[0] + 1);
        }
    }
    @Override
    public List<Line> getEdges() {
        List<Line> edges = new ArrayList<>();
        for (int i = 0; i < getVertexCount() - 1; i++) {
            edges.add(new Line(this.vertices.get(i), this.vertices.get(i+1)));
        }
        edges.add(new Line(this.vertices.get(getVertexCount() - 1), this.vertices.get(0)));
        return edges;
    }

    @Override
    public Point getVertex(int index) {
        return vertices.get(index);
    }

    public Point countCenter() {
        int xCenter = Math.min(vertices.get(0).x, vertices.get(3).x) + getWidth()/2;
        int yCenter = Math.min(vertices.get(0).y, vertices.get(1).y) + getHeight()/2;
        return new Point(xCenter, yCenter);
    }
    public int getWidth(){
        return Math.abs(vertices.get(0).x - vertices.get(3).x);
    }
    public int getHeight(){
        return Math.abs(vertices.get(0).y - vertices.get(1).y);
    }
}
