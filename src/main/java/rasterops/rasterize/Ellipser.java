package rasterops.rasterize;

import model.Ellipse;
import model.Point;
import rasterization.Raster;

public class Ellipser implements Polygoner{
    public void drawEllipse(Ellipse ellipse, Raster raster, int color){
        float dx, dy, d1, d2;
        int xc, yc, rx, ry, x, y;

        xc= ellipse.getCenter().x;
        yc = ellipse.getCenter().y;
        rx = ellipse.getRx();
        ry = ellipse.getRy();

        x = 0;
        y = ry;

        // Initial decision parameter of  1st half quadrant
        d1 = (ry * ry) - (rx * rx * ry) + (0.25f * rx * rx);
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;

        // 1st half quadrant
        while (dx < dy)
        {
            //symmetry
            setSymmetric(raster, color, x, xc, y, yc);

            // Checking and updating value of
            // decision parameter based on algorithm
            x++;
            dx = dx + (2 * ry * ry);
            if (d1 < 0)
            {
                d1 = d1 + dx + (ry * ry);
            }
            else
            {
                y--;
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }

        // Decision parameter of 2nd half quadrant
        d2 = ((ry * ry) * ((x + 0.5f) * (x + 0.5f)))
                + ((rx * rx) * ((y - 1) * (y - 1)))
                - (rx * rx * ry * ry);

        // Plotting points of 2nd half quadrant
        while (y >= 0) {
            // symmetry
            setSymmetric(raster, color, x, xc, y, yc);

            // Checking and updating parameter
            // value based on algorithm
            y--;
            dy = dy - (2 * rx * rx);
            if (d2 > 0) {
                d2 = d2 + (rx * rx) - dy;
            }
            else {
                x++;
                dx = dx + (2 * ry * ry);
                d2 = d2 + dx - dy + (rx * rx);
            }
        }
    }

    //private void SetSymmetric(Raster raster, int color, Point xy, Point center)
    private void setSymmetric(Raster raster, int color, int x, int xc, int y, int yc){
        raster.setColor(color, x+xc, y + yc);
        raster.setColor(color, x + xc,  yc - y);
        raster.setColor(color, xc - x, y + yc);
        raster.setColor(color, xc - x, yc - y);
    }

    @Override
    public void drawPolygon() {
        //TODO refactor interface
    }

    @Override
    public void drawEdge(Point p1, Point p2, int color) {

    }
}
