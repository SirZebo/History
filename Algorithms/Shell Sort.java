public class Main {
    public static void main(String[] args) {
        Point2D[] arrayA = new Point2D[5];
        arrayA[0] = new Point2D(5, 1);
        arrayA[1] = new Point2D(1, 1);
        arrayA[2] = new Point2D(4, 1);
        arrayA[3] = new Point2D(2, 1);
        arrayA[4] = new Point2D(3, 1);

        Point2D[] arrayB = new Point2D[5];
        arrayB[0] = new Point2D(1, 1);
        arrayB[1] = new Point2D(2, 1);
        arrayB[2] = new Point2D(3, 1);
        arrayB[3] = new Point2D(-4, 1);
        arrayB[4] = new Point2D(-5, 1);

        Shell<Point2D> a = new Shell<>(arrayA);
        Shell<Point2D> b = new Shell<>(arrayB);
        a.sort();
        b.sort();
    }
}
public class Point2D implements Comparable<Point2D> {
    public final double x;
    public final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point2D temp) {
        Point2D other = (Point2D) temp;
        if (this.x > other.x) {
            return 1;
        } else if (this.x < other.x) {
            return -1;
        } else if (this.y > other.y) {
            return 1;
        } else if (this.y < other.y) {
            return -1;
        } else {
            return 0;
        }
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


