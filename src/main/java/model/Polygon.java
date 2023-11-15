package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a polygon in 2D raster by its vertices
 */
public class Polygon {
    protected ArrayList<Point> vertices;
    public Polygon(){
        vertices = new ArrayList<Point>();
    }
    public Polygon(ArrayList<Point> vertices){
        this.vertices = new ArrayList<>(vertices);
    }

    public ArrayList<Point> getVertices(){
        return vertices;
    }


    /**
     * Adds a new vertex to the corresponding index in the list,
     * so that the order of adjacent vertices is saved,
     * and ensures that the new point is added to the closest edge
     * @param p
     */
    public void addVertex(Point p) {
        if (getVertexCount() < 2) {
            vertices.add(p);
        } else if (getVertexCount() == 2) {
            addVertexAtIndex(p, 1);
        } else {
            int[] edge = closestEdge(p);
            addVertexAtIndex(p, edge[0] + 1);
        }
    }

    /**
     * Searches
     * @param p point
     * @return indexes of points that form the closest edge to point p
     */
    int[] closestEdge(Point p){
        int[] closestEdge = new int[]{0, 1};
        double minDistance = new Line(getVertex(0), getVertex(1) ).distanceTo(p);

        for (int i = 1; i < getVertexCount(); i++) {
            int[] currentEdge = new int[]{i, (i + 1) % getVertexCount()};

            Point p1 = getVertex(i);
            Point p2 = getVertex(currentEdge[1]); // Wrap around for the last edge

            double distance = new Line(p1, p2 ).distanceTo(p);

            if (distance < minDistance) {
                minDistance = distance;
                closestEdge = currentEdge;
            }
        }
        return closestEdge;
    }

    /**
     * Adds new vertex at the given index
     * @param newVert
     * @param index
     */
    private void addVertexAtIndex(Point newVert, int index){
        vertices.add(index, newVert);
    }

    /**
     * Removes vertex from the list
     * @param p
     */
    public void removeVertex(Point p){
        vertices.remove(p);
    }

    /**
     * Determines if two points are close enough to consider them referring to the same point in space
     * @param p1
     * @param p2
     * @return true if close enough
     */
    public boolean isCloseEnough(Point p1, Point p2){
        double minDistance = 10;
        double distance = Math.sqrt(Math.abs((p2.x - p1.x)^2) + Math.abs((p2.y - p1.y)^2));
        return distance <= minDistance;
    }

    /**
     *
     * @param index
     * @return vertex at passed index
     */
    public Point getVertex(int index){
            return vertices.get(index);
    }

    /**
     *
     * @return number of vertices in polygon
     */
    public int getVertexCount(){
        return vertices.size();
    }

    /**
     * Determines if a passed point is close enough to existent vertex
     * @param p
     * @return Optional of closest existent vertex
     */
    public Optional<Point> isPolVertex(Point p){
        for (int i = 0; i < getVertexCount(); i++) {
            Point curr = getVertex(i);
            if(isCloseEnough(p,curr)){
                return Optional.of(curr);
            }
        }
        return  Optional.empty();
    }
    /**
     * deletes all saved vertices of a polygon
     */
    public void clear(){
        vertices.clear();
    }

    /**
     * Constructs and returns a list of polygon edges
     * @return list of polygon edges
     */
    public List<Line> getEdges(){
        List<Line> edges = new ArrayList<>();
        for (int i = 0; i < getVertexCount() - 1; i++) {
            edges.add(new Line(this.vertices.get(i), this.vertices.get(i+1)));
        }
        if(getVertexCount() > 0){
            edges.add(new Line(this.vertices.get(getVertexCount() - 1), this.vertices.get(0)));
        }

        return edges;
    }

}
