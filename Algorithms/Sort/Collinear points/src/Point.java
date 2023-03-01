import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    /**
     * Initializes a new point.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(int x, int y) {                       // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    /*
    public   void draw()                               // draws this point
    public   void drawTo(Point that)                   // draws the line segment from this point to that point
    */

    /**
     * Comparing points by their y-coordinates, breaking ties by their x-coordinates.
     *
     * @param that -> The Point to be compared.
     * @return Returns the value if this Point is greater (1) or lower than that point (-1).
     * If same exact point, returns 0.
     */
    public int compareTo(Point that) {     // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.y > that.y) return 1;
        else if (this.y < that.y) return -1;
        else if (this.x > that.x) return 1;
        else if (this.x < that.x) return -1;
        else return 0;
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0).
     * <p>
     * Edge case: The slope is defined to be
     * 0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal
     *
     * @param that -> The Point to be compared.
     * @return the slope between this point and that specified point
     */
    public double slopeTo(Point that) {      // the slope between this point and that point
        if (this.x == that.x) {
            if (this.y == that.y) {
                return Double.NEGATIVE_INFINITY; // Same point ah
            }
            return Double.POSITIVE_INFINITY; // Vertical line
        } else if (this.y == that.y) return 0; // Horizontal line}
        else return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Override vs New Class -> implements
     * <p>
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    /*
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                if (slopeTo(a) < slopeTo(b)) return -1;
                else if (slopeTo(a) > slopeTo(b)) return 1;
                else return 0;
            }
        };
    }
    */
    public Comparator<Point> slopeOrder() {              // compare two points by slopes they make with this point. Comparable for 1 properity in an object. Comparable is to compare 2 object (multiple properities)
        return new slopeOrder();
    }

    private class slopeOrder implements Comparator<Point> { // New comparator object (slopeOrder)

        public int compare(Point a, Point b) { // Compare 2 points (a, b) with respect to this point.
            if (abs(slopeTo(a) - slopeTo(b)) <= 1e-6) return 0;
            else if (slopeTo(a) < slopeTo(b)) return -1;
            else if (slopeTo(a) > slopeTo(b)) return 1;
            else throw new ArithmeticException();
        }
    }


    /**
     * Returns a string representation of this point.
     *
     * @return a string representation of this point
     * @Override toString() method
     */

    public String toString() {
        return "(" + x + ", " + y + " )";
    }
    
    public boolean equals(Point that) {
        return (this.x == that.x && this.y == that.y);
    }
}
