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
        for(Line edge:clippingEdges){
            out.clear();
            Point v1 = in.get(in.size()-1); //last vertex
            for(Point v2: in){
                if(edge.isInside(v2)){
                    if(!edge.isInside(v1)){
                        Point intercept = edge.intercept(new Line(v1,v2));
                        if(intercept.x!= -1 && intercept.y!=-1){
                            out.add(intercept); //var.4
                        }
                    }
                    out.add(v2);
                }
                else{
                    if(edge.isInside(v1)){
                        Point intercept = edge.intercept(new Line(v1,v2));
                        if(intercept.x!= -1 && intercept.y!=-1){
                            out.add(intercept); //var.2
                        }
                    }
                }
                v1 = v2;
            }
            //update the out but think ab how to do it
        }

        return new Polygon(out);
    }

}
