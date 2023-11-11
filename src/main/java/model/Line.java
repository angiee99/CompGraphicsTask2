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
        return (isInRange((int)Math.round(y), y1, y2));
    }

    public double yIntercept(double y){
        if(x1 == x2) return x1;
        //TODO check if works correctly for ScanLine
        //kx + q = y
        // x = (y - q)/k

        double k = Math.abs((double)(y2 - y1) / (double)(x2 - x1));
        double q = y1 - k*x1;
        double yIntercept = (y - q)/k;
        if(isInRange((int)yIntercept, x1, x2)){
            return yIntercept; //x coordinate
        }
        return -1;
    }

    public double xIntercept(double x){
        if(y1 == y2) return y1;
        double k = Math.abs((double)(y2 - y1) / (double)(x2 - x1));
        double q = y1 - k*x1;
        return k*x1 + q;
    }
    public Point intercept(Line other){
        //if both verticall
        if(x1 == x2 && other.x1 == other.x2 ) {
            return new Point(-1, -1); // no intercept
        }

        //if both horizontal
        if(y1 == y2 && other.y1 == other.y2){
            return new Point(-1, -1); // no intercept
        }

        // normal case
        int x3 = other.x1; int y3 = other.y1;
        int x4 = other.x2; int y4 = other.y2;
        //if divisor = 0? or inf?
        double divisor = (double) ((x1-x2)*(y3 - y4)) - (double) ((y1-y2)*(x3-x4));

        double x0UpperValue = (double)(x1*y2 - x2*y1) * (double)(x3 - x4) -
                (double) (x3*y4 - x4*y3) * (double)(x1 - x2);

        double y0UpperValue = (double)(x1*y2 - x2*y1) * (double)(y3 - y4) -
                (double) (x3*y4 - x4*y3) * (double)(y1 - y2);

        int x0 =(int) Math.round(x0UpperValue/divisor);
        int y0 = (int) Math.round(y0UpperValue/divisor);

        if(isInRange(x0, x1, x2) && isInRange(y0, y1, y2)){
            return new Point(x0, y0);
        }
        else {
            return new Point(-1, -1); // no interception
        }
    }
    public boolean isInRange(int x, int a, int b){
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return (x >= min && x <= max);
    }

    public boolean isInside(Point p){
        final Point t = new Point(x2  - x1, y2 - y1);
        final Point n = new Point(t.y, -t.x); // if not the wanted side then change to (-t.y, t.x);
        final Point v = new Point(p.x - x1, p.y - y1);

        final double cosAlpha = (double)n.x * v.x + (double)n.y * v.y;
        System.out.println(cosAlpha);
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
