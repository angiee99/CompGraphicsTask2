package rasterops;

import model.Line;
import model.Point;
import rasterization.Raster;

public class LinerStrict extends LinerDDAII {
    /**
     * Draws a strict line returned by getStrictLine method and uses LinerDDAII method drawLine
     * @param rastr
     * @param p1
     * @param p2
     * @param color
     */
    public void drawStrictLine(Raster rastr, Point p1, Point p2, int color){
        Line lineTodraw = getStrictLine(p1, p2, color);
        drawLine(rastr, lineTodraw);
    }


    /**
     * Returns a strict line based on starting and ending point of one of three types:
     * horizontal, vertical, diagonal
     * @param p1
     * @param p2
     * @param color
     * @return horizontal/vertical/diagonal Line object
     */
    public Line getStrictLine( Point p1, Point p2, int color){
        double dy = p2.y - p1.y;
        double dx = (p2.x - p1.x);
        double d = dy/dx;
        final double k = Math.abs(d);

        if(k == 1.00000 || k == 0 || p1.x == p2.x){
            // is already strict
            return new Line(p1, p2, color);
        }

        Point endPoint;
        if(k <= 0.41){
            // horizontal line
            endPoint = new Point(p2.x, p1.y);
        }
        else if((k > 0.41 && k < 1) || (k > 1 && k < 2.41)){
            //diagonal
            if(p1.y > p2.y){ //if 1st and 2nd quadrants
                endPoint = new Point(p2.x,  (p1.y - Math.abs(dx)));
            }
            else{ // 3rd and 4th quadrants
                endPoint = new Point(p2.x,  (p1.y + Math.abs(dx)));
            }
        }
        else if(k >= 2.41){
            //vertical
            endPoint = new Point(p1.x, p2.y);
        }
        else endPoint = new Point(0, 0);

        Line strictLine = new Line(p1, endPoint, color);

        return strictLine;
    }

}
