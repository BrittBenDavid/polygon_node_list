package maman15;


/**
 * The class represents a vertex in the polygon.
 *
 * Time complexity:
 * Complexity of the place:
 * @author Britt Twig
 * @version 20/06/17
 */

public class PointNode
{
    // instance variables - replace the example below with your own
    private Point _point; //The point 
    private PointNode _next; //The pointer on the next point 

    /**
     * Constructor number 1.
     * Get Point and initializing _next to be null.
     */
    public PointNode(Point p)
    {
    	_point = p;
        _next = null;
    }

    /**
     * Constructor number 2.
     * Get Point and PointNode and initializing them.
     */
    public PointNode(Point p, PointNode n)
    {
        _point = p;
        _next = n;
    }
    
    /**
     * Constructor number 3.
     * Copy constructor.
     */
    public PointNode(PointNode n)
    {
        _point = n._point;
    	_next = n._next;
    }
    
    /**
     * The method returns a copy of the point at the vertex.
     *
     * @return copy of the vertex
     */
    public Point getPoint()
    {
        return _point;
    }
    
    /**
     * The method returns a pointer to the next Point.
     *
     * @return a pointer to the next Point
     */
    public PointNode getNext()
    {
        return _next;
    }
    
    /**
     * The method receives a point and updates the point in the vertex.
     *
     * @param pointer
     */
    public void setPoint(Point p)
    {
        _point = p;
    }
    
    /**
     * The method receives a pointer and updates the pointer to the next point.
     *
     * @param pointer
     */
    public void setNext(PointNode next)
    {
        _next = next;
    }
}
