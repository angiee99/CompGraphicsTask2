package rasterops.fill;

import model.Line;
import model.Point;
import model.Polygon;
import rasterization.Raster;
import rasterops.rasterize.LinerDDAII;

import java.util.ArrayList;
import java.util.List;

public class ScanLine {
    public void fill(Raster raster, Polygon polygon, int fillColor){
        List<Line> edges = polygon.getEdges();
        //remove horizontal line
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).isHorizontal()){
                edges.remove(i);
            }
        }
        // orient the lines +works
        for (int i = 0; i < edges.size(); i++) {
            edges.set(i, edges.get(i).oriental());
        }

        // yMin and yMax (of Polygon)  +works
        int yMin = 100000;
        int yMax = -100000;

        for (int i = 0; i < polygon.getVertexCount(); i++) {
            Point curr = polygon.getVertex(i);
            if(curr.y < yMin){
                yMin = curr.y;
            }
            if(curr.y > yMax){
                yMax = curr.y;
            }
        }

        for (int i = yMin; i < yMax; i++) {
            List<Point> intercepts = new ArrayList<>();
            for (Line line: edges) {
                if(line.hasYIntercept(i)){
                    double intercept = line.yIntercept(i);
                    if(intercept!= -1)
                        intercepts.add(new Point(intercept, i));
                }
            }
            //sort
            for (int k = 0; k < intercepts.size(); k++) {
                for (int j = k+1; j < intercepts.size(); j++) {
                    if(intercepts.get(k).x > intercepts.get(j).x){
                        Point temp = intercepts.get(k);
                        intercepts.set(k, intercepts.get(j));
                        intercepts.set(j, temp);
                    }
                }
            }
            // fill between even and odd intercepts -> works weird
            for (int l = 0; l < intercepts.size()-1; l+=2) {
                Point p1 = intercepts.get(l);
                Point p2 = intercepts.get(l+1);
                new LinerDDAII().drawLine(raster, p1, p2, fillColor);
            }
        }

    }
}
