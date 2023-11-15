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

    /**
     *
     * @return true if line is horizontal
     */
    public boolean isHorizontal(){
        return y1 == y2;
    }
    /**
     * orients the line so that the endPoint.y coordinate is less than startPoint.y
     * @return oreinted line
     */
    public Line oriental(){
        if(y2 > y1){
            return this;
        }
        else return new Line(x2, y2, x1, y1, color);
    }

    /**
     *
     * @param y
     * @return true if line has an intercept with y
     */
    public boolean hasYIntercept(double y){
        return (isInRange((int)Math.round(y), y1, y2-1));
    }

    /**
     *
     * @param y
     * @return x coordinate of intercept between line and y
     */
    public double yIntercept(double y){
        if(x1 == x2) return x1;
        Point interc = lineIntercept(new Line(new Point(0, Math.round(y)), new Point(600, Math.round(y))));
        return interc.x;
    }

    /**
     * Counts the intercept between two lines
     * @param other other line
     * @return Point of two line intercept, if does not exist returns Point(-1; -1)
     */
    public Point lineIntercept(Line other){
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

        double divisor = (double) ((x1-x2)*(y3 - y4)) - (double) ((y1-y2)*(x3-x4));

        double x0UpperValue = (double)(x1*y2 - x2*y1) * (double)(x3 - x4) -
                (double) (x3*y4 - x4*y3) * (double)(x1 - x2);

        double y0UpperValue = (double)(x1*y2 - x2*y1) * (double)(y3 - y4) -
                (double) (x3*y4 - x4*y3) * (double)(y1 - y2);

        int x0 =(int) Math.round(x0UpperValue/divisor);
        int y0 = (int) Math.round(y0UpperValue/divisor);

        return new Point(x0, y0);
    }

    /**
     * Determines if a is in range from a to be (both included)
     * @param x
     * @param a
     * @param b
     * @return
     */
    public boolean isInRange(int x, int a, int b){
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return (x >= min && x <= max);
    }

    /**
     * Determines uf a point lies inside or outside the half plane
     * @param p
     * @return true if point lies inside
     */
    public boolean isInside(Point p){
        final Point t = new Point(x2  - x1, y2 - y1);
        final Point n = new Point(t.y, -t.x);
        final Point v = new Point(p.x - x1, p.y - y1);

        final double cosAlpha = (double)n.x * v.x + (double)n.y * v.y;
        return cosAlpha > 0;
    }

    /**
     * Calculates the distance between point and line
     * @param point
     * @return
     */
    public double distanceTo(Point point) {
        //refactor
        Point p1 = getP1();
        Point p2 = getP2();

        double x1 = p1.x;
        double y1 = p1.y;
        double x2 = p2.x;
        double y2 = p2.y;
        double x = point.x;
        double y = point.y;

        double A = x - x1;
        double B = y - y1;
        double C = x2 - x1;
        double D = y2 - y1;

        double dot = A * C + B * D;
        double len_sq = C * C + D * D;
        double param = -1;

        if (len_sq != 0) // zero length line
            param = dot / len_sq;

        double xx, yy;

        if (param < 0) {
            xx = x1;
            yy = y1;
        } else if (param > 1) {
            xx = x2;
            yy = y2;
        } else {
            xx = x1 + param * C;
            yy = y1 + param * D;
        }

        double dx = x - xx;
        double dy = y - yy;
        return Math.sqrt(dx * dx + dy * dy);
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
