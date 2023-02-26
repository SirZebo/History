public class Mergesort<T extends Comparable<T>> {

    private final T[] arr;

    /**
     * Initialize the array that needs to be sorted
     *
     * @param arr the array that is going to be sorted
     */
    public Mergesort(T[] arr) {
        this.arr = arr;
    }

    public T[] getArr() {
        return arr;
    }

    public void sort() {
        mergesort(0, arr.length - 1);
    }

    /**
     * 1. It will overload mergesort and divide til array has a size of 1
     * <br>
     * 2. Return to the previous overloaded mergesort and divide another array til array has a size 1
     * <br>
     * 3. Return to the previous overloaded mergesort and merge the 2 array (sorted).
     * <br>
     *
     * @param lo is an index of an array. Determines where the method should start sorting
     * @param hi is an index of an array. Determines where the method should only sort.
     */
    public void mergesort(int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (hi + lo) / 2;
        mergesort(lo, mid);
        mergesort(mid + 1, hi);
        merge(lo, mid, hi);
    }

    /**
     * 1. It creates 2 auxiliary array to use for sorting.
     * <br>
     * 2. Copies over the data that needs to be sorted
     * <br>
     * 3. CompareTo() between 2 auxiliary array and rewrites arr with sorted data
     * <br>
     * 4. Rewrites arr with remaining data when either auxiliary array is empty (Nothing left to compareTo)
     * <br>
     *
     * @param lo  is an index of an array. Determines the start of the first array.
     * @param mid is an index of an array. Determines the end of the first array.
     * @param hi  is an index of an array. Determines the end of the second array.
     */
    private void merge(int lo, int mid, int hi) {
        T[] leftArray = (T[]) new Comparable[mid - lo + 1];
        T[] rightArray = (T[]) new Comparable[hi - mid];

        System.arraycopy(arr, lo, leftArray, 0, leftArray.length);
        System.arraycopy(arr, mid + 1, rightArray, 0, rightArray.length);

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int arrIndex = lo;

        while (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
            arr[arrIndex++] = leftArray[leftArrayIndex].compareTo(rightArray[rightArrayIndex]) <= 0
                    ? leftArray[leftArrayIndex++] : rightArray[rightArrayIndex++];
        }
        while (leftArrayIndex < leftArray.length) {
            arr[arrIndex++] = leftArray[leftArrayIndex++];
        }
        while (rightArrayIndex < rightArray.length) {
            arr[arrIndex++] = rightArray[rightArrayIndex++];
        }
    }
}
