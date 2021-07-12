package maman15;

/**
 * The class polygon represents a polygon in the cooardinate system,
 * by using a list of vertices that keeps the polygon vertices in order of appearance.
 * 
 * Time complexity: O(n), In each method the worst time to run is O(n), so this is the overall complexity.
 * Complexity of the place: O(n), Determined according to the size of the memory that should be allocated for executing the program.
 *
 * @author Britt Twig
 * @version 20-06-2017
 */

public class Polygon
{
	//The head of the list of vertices
	private PointNode _head;

	/**
	 * Constructs a new polygon using class Point.
	 */
	public Polygon()
	{
		_head = null;
	}

	/**
	 * The method check if the polygon is empty (has no vertices in the list that represent it).
	 * 
	 * @return True if the polygon is empty
	 */
	private boolean empty()
	{
		return _head == null;
	}

	/**
	 * The method check how many vertices in the polygon.
	 * 
	 * @return numOfVertices The number of the vertices in the polygon
	 */
	private int howManyVertices()
	{
		PointNode temp = _head;
		int numOfVertices = 0;
		while (temp != null)
		{
			numOfVertices ++;
			temp = temp.getNext();
		}      	
		return numOfVertices;
	}

	/**
	 * The method added a new vertex to the polygon, in the wanted place to had.
	 * We assume that the added new vertex does not spoil the fact that the polygon is a convex polygon.
	 * 
	 * @param p The point to add
	 * @param pos The place in list to insert the point
	 * @Return True if the added is done correctly
	 */
	public boolean addVertex(Point p, int pos)
	{
		PointNode pointToAdd = new PointNode(p);
		pointToAdd.setNext(null);

		PointNode temp = _head;
		int i = 1;
		if (howManyVertices() == 0)
		{
			_head = pointToAdd;
			return true;
		}
		else if(howManyVertices() == pos-1)
		{
			while(temp.getNext() != null)
			{
				temp = temp.getNext();
			}
			temp.setNext(pointToAdd);
			return true;
		}
		while(temp != null)
		{
			if(i == pos)
			{
				pointToAdd.setNext(temp.getNext());
				temp.setNext(pointToAdd);
				return true;
			}
			temp = temp.getNext();
			i++;
		}
		return false;
	}

	/**
	 * The method returns a copy of the first vertex that is highest than the other vertices in the cooardinate system.
	 * 
	 * @param highest Represent the highest vertex in the polygon
	 */
	public Point highestVertex()
	{
		if (empty())
			return null;
		else
		{
			PointNode highest = _head;
			PointNode temp = _head;
			while (temp != null)
			{
				if (temp.getPoint().isAbove(highest.getPoint()))
					highest = temp;
				temp = temp.getNext();
			}
			return highest.getPoint();
		}
	}

	/**
	 * The method returns a string of Points that represent the polygon.
	 * 
	 * @Return A string of point that represent the polygon
	 */
	public String toString()
	{
		String getPolygon = "";
		if (empty())
			return "The polygon has 0 vertices.";
		PointNode temp = _head.getNext();
		getPolygon = "The polygon has " + howManyVertices() + " vertices:\n(";
		getPolygon += _head.getPoint().toString();
		while (temp != null)
		{
			getPolygon += "," + temp.getPoint().toString();
			temp = temp.getNext();
		}
		getPolygon += ")";
		return getPolygon;
	}

	/**
	 * The method returns the perimeter of the polygon.
	 * 
	 * @param Perimeter The perimeter of the polygon.
	 */
	public double calcPerimeter()
	{
		double perimeter = 0;
		PointNode temp = _head;
		if (temp == null || temp.getNext() == null) //for 1 or 0 vertices in the polygon
			return 0;
		if (howManyVertices() == 2)//for more than 2 vertices in the polygon
			return temp.getPoint().distance(temp.getNext().getPoint());

		while (temp.getNext() != null)
		{
			perimeter += temp.getPoint().distance(temp.getNext().getPoint()); 
			temp = temp.getNext();
		}
		perimeter += temp.getPoint().distance(_head.getPoint());
		return perimeter;
	}

	/**
	 * The method calculate a triangular area.
	 * 
	 * @param TriangulArea The triangular that we want to calc
	 * @return Triangular area
	 */
	private double calcTriangulArea(PointNode A, PointNode B, PointNode C)
	{     
		Polygon triangulPolygon = new Polygon();
		triangulPolygon.addVertex(A.getPoint(), 0);
		triangulPolygon.addVertex(B.getPoint(), 1);
		triangulPolygon.addVertex(C.getPoint(), 2);

		double triangulArea = 0;
		double s = (triangulPolygon.calcPerimeter()/2);
		double a = A.getPoint().distance(B.getPoint());
		double b = B.getPoint().distance(C.getPoint());
		double c = C.getPoint().distance(A.getPoint());
		triangulArea = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		return triangulArea;
	}

	/**
	 * The method returns a number that represent the polygon area.
	 * 
	 * @param Area The polygon area
	 */
	public double calcArea()
	{
		if (howManyVertices() < 3)
			return 0;
		double area = 0;
		PointNode temp = _head.getNext();
		while (temp.getNext() != null)
		{
			area += calcTriangulArea(_head, temp, temp.getNext());
			temp = temp.getNext();
		}
		return area;
	}

	/**
	 * The method check if this polygon is bigger than the reference polygon (other).
	 * 
	 * @param other The reference polygon
	 * @Return True if this polygon is bigger than other polygon
	 */
	public boolean isBigger(Polygon other)  
	{   
		return (this.calcArea() > other.calcArea());
	}

	/**
	 * The method gets a point returns the location of that point in the array.
	 * 
	 * @param p Point object
	 * @param location The location of the point in  the array
	 */
	public int findVertex(Point p)
	{
		PointNode temp = _head;
		int findVertex = 1;
		while (temp != null)
		{
			if (temp.getPoint().equals(p))
				return findVertex;
			findVertex ++;
			temp = temp.getNext();
		}
		return -1;
	}

	/**
	 * The method get a point and returns a copy of the point that represent the next point in the polygon
	 * (after the point that entered).
	 * 
	 * @param p Represent the point we are looking at
	 * @return The next point in the polygon
	 */
	public Point getNextVertex(Point p)
	{
		int pos = findVertex(p);
		PointNode temp = _head;
		if (pos == -1)
			return null;
		if (howManyVertices() == 1)
			return new Point(temp.getPoint());
		if(howManyVertices() == pos)
			return new Point(_head.getPoint());
		for (int i = 1; i < pos; i ++)
			temp = temp.getNext();
		return new Point(temp.getNext().getPoint());
	}

	/**
	 * The method returns the rightmost vertex in the polygon.
	 * 
	 * @Return Rightmost vertex
	 */
	private Point rightmostVertex()
	{
		if (empty())
			return null;
		else
		{
			PointNode rightmost = _head;
			PointNode temp = _head;
			while (temp != null)
			{
				if (temp.getPoint().isRight(rightmost.getPoint()))
					rightmost = temp;
				temp = temp.getNext();
			}
			return rightmost.getPoint();
		}
	}

	/**
	 * The method returns the leftmost vertex in the polygon. 
	 * 
	 * @Return Leftmost vertex
	 */
	private Point leftmostVertex()
	{
		if (empty())
			return null;
		else
		{
			PointNode leftmost = _head;
			PointNode temp = _head;
			while (temp != null)
			{
				if (temp.getPoint().isLeft(leftmost.getPoint()))
					leftmost = temp;
				temp = temp.getNext();
			}
			return leftmost.getPoint();
		}
	}

	/**
	 * The method returns the lowest vertex in the polygon.
	 *  
	 * @Return lowest vertex
	 */
	private Point lowestVertex()
	{
		if (empty())
			return null;
		else
		{
			PointNode lowest = _head;
			PointNode temp = _head;
			while (temp != null)
			{
				if (temp.getPoint().isUnder(lowest.getPoint()))
					lowest = temp;
				temp = temp.getNext();
			}
			return lowest.getPoint();
		}
	}

	/**
	 * The method returns the frame that block the polygon.
	 * 
	 * @Return The frame
	 */
	public Polygon getBoundingBox()
	{
		if (howManyVertices() < 3)
			return null;

		Polygon boundingBox = new Polygon();

		boundingBox.addVertex(new Point(leftmostVertex().getX(), lowestVertex().getY()), 0);
		boundingBox.addVertex(new Point(leftmostVertex().getX(), highestVertex().getY()), 1);
		boundingBox.addVertex(new Point(rightmostVertex().getX(), highestVertex().getY()), 2);
		boundingBox.addVertex(new Point(rightmostVertex().getX(), lowestVertex().getY()), 3);
		return boundingBox;
	}
}