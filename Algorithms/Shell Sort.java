import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point2D> a = new ArrayList<>();
        a.add(new Point2D(1, 1));
        a.add(new Point2D(2, 1));
        a.add(new Point2D(3, 1));
        a.add(new Point2D(4, 1));
        a.add(new Point2D(5, 1));
        List<Point2D> b = new ArrayList<>();
        b.add(new Point2D(1, 1));
        b.add(new Point2D(2, 1));
        b.add(new Point2D(3, 1));
        b.add(new Point2D(-4, 1));
        b.add(new Point2D(-5, 1));
        Point2D[] ArrayA = a.toArray(new Point2D[5]);
        Point2D[] ArrayB = b.toArray(new Point2D[5]);
        
    }
}
public class Point2D {
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
public class Shell<T extends Comparable<T>> {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {

                }
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}


