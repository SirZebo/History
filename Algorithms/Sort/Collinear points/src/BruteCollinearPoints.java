import java.util.ArrayList;

public class BruteCollinearPoints {

    private final Point[] pointArray;
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] pointArray) {   // finds all line segments containing 4 points

        // 1. Check Null
        if (pointArray == null)
            throw new NullPointerException("Input is null.");
        for (Point points : pointArray) {
            if (points == null) {
                throw new NullPointerException("Input contains null.");
            }
        }


        // 2. Check for duplicate points
        Mergesort<Point> tempPointArray = new Mergesort<>(pointArray);
        tempPointArray.sort();
        this.pointArray = tempPointArray.getArr();
        CheckDuplicatePoints();
        ArrayList<LineSegment> collinearSegmentArrayList = new ArrayList<>();
        // 3. Collinear Points min is 4
        if (pointArray.length > 3) {

            // 4. For each permutation, check if 4 points are collinear

            Point[] tempPoints = new Point[4];

            for (int i = 0; i < pointArray.length; i++) {
                tempPoints[0] = pointArray[i];
                for (int j = i + 1; j < pointArray.length; j++) {
                    tempPoints[1] = pointArray[j];
                    for (int k = j + 1; k < pointArray.length; k++) {
                        tempPoints[2] = pointArray[k];
                        for (int l = k + 1; l < pointArray.length; l++) {
                            tempPoints[3] = pointArray[l];
                            if (collinear(tempPoints)) {
                                LineSegment collinearSegment = getSegment(tempPoints);
                                collinearSegmentArrayList.add(collinearSegment);
                            }
                        }
                    }
                }
            }

        }
        segments = collinearSegmentArrayList.toArray(new LineSegment[collinearSegmentArrayList.size()]);
    }

    public int numberOfSegments() {     // the number of line segments
        return segments().length;
    }

    /**
     * getSegments()
     *
     * @return segments (should only contain collinear segments)
     */
    public LineSegment[] segments() {               // the line segments
        return segments;
    }

    /**
     * The method segments() should include each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s,
     * then you should include either the line segment p→s or s→p (but not both) and
     * you should not include subsegments such as p→r or q→r.
     *
     * @param temp
     * @return
     */
    public LineSegment getSegment(Point[] temp) {  //
        return new LineSegment(temp[0], temp[temp.length - 1]);
    }

    /**
     * Check if Point array contains duplicates
     */
    private void CheckDuplicatePoints() {
        if (pointArray.length > 1) { // Cannot compare if there is only 1 item
            for (int i = 1; i < pointArray.length; i++) {
                if (pointArray[i].compareTo(pointArray[i - 1]) == 0)
                    throw new IllegalArgumentException("Input contain duplicate");
            }
        }
    }

    public boolean collinear(Point[] temp) {
        if ((temp[0].slopeOrder().compare(temp[1], temp[2]) == 0) && (temp[0].slopeOrder().compare(temp[2], temp[3]) == 0)) {
            return true;
        }
        return false;
    }
}

