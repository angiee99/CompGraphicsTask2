package model;

public class Ellipse extends Polygon{
    private Point center;
    private int Rx, Ry;
    public Ellipse(){
        center = new Point(0, 0);
        Rx = 0;
        Ry = 0;
    }
    public Ellipse(Point center, int rx, int ry) {
        this.center = center;
        Rx = rx;
        Ry = ry;
    }

    public Ellipse(Rectangle borderRect){
        this.center = borderRect.countCenter();
        this.Rx = borderRect.getWidth()/2;
        this.Ry = borderRect.getHeight()/2;
    }

    public Point getCenter() {
        return center;
    }
    public int getRx() {
        return Rx;
    }
    public int getRy() {
        return Ry;
    }

}
