package rasterops;

import model.Line;
import model.Point;
import rasterization.Raster;

/**
 * Liner implementation for drawing 2D lines with DDA II algorithm
 */
public class LinerDDAII implements Liner {
    /**
     * Draws a line based on end points coordinates
     * @param rastr
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public void drawLine(Raster rastr, double x1, double y1, double x2, double y2, int color){
        if(Math.abs(y2 - y1) < Math.abs(x2 - x1)){ // ridici osa x
            if(x1 > x2){//swap
                double temp = x1;
                x1 = x2;
                x2 = temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }

            final double k = (y2 - y1) / (x2 - x1);
            int x = (int)Math.round(x1);
            double y = y1;

            do{
                rastr.setColor(color, x, (int)Math.round(y));
                x += 1;
                y += k;
            } while (x <= x2);
        }
        else{ // ridici osa y
            if(y1 > y2){ //swap
                double temp = x1;
                x1 = x2;
                x2 = temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            double k = (y2 - y1) / (x2 - x1);
            double x = x1;
            int y = (int)Math.round(y1);
            do{
                rastr.setColor(color, (int)Math.round(x), y);
                x += 1/k;
                y += 1;
            } while (y <= y2);
        }
    }

    /**
     * Draws a line based on two given points
     * @param rastr
     * @param p1
     * @param p2
     * @param color
     */
    public void drawLine(Raster rastr, Point p1, Point p2, int color){
        drawLine(rastr, p1.x, p1.y, p2.x, p2.y, color);
    }

    /**
     * Draws a line by accepting a Line object
     * @param rastr
     * @param line
     */
    public void drawLine(Raster rastr, Line line){
        drawLine(rastr, line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getColor());
    }


}