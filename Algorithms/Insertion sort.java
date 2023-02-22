import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(5);
        intList.add(7);
        intList.add(1);
        intList.add(4);
        intList.add(2);
        intList.add(3);
        intList.add(9);
        intList.add(8);
        int[] intArray = intList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        Integer[] integerArray = IntStream.of(intArray).boxed().toArray(Integer[]::new);
        int size = integerArray.length;

        InsertionSort<Integer> arr = new InsertionSort<>(intList);
        arr.sort();
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }


}
public class InsertionSort<T extends Comparable<T>> {
    public InsertionSort(T[] arr) {
        this.arr = arr;
    }

    private final T[] arr;

    public void sort() {
        int N = arr.length;
        for (int i = 1; i < N; i++) // For N items to sort
        {
            int j = i;
            while (j > 0 &&
                    arr[j].compareTo(arr[j - 1]) < 0) // Excluding sorted items, check every non-sorted items
            {
                T swap = arr[i];
                arr[i] = arr[j - 1];
                arr[j - 1] = swap;
                j--;
            }
        }
    }

    /*
    public static void sortV1(T[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) // For N items to sort
        {
            for (int j = i; j > 0; j--) // Excluding sorted items, check every non-sorted items
            {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break; // or else it will continue loop
                }
            }
        }
    }

    private static boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static <T> void exch(T[] a, int i, int j) {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public void sortV2() {
        int N = arr.length;
        for (int i = 1; i < N; i++) // For N items to sort
        {
            for (int j = i; j > 0
                    && arr[j].compareTo(arr[j - 1] < 0); j--) // Excluding sorted items, check every non-sorted items
            {
                T swap = arr[i];
                arr[i] = arr[j - 1];
                arr[j - 1] = swap;
            }
        }
    }
    */


}
