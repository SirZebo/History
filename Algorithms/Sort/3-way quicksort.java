import java.util.Arrays;

// Can be linear time
public class Quicksort<T extends Comparable<T>> {
    private final T[] arr;

    public Quicksort(T[] arr) {
        this.arr = arr;
    }

    public void sort() {
        // Random shuffle arr for performance guaranteed ~1.39 N log N. Else, worst case is 1/2 N^2
        quicksort(0, arr.length - 1);
    }

    private void quicksort(int lo, int hi) { // 3-way sort
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        T v = arr[lo];
        int i = lo;

        while (i <= gt) {
            int cmp = arr[i].compareTo(v); // v is just some random pivot to sort by
            if (cmp < 0) swap(lt++, i++); // if arr[lt] < arr[i]. Swap to lt++ and i++
            else if (cmp > 0) swap(i, gt--); // if arr[lt] > arr[i]. Swap to gt--
            else i++; // if arr[lt] == arr[i]
        }

        quicksort(lo, lt - 1);
        quicksort(gt + 1, hi);
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
        Integer[] integers = {10, 55, -5, 34, 7, 22, 19, 7, 7, 7};
        String[] strings = {"Oranges", "Grapes", "Apple", "Pears", "Mango", "Bananas", "Bananas", "Bananas"};
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
        new Quicksort<>(integers).sort();
        new Quicksort<>(strings).sort();
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
    }
}
