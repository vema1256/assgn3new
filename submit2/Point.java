/*************************************************************************
 * Name: Mahesh Vemula
 * Email:  
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public  final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }


    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    private void checkValidPoint(Point p) {
        if (p == null) {
            throw new  NullPointerException();
        }
   }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        checkValidPoint(that);
        if (that.x == x) {
        	//StdOut.printf( "SlopeLog Point x (%s) Point Y (%s)\n", this.toString(), that.toString());
        	if (y > that.y ) return Double.POSITIVE_INFINITY;
        	if (y <= that.y ) return Double.NEGATIVE_INFINITY;

        }
        return ((double) (y - that.y))/((double) (x - that.x ));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        checkValidPoint(that);
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        if (x < that.x) return  -1;
        if (x > that.x) return  +1;
        return 0;
    }

    private static int sign(double f) {
    	// From StackOverflow 
	    if (f != f) throw new IllegalArgumentException("NaN");
	    if (f == 0) return 0;
	    f *= Double.POSITIVE_INFINITY;
	    if (f == Double.POSITIVE_INFINITY) return +1;
	    if (f == Double.NEGATIVE_INFINITY) return -1;

	    //this should never be reached, but I've been wrong before...
	    throw new IllegalArgumentException("Unfathomed double");
	}

	private static int slopecompare(Point px, Point py, Point pz) {
		double fxy = px.slopeTo(py);
		double fxz = px.slopeTo(pz);
        if (fxy ==  Double.NEGATIVE_INFINITY) fxy = -fxy;
        if (fxz ==  Double.NEGATIVE_INFINITY) fxz = -fxz;

		boolean fxyisinf = (fxy ==  Double.POSITIVE_INFINITY) || (fxy ==  Double.NEGATIVE_INFINITY);
		boolean fxzisinf = (fxz ==  Double.POSITIVE_INFINITY) || (fxz ==  Double.NEGATIVE_INFINITY);
		 
		if (fxyisinf && fxzisinf) return 0;
		return sign(fxy - fxz);
	}

    private class BySlope implements Comparator<Point> 
    {
  	    	public int compare(Point px, Point py) {
     		return   slopecompare(Point.this , px, py);
    	}
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
 
     // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p = new Point (4, 4);
        Point q = new Point (4, 0);
        Point r = new Point (2, 6);

        StdOut.printf("Slope is %f\n", p.slopeTo(q)); 
        StdOut.printf("Slope is %f\n", p.slopeTo(r)); 
        Comparator slopeComparator =  p.SLOPE_ORDER;
        StdOut.printf("Slope is %d\n", slopeComparator.compare(q, r)); 

        // Point p = new Point(344, 294);
        // Point q = new Point(339, 294);
        // StdOut.printf("Slope is %f\n", p.slopeTo(q)); 

        StdOut.printf("\n");
    }
}