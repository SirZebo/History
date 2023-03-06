public abstract class Heap<T extends Comparable<T>> implements IHeap<T> {
    protected T[] heap;
    protected int position = -1;

    public Heap() {
        this.heap = (T[]) new Comparable[2];
    }

    @Override
    public IHeap<T> insert(T data) {
        if (isFull()) resize(2 * heap.length);
        heap[++position] = data;
        heapOrderUpward();
        return this;
    }

    @Override
    public T getRoot() {
        if (isEmpty()) return null;

        T result = heap[0];
        swap(0, position--);
        heap[position + 1] = null; // prevent loitering
        heapOrderDownwards(position);
        return result;
    }

    public void sort() {

    }


    protected abstract void heapOrderUpward();

    protected abstract void heapOrderDownwards(int endIndex);

    protected void swap(int firstIndex, int secondIndex) {
        T temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    private boolean isEmpty() {
        return position == -1;
    }

    private boolean isFull() {
        return position == heap.length - 1;
    }

    private void resize(int capacity) {
        System.arraycopy(heap, 0, heap = (T[]) new Comparable[capacity], 0, position + 1);
    }

}
