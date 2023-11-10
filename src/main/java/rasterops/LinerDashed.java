package rasterops;

import model.Line;
import model.Point;
import rasterization.Raster;

/**
 * Liner implementation for drawing the dashed line
 */
public class LinerDashed implements Liner{
    /**
     * Draws a dashed line with parametrised step
     * @param rastr
     * @param x1 Point 1 (x)
     * @param y1 Point 1 (y)
     * @param x2 Point 2 (x)
     * @param y2 Point 2 (y)
     * @param color
     * @param step
     */
    public void drawLine(Raster rastr, double x1, double y1, double x2, double y2, int color, int step){
        boolean draw = false;

        if(Math.abs(y2 - y1) < Math.abs(x2 - x1)){// y = x
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
                draw = (draw == false) ? true: false;
                for (int i = 0; i < step; i++) {
                    if(draw){
                        rastr.setColor(color, x, (int)Math.round(y));
                    }
                    x += 1;
                    y += k;
                }
            } while (x <= x2);
        }
        else{ // ridici osa x
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
                draw = (draw == false) ? true: false;
                for (int i = 0; i < step; i++) {
                    if(draw){
                        rastr.setColor(color, (int)Math.round(x), y);
                    }
                    x += 1/k;
                    y += 1;
                }
            } while (y <= y2);
        }
    }

    /**
     * Draws a dashed line with a default step
     * @param rastr
     * @param x1 Point 1 (x)
     * @param y1 Point 1 (y)
     * @param x2 Point 2 (x)
     * @param y2 Point 2 (y)
     * @param color
     */
    @Override
    public void drawLine(Raster rastr, double x1, double y1, double x2, double y2, int color) {
        drawLine(rastr, x1, y1, x2, y2, color, 10); // default step = 10
    }
    public void drawLine(Raster rastr, Point p1, Point p2, int color){
        drawLine(rastr, p1.x, p1.y, p2.x, p2.y, color);
    }

    public void drawLine(Raster rastr, Point p1, Point p2, int color, int step){
        drawLine(rastr, p1.x, p1.y, p2.x, p2.y, color, step);
    }
    public void drawLine(Raster rastr, Line line){
        drawLine(rastr, line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getColor());
    }
    public void drawLine(Raster rastr, Line line, int step){
        drawLine(rastr, line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getColor(), step);
    }

}
