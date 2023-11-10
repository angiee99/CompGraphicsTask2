package rasterization;

import java.awt.*;
import java.util.Optional;

/**
 * Represents a two-dimensional raster image with pixels of type Integer
 */
public interface Raster {

    /**
     * Returns the width of this raster image
     * @return
     */
    int getWidth();

    /**
     * Returns the height of this raster image
     * @return
     */
    int getHeight();

    /**
     * Returns the color of a pixel based on its address.
     * @param c column address of the pixel
     * @param r row address of the pixel
     * @return Optional of Integer representation of the color if the give address is valid;
     * empty Optional otherwise
     */
    Optional<Integer> getColor(int c, int r);

    /**
     * Sets the color of the pixel to the provided pixel value at the specified address
     * @param color new color of the pixel
     * @param c column address of the pixel
     * @param r row address of the pixel
     * @return boolean indicating whether the given address is valid
     */
    boolean  setColor(int color, int c, int r);

    /**
     * Sets all the pixels to hold the provided color
     * @param backgroundColor color value to be used when setting pixels
     */
    void clear(int backgroundColor);

    /**
     * Draws this raster onto the given Graphics
     * @param g Graphics to be used to drawing
     */
    void present(Graphics g);
}

