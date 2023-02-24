import java.util.*;


public class Main {
    public static void main(String[] args) {
      Integer[] integers = {3,4,-1,5,-4};
      String[] strings = {"Apples", "Oranges", "Grapes", "Pears"};
      System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
      new MergeSort<>(integers).sort();
      new MergeSort<>(strings).sort();
      System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
  }
}

public class MergeSort<T extends Comparable<T>> {
  private final T[] arr;
  
  public MergeSort(T[] arr){
    this.arr = arr;
  }
  
  public void sort() {
    mergesort(0 , arr.length -1);
  }
  
  private void mergesort(int lo,  int hi) {
    if (lo >= hi) {
      return;
    }
    int mid = (lo + hi) / 2; // initialise the 2 sub array
    mergesort(lo, mid); // Since technicallly need to merge 2 sub array that requires (lo, mid, hi) for 2 merge
    mergesort(mid +1, hi); // keeps dividing until array size = 1
    merge(lo, mid, hi);
  }
  
  private void merge(int lo, int mid, int hi){
    T[] leftArray = (T[]) new Comparable[mid - lo + 1]; // index starts at 0 instead of 1
    T[] rightArray = (T[]) new Comparable[hi - mid];
    
    System.arraycopy(arr, lo, leftArray, 0, leftArray.length);
    System.arraycopy(arr, mid + 1, rightArray, 0 , rightArray.length);
    
    int leftSubArrIndex = 0;
    int rightSubArrIndex = 0;
    int arrIndex = lo;
    
    while (leftSubArrIndex < leftArray.length && rightSubArrIndex < rightArray.length) {
      arr[arrIndex++] = leftArray[leftSubArrIndex].compareTo(rightArray[rightSubArrIndex]) <= 0 
      ? leftArray[leftSubArrIndex++] : rightArray[rightSubArrIndex++];
      /*
      if (leftArray[leftSubArrIndex].compareTo(rightArray[rightSubArrIndex]) <= 0 ) {
        arr[arrIndex] = leftArray[leftSubArrIndex];
        arrIndex++;
        leftSubArrIndex++;
      } else {
        arr[arrIndex] = rightArray[rightSubArrIndex];
        arrIndex++;
        rightSubArrIndex++;
      }
      */
    }
    while (leftSubArrIndex < leftArray.length) {
      arr[arrIndex++] = leftArray[leftSubArrIndex++];
    }
    while (rightSubArrIndex < rightArray.length) {
      arr[arrIndex++] = rightArray[rightSubArrIndex++];
    }
  }
}
