import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final Point[] pointArray;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] pointArray) {

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
        Point[] tempPointArraySorted = tempPointArray.getArr();
        this.pointArray = tempPointArray.getArr();
        CheckDuplicatePoints();

        // 3. For each point sort by slope and check for collinear
        ArrayList<LineSegment> collinearSegmentArrayList = new ArrayList<>();
        if (pointArray.length > 3) {
            for (Point p : pointArray) {
                Arrays.sort(tempPointArraySorted, p.slopeOrder());
                CollinearSegments(tempPointArraySorted, p, collinearSegmentArrayList);
            }
        }

        // 4. Check for duplicate collinear
        collinearSegmentArrayList = CheckDuplicateSegment(collinearSegmentArrayList);
        segments = collinearSegmentArrayList.toArray(new LineSegment[collinearSegmentArrayList.size()]);

    }

    /**
     * Finds collinear segments of Point P
     * <br>
     * 1. Check if there are 3 or more points to P with the same slope
     * <br>
     * 2. Return collinear points with reference to P
     *
     * @param tempPointArraySorted      is the array of points we are checking
     * @param p                         is the point we are check collinear points
     * @param collinearSegmentArrayList returns collinear segments
     */
    private void CollinearSegments(Point[] tempPointArraySorted, Point p, ArrayList<LineSegment> collinearSegmentArrayList) {
        int collinearCounterIndex = 0;
        for (int i = 3; i < tempPointArraySorted.length; i++) {
            if (p.slopeOrder().compare(tempPointArraySorted[i - 2], tempPointArraySorted[i - 1]) == 0
                    && p.slopeOrder().compare(tempPointArraySorted[i - 1], tempPointArraySorted[i]) == 0) {
                collinearCounterIndex++; // Min segment = {p, i-2, i-1, i}. Content is p + start ---> i
            } else if (collinearCounterIndex > 0) { // If next point is not collinear && previous is collinear. Add segment to array
                //  getCollinearSegment(pointArray, p, int start, int i); // Create collinear segment
                collinearSegmentArrayList.add(getCollinearSegment(pointArray, p, i - 3, i - 1));  // add segment
                collinearCounterIndex = 0;
            }
        }
        if (collinearCounterIndex > 0) { // If next point is not collinear && previous is collinear. Add segment to array
            collinearSegmentArrayList.add(getCollinearSegment(pointArray, p, tempPointArraySorted.length - 3, tempPointArraySorted.length - 1));  // add segment
        }
    }

    /**
     * Generate a segment where segment is p + start ---> i. Example {p, i-2, i-1, i}.
     * <br>
     * Needs to be sorted to ensure i=0 is the start and .size() - 1 is the end of segment
     *
     * @param pointArray is the point array containing all points
     * @param p          is the reference point for slope
     * @param start      is the next collinear point after reference point
     * @param end        is the last collinear point
     * @return a collinear segment
     */
    private LineSegment getCollinearSegment(Point[] pointArray, Point p, int start, int end) {
        Point[] temp = new Point[end - start + 2];
        temp[0] = p;
        int tempIndex = 1;
        for (int i = start; i < end + 1; i++) {
            temp[tempIndex++] = pointArray[i];
        }

        /*
        Mergesort<Point> sortArray = new Mergesort<>(temp);
        sortArray.sort();
        temp = sortArray.getArr();
        */
        Arrays.sort(temp);
        return new LineSegment(temp[0], temp[temp.length - 1]);
    }

    /**
     * Sorts the segment based on the xy coords of its own points
     * Then checks if the segment are distinct.
     *
     * @param segmentArrayList is the collinear segments
     * @return distinct collinear segments
     */
    private ArrayList<LineSegment> CheckDuplicateSegment(ArrayList<LineSegment> segmentArrayList) {

        LineSegment[] segmentArray = segmentArrayList.toArray(new LineSegment[segmentArrayList.size()]);
        /*
        Mergesort sorted = new Mergesort<>(segmentArray);
        sorted.sort();
        segmentArray = (LineSegment[]) sorted.getArr();
        */
        Arrays.sort(segmentArray);
        ArrayList<LineSegment> temp = new ArrayList<>();
        temp.add(segmentArray[0]);
        for (int i = 1; i < segmentArrayList.size(); i++) {
            if (!(segmentArray[i - 1].equals(segmentArray[i]))) {
                temp.add(segmentArray[i]);
            }
        }
        return temp;
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


}
