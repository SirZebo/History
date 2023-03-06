public class MaximumHeap<T extends Comparable<T>> extends Heap<T> {
    protected void heapOrderUpward() {
        //childIndex1 = parentIndex * 2 + 1
        //childIndex1 = parentIndex * 2 + 2
        int index = position;
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    protected void heapOrderDownwards(int endIndex) {
        if (endIndex == -1) return; // Nothing to compare
        int index = 0;
        while (index <= endIndex) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (leftChildIndex > endIndex) break; // out of bound

            int childToSwap = rightChildIndex > endIndex
                    ? leftChildIndex
                    : heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0
                    ? leftChildIndex
                    : rightChildIndex;

            if (heap[index].compareTo(heap[childToSwap]) > 0) break;
            swap(index, childToSwap);
            index = childToSwap;

        }
    }
}
