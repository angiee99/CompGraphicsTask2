package objectop;

import model.Line;
import model.Point;
import model.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Shuterland-Hodgman algorithm for polygon cutter
 */
public class PolygonCutter {
    public Polygon cut(Polygon cutiingPolygon, Polygon polygonToCut){
        //TODO
        List<Line> clippingEdges = cutiingPolygon.getEdges();
        ArrayList<Point> in = polygonToCut.getVertices();
        ArrayList<Point> out = new ArrayList<>();
        for(Line edge: clippingEdges){
            out.clear();
            if(in.size() < 1){
                continue;
            }
            Point v1 = in.get(in.size()-1); //last vertex
            for(Point v2: in){
                if(edge.isInside(v2)){
                    if(!edge.isInside(v1)){
                        Point intercept = edge.intercept(new Line(v1,v2));
                        if(intercept.x == -2 && intercept.y == -2){
                            // sometimes maybe we need to add v2, dependidng if v2 is inside or not
                            out.add(closestCuttingPoint(edge, v2));
                        }
                        else if(intercept.x!= -1 && intercept.y!=-1){
                            out.add(intercept); //var.4
                        }
                    }
                    out.add(v2);
                }
                else{
                    if(edge.isInside(v1)){
                        Point intercept = edge.intercept(new Line(v1,v2));
                        if(intercept.x == -2 && intercept.y == -2){
                            // sometimes maybe we need to add v2, dependidng if v2 is inside or not
                            out.add(closestCuttingPoint(edge, v2));
                        }
                         else if(intercept.x!= -1 && intercept.y!=-1){
                            out.add(intercept); //var.2
                        }
                    }
                }
                v1 = v2;
            }
            //update the polygon-to-cut
            in = new ArrayList<>(out);
        }

        return new Polygon(out);
    }

    private Point closestCuttingPoint(Line edge, Point p){
        double distToP1 = new Point(edge.getX1() - p.x, edge.getY1() - p.y).length();
        double distToP2 = new Point(edge.getX2() - p.x, edge.getY2() - p.y).length();
        if(distToP1 <= distToP2){
            return edge.getP1();
        }
        else{
            return edge.getP2();
        }
    }
}
