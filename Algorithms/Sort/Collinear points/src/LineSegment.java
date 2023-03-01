public class LineSegment implements Comparable<LineSegment> {

    private final Point p;
    private final Point q;

    /**
     * Initializes a new line segment.
     *
     * @param p one endpoint
     * @param q the other endpoint
     * @throws NullPointerException if either p or q
     *                              is null
     */
    public LineSegment(Point p, Point q) {      // constructs the line segment between points p and q
        if (p == null || q == null) {
            throw new NullPointerException("Argument is null");
        }
        this.p = p;
        this.q = q;
    }
    
    public Point getPointP() {
        return p;
    }

    public Point getPointQ() {
        return q;
    }

    /**
     * Returns a string representation of this point.
     *
     * @return a string representation of this point
     * @Override toString() method
     */
    @Override
    public String toString() {                 // string representation
        return p.toString() + " ---> " + q.toString();
    }
    
    public int compareTo(LineSegment that) {
        if ((this.p).slopeTo(this.q) < (that.p).slopeTo(that.q)) return -1;
        if ((this.p).slopeTo(this.q) > (that.p).slopeTo(that.q)) return 1;
        else return 0;
    }
    
    
    
    /*
    public   void draw()                        // draws this line segment
     */
}
