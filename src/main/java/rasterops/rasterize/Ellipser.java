package rasterops.rasterize;

import model.Ellipse;
import rasterization.Raster;

/**
 * implementation for rasterising an ellipse
 */
public class Ellipser {
    private Raster raster;
    private int color;

    public Ellipser(Raster raster, int color) {
        this.raster = raster;
        this.color = color;
    }


    public void drawEllipse(Ellipse ellipse){
        float dx, dy, d1, d2;
        int xc, yc, rx, ry, x, y;

        xc = ellipse.getCenter().x;
        yc = ellipse.getCenter().y;
        rx = ellipse.getRx();
        ry = ellipse.getRy();

        // initializing x, y
        x = 0;
        y = ry;

        // Initial decision parameter of 1st half quadrant
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

    /**
     * Based on one point colors also symmetric points
     * @param raster
     * @param color
     * @param x givenPoint.x
     * @param xc centralPoint.x
     * @param y givenPoint.y
     * @param yc centralPoint.y
     */
    private void setSymmetric(Raster raster, int color, int x, int xc, int y, int yc){
        raster.setColor(color, x+xc, y + yc);
        raster.setColor(color, x + xc,  yc - y);
        raster.setColor(color, xc - x, y + yc);
        raster.setColor(color, xc - x, yc - y);
    }




}
