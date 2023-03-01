import java.util.List;
import java.util.ArrayList;

public class Main {
    private static List<String> txtList = new ArrayList<String>();

    static {
        txtList.add("to");
        txtList.add("be");
        txtList.add("or");
        txtList.add("not");
        txtList.add("to");
        txtList.add("-");
        txtList.add("be");
        txtList.add("-");
        txtList.add("-");
        txtList.add("that");
        txtList.add("-");
        txtList.add("-");
        txtList.add("-");
        txtList.add("is");
    }

    public static void main(String[] args) { // Test Client
        ResizingStackArray stack = new ResizingStackArray();
        for (String txt : txtList) {
            if (txt.equals("-")) 
            System.out.print(stack.Pop() + " ");
            else stack.Push(txt);
        }
    }
}

class LinkedList {
    private Node first = null; //  create an empty stack

    private class Node { //  40 bytes per stack node
        String item;
        Node next;
        /*
        Node(String item) {
            this.item = item;
            next = null;
        }
         */
    }

    public void Push(String item) { //  insert a new string onto stack
        Node oldFirst = first; // Save the first node
        first = new Node(); // First node becomes empty => make space for new node
        first.item = item; // New node contains pushed item
        first.next = oldFirst; // Add back the previous node that got accidentally removed
    }

    public String Pop() { //  remove and return the string most recently added
        String item = first.item; //Save item popping out of stack
        first = first.next; // Redeclare the first to the next in the list.
        return item; // return the popped item
    }

    public boolean IsEmpty() { //  is the stack empty?
        return first == null;
    }
}

class FixedStackArray {
    private String[] stringArray;
    private int N = 0;

    public FixedStackArray(int capacity) { //  Fatal flaw of requiring declaration of capacity
        stringArray = new String[capacity];
    }

    public boolean IsEmpty() {
        return N == 0;
    }

    public void Push(String item) { //  overflow, adding above capacity of array
        stringArray[N++] = item;
    }

    public String Pop() { // underflow, popping empty stack
        return stringArray[--N];//  Loitering,
    }
}

class ResizingStackArray { // 8N bytes when full, 32N bytes when 1/4 full
    private String[] stringArray;
    private int N = 0;

    public ResizingStackArray() {
        stringArray = new String[1];
    }

    public boolean IsEmpty() {
        return N == 0;
    }

    public void Push(String item) { //  overflow, adding above capacity of array
        if (N == stringArray.length) resize(2 * stringArray.length);// Changes N to lg N. (Algo changes N^2 to N lg N)
        stringArray[N++] = item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) { // N time
            copy[i] = stringArray[i];
        }
        stringArray = copy;
    }

    public String Pop() {
        String item = stringArray[--N];
        stringArray[N] = null;
        if (N > 0 && N == stringArray.length / 4)
            resize(stringArray.length / 2); // Prevents underflow && 1/4 array due to thrashing ( "push pop push pop" cost N^2 time).
        return item;
    }
} //Amortized time is only 1, while worst case (resizing is N)
