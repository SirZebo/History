public class LineSegment {

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
    /*
    public   void draw()                        // draws this line segment
     */
}
