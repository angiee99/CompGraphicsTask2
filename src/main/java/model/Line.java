package model;

/**
 * Represents a line in 2D raster
 */
public class Line {
    private final int x1, y1, x2, y2;
    private final int color;

    public Line(int x1, int y1, int x2, int y2, int color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    public Line(Point p1, Point p2, int color){

        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
        this.color = color;
    }

    public Line(Point p1, Point p2){

        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
        this.color = 0;
    }

    public boolean isHorizontal(){
        return y1 == y2;
    }

    public Line oriental(){
        if(y2 > y1){
            return this;
        }
        else return new Line(x2, y2, x1, y1, color);
    }

    public boolean hasYIntercept(double y){
        return y1 <= y && y <= y2;
    }

    public double yIntercept(double y){
        if(x1 == x2) return x1;

        //kx + q = y
        // x = (y - q)/k

        double k = Math.abs((double)(y2 - y1) / (double)(x2 - x1));
        double q = y1 - k*x1;
        double yIntercept = (y - q)/k;
        return yIntercept; //x coordinate
    }

    public Point intercept(Line other){
        //TODO
        return null;
    }
    public boolean isInside(Point p){
        final Point t = new Point(x2  - x1, y2 - y1);
        final Point n = new Point(-t.y, t.x); // if not the wanted side then change to (t.y, -t.x); - deside just by testing
        final Point v = new Point(p.x - x1, p.y - y1);

        final Point nNorm = new Point(n.x /n.length(), n.y /n.length()); // what if length == 0
        final Point vNorm = new Point(v.x /v.length(), v.y /v.length()); // what if length == 0

        final double cosAlpha = nNorm.x * vNorm.x + nNorm.y * vNorm.y;

        return cosAlpha > 0;
    }

    public Point getP1(){
        return new Point(x1, y1);
    }

    public Point getP2(){
        return new Point(x2, y2);
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getColor() {
        return color;
    }
}
