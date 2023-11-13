package rasterops.fill;

import java.awt.*;
import java.util.ArrayList;

public class PatternFill {
    private int[][] pattern;
    private int sizeI, sizeJ;
    public PatternFill(){
        pattern = new int[5][5];
        sizeI = sizeJ = 5;
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                pattern[i][j] = new Color(0, 90, 180).getRGB();
            }
        }
        pattern[2][1] =  new Color(0, 158, 90).getRGB();
        pattern[2][2] =  new Color(0, 158, 90).getRGB();
        pattern[1][2] =  new Color(0, 158, 90).getRGB();
        pattern[1][3] =  new Color(0, 158, 90).getRGB();
        pattern[3][2] =  new Color(0, 158, 90).getRGB();
        pattern[3][1] =  new Color(0, 158, 90).getRGB();

    }

    public int paint(int x, int y){
        int i = x % sizeI; //
        int j = y % sizeJ;
        int color = pattern[i][j];
        return color;
    }
}
