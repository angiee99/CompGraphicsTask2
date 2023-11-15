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
    /**
     * Cuts the polygon
     * @param cutiingPolygon
     * @param polygonToCut
     * @return polygon aften cutting
     */
    public Polygon cut(Polygon cutiingPolygon, Polygon polygonToCut){
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
                        Point intercept = edge.lineIntercept(new Line(v1,v2));
                        if(intercept.x!= -1 && intercept.y!=-1){
                            out.add(intercept);
                        }
                    }
                    out.add(v2);
                }
                else{
                    if(edge.isInside(v1)){
                        Point intercept = edge.lineIntercept(new Line(v1,v2));
                         if(intercept.x!= -1 && intercept.y!=-1){
                            out.add(intercept);
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

}
