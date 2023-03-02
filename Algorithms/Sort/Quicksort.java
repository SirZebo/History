import java.util.Arrays;

// Quicksort goes quadratic time if
//  1. Array is sorted or reverse-sorted
//  2. Array contains many duplicates
public class Quicksort<T extends Comparable<T>> {
    private final T[] arr;

    public Quicksort(T[] arr) {
        this.arr = arr;
    }

    public void sort() {
        // Random shuffle arr for performance guaranteed ~1.39 N log N. Else, worst case is 1/2 N^2
        quicksort(0, arr.length - 1);
    }

    private void quicksort(int lo, int hi) {
        if (lo >= hi) { // breaking recursion. When partition size = 1
            return;
        }
    /*
    if (hi <= lo + CUTOFF -1) { // Maybe expensive for smaller subarrays. CUTOFF = 10 to insertion sort
      Insertion.sort(a, lo, hi);
      return;
    }
    // 10% faster (12/35 N ln N) exchanges if median is used
    int m = medianOf3(lo, lo + (hi - lo)/2 , hi);
    swap(lo, m);
    */

        int pivot = partition(lo, hi);
        // Since we know that the pivot is already sorted in place. We just need to sort lo <- pivot - 1, pivot + 1 -> hi.
        quicksort(lo, pivot - 1);
        quicksort(pivot + 1, hi);
    }

    private int partition(int lo, int hi) {
        int pivotIndex = (lo + hi) / 2;
        swap(pivotIndex, hi);

        int pivotCounterIndex = lo;
        for (int i = lo; i < hi; i++) {
            if (arr[i].compareTo(arr[hi]) <= 0) { // When arr[i] is smaller, pivot + 1 and move it to the left of pivot.
                swap(pivotCounterIndex++, i);
            }
        }
        swap(pivotCounterIndex, hi);
        return pivotCounterIndex;
    }

    private void swap(int firstIndex, int secondIndex) {
        if (firstIndex != secondIndex) {
            T temp = arr[secondIndex];
            arr[secondIndex] = arr[firstIndex];
            arr[firstIndex] = temp;
        }
    }

    private T select(int k) { // select the k th item. Linear N time
        //random.shuffle(arr)
        int lo = 0, hi = arr.length - 1;
        while (hi > lo) {
            int j = partition(lo, hi);
            if (k > j) lo = j + 1; // in the right array
            else if (k < j) hi = j - 1; // in the left array
            else return arr[k];
        }
        return arr[k];
    }
}

public class Main {
    public static void main(String[] args) {
        Integer[] intergers = {10, 55, -5, 34, 7, 22, 19};
        String[] strings = {"Oranges", "Grapes", "Apple", "Pears", "Mango", "Bananas"};
        System.out.println(Arrays.toString(intergers) + " | " + Arrays.toString(strings));
        new Quicksort<>(intergers).sort();
        new Quicksort<>(strings).sort();
        System.out.println(Arrays.toString(intergers) + " | " + Arrays.toString(strings));
    }
}

