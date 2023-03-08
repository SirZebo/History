import java.util.*;

public class Main {
    public static void main(String[] args) {
      /*
      MaxPQ<Integer> pq = new MaxPQ<>(7);
      pq.insert(7);
      pq.insert(1);
      pq.insert(5);
      pq.insert(4);
      pq.insert(3);
      pq.insert(6);
      pq.insert(2);
      while(!pq.isEmpty()){
        System.out.println(pq.delMax());
      }
      */
      MinimumHeap<Integer> heap = new MinimumHeap<>();
      Integer[] integers = {7,1,5,4,3,6,2};
      for(Integer integer : integers) {
        heap.insert(integer);
      }
      heap.sort();
  }
}

public class MaxPQ<Key extends Comparable<Key>> {
  // PQ starts from index 1 instead of 0
  private Key[] pq;
  private int N;
  
  public MaxPQ(int capacity) {
    pq = (Key[]) new Comparable[capacity+1];
  }
  
  public boolean isEmpty() {
    return N == 0;
  }
  
  public void insert(Key x) {
    pq[++N] = x;
    swim(N);
  }
  
  public Key delMax() { // 2 Lg N compares
    Key max = pq[1]; // The root of the tree. Temp, to be return;
    exch(1, N--); // This makes the program re-sort the tree since the root is no longer the biggest
    pq[N+1] = null; // remove the max
    sink(1); // Checks the tree so that it will be heap ordered
    return max;
  }
  
  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {
      exch(k, k/2);
      k = k/2;
    }
  }
  
  private void sink(int k) { // K is dependent on array size, not array index
    while (2*k < N) {
      int j = 2*k;
      if (j < N && less(j, j+1)) j++; // j < N to prevent out of bound, less(j, j+1) is choosing the bigger child out of the 2 to compare
      if (!less(k,j)) break; //  if no need to swap, tree is in order. Break
      exch(k, j); // if swap, means need to check further. Continue while loop
      k = j;
    }
  }
  
  private boolean less(int a, int b) {
    return pq[a].compareTo(pq[b]) < 0 ; 
  }
  
  private void exch(int firstIndex, int secondIndex) {
    Key temp = pq[firstIndex];
    pq[firstIndex] = pq[secondIndex];
    pq[secondIndex] = temp;
  }
}

public interface IHeap<T extends Comparable<T>> {
  
  IHeap<T> insert(T data);
  
  T getRoot();
  
  void sort();
}

public abstract class Heap<T extends Comparable<T>> implements IHeap<T> { // Since there is maximum heap and minimum heap, we abstract them into 1 class
  protected T[] heap;
  protected int position = -1; // index
  
  public Heap() {
    heap = (T[]) new Comparable[2];
  }
  
  @Override
  public IHeap<T> insert(T data) {
    if (isFull()) {
      resize(2 * heap.length);
    }
    heap[++position] = data;
    heapOrderUpward();
    return this; // return the whole tree from heapOrderedUpward()
  }
  
  @Override
  public T getRoot() {
    if (isEmpty()) {
      return null;
    }
    T result = heap[0];
    heap[0] = heap[position--];
    heap[position+1] = null; // prevent loitering
    heapOrderDownward(position);
    return result;
  }
  
  @Override
  public void sort() {
    for (int i = 0 ; i <= position; i++){
      swap(0, position - i);
    heapOrderDownward( (position - 1) - i);
    }
    Arrays.stream(heap).forEach(System.out::println);
  }
  
  private void resize(int capacity) {
    System.arraycopy(heap, 0, heap = (T[]) new Comparable[capacity], 0, position + 1);
  }
  
  protected abstract void heapOrderUpward();
  protected abstract void heapOrderDownward(int endIndex);
  
  private boolean isFull() {
    return position == heap.length - 1;
  }
  
  private boolean isEmpty() {
    return heap.length == 0;
  }
  
  protected void swap( int firstIndex, int secondIndex) {
    T temp = heap[firstIndex];
    heap[firstIndex] = heap[secondIndex];
    heap[secondIndex] = temp;
  }
}

public class MaximumHeap<T extends Comparable<T>> extends Heap<T> {
  
  protected void heapOrderUpward() {
    int index = position;
    //leftIndex = 2 * parentIndex + 1; // childIndex 
    int parentIndex = (index - 1) / 2;
    while (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) > 0) { // childIndex > parentIndex. Should be parent > child
      swap(index, parentIndex);
      //Update both index for while loop
      index = parentIndex;
      parentIndex = (index - 1) / 2;
    }
  }
  
  protected void heapOrderDownward(int endIndex) {
    if (endIndex == -1) return; // Because position--, there could be no data in heap
    int index = 0;
    while (index <= endIndex) {
      int leftChildIndex = 2 * index + 1;
      int rightChildIndex = 2 * index + 2;
      if (leftChildIndex > endIndex) break;
      
      int childToSwap = rightChildIndex > endIndex 
        ? leftChildIndex 
        : heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0
          ? leftChildIndex
          : rightChildIndex;
      //if (leftChildIndex < endIndex && heap[leftChildIndex].compareTo(heap[rightChildIndex] > 0))
      if (heap[index].compareTo(heap[childToSwap]) > 0) break;
      swap(index, childToSwap);
      index = childToSwap;
    }
    /*
    while (2*k < N) {
      int j = 2*k;
      if (j < N && less(j, j+1)) j++; // j < N to prevent out of bound, less(j, j+1) is choosing the bigger child out of the 2 to compare
      if (!less(k,j)) break; //  if no need to swap, tree is in order. Break
      exch(k, j); // if swap, means need to check further. Continue while loop
      k = j;
    }
    */
  }
}

public class MinimumHeap<T extends Comparable<T>> extends Heap<T> {
  protected void heapOrderUpward() {
    int index = position;
    //leftIndex = 2 * parentIndex + 1; // childIndex 
    int parentIndex = (index - 1) / 2;
    while (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) < 0) { // childIndex > parentIndex. Should be parent > child
      swap(index, parentIndex);
      //Update both index for while loop
      index = parentIndex;
      parentIndex = (index - 1) / 2;
    }
  }
  
  protected void heapOrderDownward(int endIndex) {
    if (endIndex == -1) return; // Because position--, there could be no data in heap
    int index = 0;
    while (index <= endIndex) {
      int leftChildIndex = 2 * index + 1;
      int rightChildIndex = 2 * index + 2;
      if (leftChildIndex > endIndex) break;
      
      int childToSwap = rightChildIndex > endIndex 
        ? leftChildIndex 
        : heap[leftChildIndex].compareTo(heap[rightChildIndex]) < 0 
          ? leftChildIndex
          : rightChildIndex;
      //if (leftChildIndex < endIndex && heap[leftChildIndex].compareTo(heap[rightChildIndex] > 0))
      if (heap[index].compareTo(heap[childToSwap]) < 0) break;
      swap(index, childToSwap);
      index = childToSwap;
    }
    /*
    while (2*k < N) {
      int j = 2*k;
      if (j < N && less(j, j+1)) j++; // j < N to prevent out of bound, less(j, j+1) is choosing the bigger child out of the 2 to compare
      if (!less(k,j)) break; //  if no need to swap, tree is in order. Break
      exch(k, j); // if swap, means need to check further. Continue while loop
      k = j;
    }
    */
  }
}
