public class Main {
    public static void main(String[] args) {
        Point[] pointArray = new Point[12];
        pointArray[0] = new Point(3, 3);
        pointArray[1] = new Point(5, 1);
        pointArray[2] = new Point(1, 1);
        pointArray[3] = new Point(1, -1);
        pointArray[4] = new Point(4, -4);
        pointArray[5] = new Point(-1, -1);
        pointArray[6] = new Point(-1, -4);
        pointArray[7] = new Point(-3, -3);
        pointArray[8] = new Point(-1, 1);
        pointArray[9] = new Point(-4, 1);
        pointArray[10] = new Point(-2, 2);
        pointArray[11] = new Point(-1, 4);
        BruteCollinearPoints arr = new BruteCollinearPoints(pointArray);
        System.out.println(arr.numberOfSegments());
        for (LineSegment line : arr.segments()) {

            System.out.println((line.getPointP()).slopeTo(line.getPointQ()));

        }
    }
}

