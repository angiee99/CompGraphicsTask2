package model;

/**
 * represents an Ellipse by its center point and radius on x asis and y axis
 */
public class Ellipse{
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

    /**
     * constructor to create an ellipse limited by rectangle
     * @param borderRect limiting rectangle
     */
    public Ellipse(Rectangle borderRect){
        this.center = borderRect.countCenter();
        this.Rx = borderRect.getWidth()/2;
        this.Ry = borderRect.getHeight()/2;
    }

    /**
     * returns the center point
     * @return center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * returns the radius on X axis
     * @return radius on X axis
     */
    public int getRx() {
        return Rx;
    }
    /**
     * returns the radius on Y axis
     * @return radius on Y axis
     */
    public int getRy() {
        return Ry;
    }

}
