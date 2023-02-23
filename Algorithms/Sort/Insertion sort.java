public class Main {
    public static void main(String[] args) {

        Integer[] intArray = new Integer[8];
        intArray[0] = 3;
        intArray[1] = 1;
        intArray[2] = 8;
        intArray[3] = 7;
        intArray[4] = 2;
        intArray[5] = 5;
        intArray[6] = 4;
        intArray[7] = 0;
        InsertionSort<Integer> arr = new InsertionSort<>(intArray);
        arr.sort();
        for (int num : arr.arr) {
            System.out.println(num);
        }
    }
}

public class InsertionSort<T extends Comparable<T>> {

    public T[] arr;

    public InsertionSort(T[] arr) {
        this.arr = arr;
    }

    public void sort() {
        int N = arr.length;
        for (int i = 1; i < N; i++) // For N items to sort
        {
            int j = i;
            while (j > 0 &&
                    arr[j].compareTo(arr[j - 1]) < 0) // Excluding sorted items, check every non-sorted items
            {
                T swap = arr[j];
                arr[j] = arr[j - 1];
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
                    && arr[j].compareTo(arr[j - 1]) < 0; j--) // Excluding sorted items, check every non-sorted items
            {
                T swap = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = swap;
            }
        }
    }

     */
}
