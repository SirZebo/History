import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D(2, 1));
        points.add(new Point2D(2, 3));
        points.add(new Point2D(5, 1));
        points.add(new Point2D(0, 0));
        points.add(new Point2D(4, -2));
        points.add(new Point2D(2, -3));
        points.add(new Point2D(1, -2));
        points.add(new Point2D(-1, -1));
        points.add(new Point2D(-3, -3));
        points.add(new Point2D(-2, 2));
        points.add(new Point2D(-4, 1));
    }
}
import java.util.List;

public class Point2D {
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int CCW(Point2D a, Point2D b, Point2D c) {
        double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area > 0) {
            return 1; // counter-clockwise
        } else if (area < 0) {
            return -1; // clockwise
        } else {
            return 0; // area == 0, collinear
        }
    }

    public static void ConvexHull(List<Point2D> listPoints) {
        for (int i = 0; i) {

        }
    }

}
