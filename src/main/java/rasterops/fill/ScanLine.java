package rasterops.fill;

import model.Line;
import model.Point;
import model.Polygon;
import rasterization.Raster;
import rasterops.rasterize.LinerDDAII;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements ScanLine algorithm for filling polygon
 */
public class ScanLine {
    /**
     * fills a polygon with ScanLine
     * @param raster
     * @param polygon
     * @param fillColor
     */
    public void fill(Raster raster, Polygon polygon, int fillColor){
        List<Line> edges = polygon.getEdges();
        //remove horizontal line
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).isHorizontal()){
                edges.remove(i);
            }
        }
        // orient the lines
        for (int i = 0; i < edges.size(); i++) {
            edges.set(i, edges.get(i).oriental());
        }

        // yMin and yMax (of Polygon)
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

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
            mergeSort(intercepts);

            // fill between even and odd intercepts
            for (int l = 0; l < intercepts.size()-1; l+=2) {
                Point p1 = intercepts.get(l);
                Point p2 = intercepts.get(l+1);
                new LinerDDAII().drawLine(raster, p1, p2, fillColor);
            }
        }

    }
    public void mergeSort (List < Point > points) {
        if (points.size() <= 1) {
            return; // Already sorted
        }

        int mid = points.size() / 2;
        ArrayList<Point> left = new ArrayList<>(points.subList(0, mid));
        ArrayList<Point> right = new ArrayList<>(points.subList(mid, points.size()));

        mergeSort(left);
        mergeSort(right);

        merge(points, left, right);
    }

    private void merge (List < Point > points, ArrayList < Point > left, ArrayList < Point > right){
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).x <= right.get(j).x) {
                points.set(k++, left.get(i++));
            } else {
                points.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            points.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            points.set(k++, right.get(j++));
        }
    }

}

