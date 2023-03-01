import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> listString = new Deque<>();
        listString.addFirst(1);
        listString.addFirst(2);
        listString.addFirst(3);
        listString.addFirst(4);
        listString.addFirst(5);
        for (int element : listString) {
            System.out.println(element);
        }
    }

}

class Deque<Item> implements Iterable<Item> {
    int listSize;
    private Node first; //  head of the deque.
    private Node last; //  tail of the deque.

    private class Node { //  Create of an object called node that can contain...
        Item item; //  container to store data
        Node next; //  pointer to next node
        Node prev; //  pointer to prev node

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    // construct an empty deque
    public Deque() {
        first = new Node(null);
        last = new Node(null);
        listSize = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first.item == null;
    }

    // return the number of items on the deque
    public int size() {
        return listSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node(item);
        first.prev = null;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        } //  We already declared first node is the new node. First --> new node <-- Last
        else {
            oldFirst.prev = first;
        }
        listSize++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node(item);
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        listSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        listSize--;
        if (isEmpty()) {
            last = first;
        }  //  If empty, first.next will be null. Resulting this.first to be null. We just make last = null. AKA I complicate you >:3
        else {
            first.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        listSize--;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

}

