package model;

import java.util.ArrayList;

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