package rasterization;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Represents a 2D raster buffered image
 */
public class RasterBI implements Raster{

    private final BufferedImage img;

    public RasterBI(int width, int height) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public Optional<Integer> getColor(int c, int r) {
        if (c < img.getWidth() && r < img.getHeight() && c >= 0 && r >= 0) {
            return Optional.of(img.getRGB(c, r));
        }
        return Optional.empty();
    }

    @Override
    public boolean setColor(int color, int c, int r) {
        if (c < img.getWidth() && r < img.getHeight() && c >= 0 && r >= 0) {
            img.setRGB(c, r, color);
            return true;
        }
        return false;
    }

    @Override
    public void clear(int backgroundColor) {
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(backgroundColor));
        gr.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    @Override
    public void present(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
