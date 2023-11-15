package rasterops.fill;

import java.awt.*;

/**
 * represents a pattern and enables filling with pattern
 */
public class PatternFill {
    private int[][] pattern;
    private int sizeI, sizeJ;
    private Color blue, green;

    public PatternFill(){
        pattern = new int[5][5];
        blue = new Color(0, 90, 180);
        green = new Color(0, 158, 90);
        sizeI = sizeJ = 5;
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                pattern[i][j] = blue.getRGB();
            }
        }
        pattern[2][1] =  green.getRGB();
        pattern[2][2] =  green.getRGB();
        pattern[1][2] =  green.getRGB();
        pattern[1][3] =  green.getRGB();
        pattern[3][2] =  green.getRGB();
        pattern[3][1] =  green.getRGB();

    }

    /**
     * Determines which color of pattern should be picked up for given pixel
     * @param x x coordinate of pixel
     * @param y y coordinate of pixel
     * @return color of current pixel for the pattern
     */
    public int paint(int x, int y){
        int i = x % sizeI; //
        int j = y % sizeJ;
        int color = pattern[i][j];
        return color;
    }
}
