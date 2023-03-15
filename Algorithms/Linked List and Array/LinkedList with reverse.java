public class LinkedList {

    Node head;

    public LinkedList() {
    }

    //Iterative T O(n) M O(1)
    public void reverseListIterative() {
        if (head == null) return;
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev; // 1 now points to null
            prev = curr; // prev is now 1
            curr = next; // old curr.next, curr is now at 2
        }
        this.head = prev;
    }

    public void add(int val) {
        if (head == null) head = new Node(val);
        else head = new Node(val, head);
    }

    public int pop() {
        int temp = head.val;
        this.head = head.next;
        return temp;
    }

    public void traverse() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }

// recursive T O(N) M(N)

    public Node reverseListRecursive(Node head) {
        if (head == null) return null;
        Node newHead = head; // newHead is the root "5"
        if (head.next != null) {
            newHead = reverseListRecursive(head.next); // recursive to the end and return forward the result "root"
            head.next.next = head; // Ex. null <- 5 and want to insert 4. You want 5 "head.next.next" to point to 4 "head". Returning 4 <- 5
        }
        head.next = null; // returning null <- 4 <- 5
        return newHead; // push the root 5 back up the recursive.
    }

    public class Node {

        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList intList = new LinkedList();
        int[] nums = {1, 2, 3, 4, 5};
        for (int num : nums) {
            intList.add(num);
        }
        System.out.println("Original");
        intList.traverse();

        intList.reverseListIterative();

        System.out.println("Reverse");
        intList.traverse();


        System.out.println("Pop");
        System.out.println(intList.pop());

    }
}
