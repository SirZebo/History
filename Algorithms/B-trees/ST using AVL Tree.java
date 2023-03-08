import java.util.LinkedList;
import java.util.Queue;

public interface ST<Key extends Comparable<Key>, Value> {

    ST<Key, Value> put(Key key, Value val);

    Value get(Key key);

    void delete(Key key);

    Iterable<Key> iterator();

    Key getMax();

    Key getMin();

    boolean isEmpty();

    Key floor(Key key);

    Key ceiling(Key key);
}

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {

    private Node<Key, Value> root;

    @Override
    public ST<Key, Value> put(Key key, Value val) {
        root = put(key, val, root);
        return this;
    }

    private Node<Key, Value> put(Key key, Value val, Node<Key, Value> node) {
        if (node == null) return new Node<>(key, val);
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftChild(put(key, val, node.getLeftChild()));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRightChild(put(key, val, node.getRightChild()));
        }
        //return node; // So to leave node untouched, the node will be .set(.get())
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> result = get(key, root);
        if (result == null) return null;
        return get(key, root).getVal();
    }

    private Node<Key, Value> get(Key key, Node<Key, Value> node) {
        if (node == null) return null;
        if (key.equals(node.getKey())) return node;
        else if (key.compareTo(node.getKey()) < 0) {
            return get(key, node.getLeftChild());
        }
        /*
        else if (key.compareTo(node.getKey()) > 0) {
            return get(key, node.getRightChild());
        }
        return null;
         */
        else return get(key, node.getRightChild());
    }

    @Override
    public void delete(Key key) {
        root = delete(key, root);
    }

    private Node<Key, Value> delete(Key key, Node<Key, Value> node) {
        if (node == null) return null;
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftChild(delete(key, node.getLeftChild()));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRightChild(delete(key, node.getRightChild()));
        } else { // key == .getKey(). Found key
            // One Child or Leaf Node
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            } else { // Two Children
                node.setKey(getMax(node.getLeftChild()));
                node.setLeftChild(delete(node.getKey(), node.getLeftChild()));
            }
        }
        //return node;
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public Iterable<Key> iterator() {
        Queue<Key> q = new LinkedList<>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node<Key, Value> node, Queue<Key> queue) {
        if (node == null) return;
        inorder(node.getLeftChild(), queue);
        queue.add(node.getKey());
        System.out.println(node.getHeight());
        inorder(node.getRightChild(), queue);
    }

    @Override
    public Key getMax() {
        if (isEmpty()) return null;
        return getMax(root);
    }

    private Key getMax(Node<Key, Value> node) {
        if (node.getRightChild() == null) return node.getKey();
        return getMax(node.getRightChild());
    }

    @Override
    public Key getMin() {
        if (isEmpty()) return null;
        return getMin(root);
    }

    private Key getMin(Node<Key, Value> node) {
        if (node.getLeftChild() == null) return node.getKey();
        return getMin(node.getLeftChild());
    }

    @Override
    public Key floor(Key key) {
        return floor(key, root).getKey();
    }

    private Node<Key, Value> floor(Key key, Node<Key, Value> node) {
        if (node == null) return null;
        if (key.equals(node.getKey())) return node;
        if (key.compareTo(node.getKey()) < 0) {
            return floor(key, node.getLeftChild());
        } //else if (key.compareTo(node.getKey()) > 0) {
        Node<Key, Value> temp = floor(key, node.getRightChild());
        if (temp == null) return node;
        else return temp;

    }

    @Override
    public Key ceiling(Key key) {
        return ceiling(key, root).getKey();
    }

    private Node<Key, Value> ceiling(Key key, Node<Key, Value> node) {
        if (node == null) return null;
        if (key.equals(node.getKey())) return node;
        if (key.compareTo(node.getKey()) > 0) {
            return ceiling(key, node.getRightChild());
        }
        Node<Key, Value> temp = ceiling(key, node.getLeftChild());
        if (temp == null) return node;
        else return temp;
    }

    private Node<Key, Value> applyRotation(Node<Key, Value> node) {
        int balance = balance(node);
        // -1, 0 , 1 are acceptable
        if (balance > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
                // .setRightChild() when rotate right, we need to point the parent node to the swapped node
            }
            return rotateLeft(node);
        }
        return node;
    }

    private int balance(Node<Key, Value> node) {
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
        Node<Key, Value> prevRightNode = node.getRightChild();
        Node<Key, Value> centerNode = prevRightNode.getLeftChild();
        prevRightNode.setLeftChild(node); //rotate left, becomes root node
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(prevRightNode);
        updateHeight(centerNode);
        return prevRightNode;
    }

    private Node<Key, Value> rotateRight(Node<Key, Value> node) {
        Node<Key, Value> prevLeftNode = node.getLeftChild();
        Node<Key, Value> centerNode = prevLeftNode.getRightChild();
        prevLeftNode.setRightChild(node); // rotate right, becomes root node
        node.setLeftChild(centerNode); // balancing
        updateHeight(node);
        updateHeight(prevLeftNode);
        updateHeight(centerNode);
        return prevLeftNode;
    }


    private void updateHeight(Node<Key, Value> node) { //height should not use getters, since method could call null nodes
        if (node != null) {
            int maxHeight = Math.max(
                    height(node.getLeftChild()),
                    height(node.getRightChild())
            );
            node.setHeight(maxHeight + 1);
        }
    }

    private int height(Node<Key, Value> node) {
        return node != null ? node.getHeight() : 0;
    }

    public boolean isEmpty() {
        return root == null;
    }


    private class Node<Key extends Comparable<Key>, Value> {
        private Key key;
        private Value val;
        private Node<Key, Value> leftChild;
        private Node<Key, Value> rightChild;
        private int height;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.height = 1;
        }

        public Key getKey() {
            return key;
        }

        public Value getVal() {
            return val;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public void setVal(Value val) {
            this.val = val;
        }

        public Node<Key, Value> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<Key, Value> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<Key, Value> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<Key, Value> rightChild) {
            this.rightChild = rightChild;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String toString() {
            return "Key: " + key + " Value: " + val;
        }

    }
}

public class Main {
    public static void main(String[] args) {
        ST<String, Integer> integerST = new BST<>();
        integerST.put("C", 3);
        integerST.put("E", 3);
        integerST.put("B", 2);
        integerST.put("F", 3);
        integerST.put("D", 3);
        integerST.put("A", 1);
        integerST.put("G", 3);
        integerST.put("H", 3);

        System.out.println(integerST.get("F"));
        integerST.delete("F");
        System.out.println(integerST.get("F"));

        System.out.println(integerST.get("B"));
        integerST.delete("B");
        System.out.println(integerST.get("B"));

        System.out.println(integerST.getMax());
        System.out.println(integerST.getMin());

        System.out.println(integerST.floor("B"));
        System.out.println(integerST.ceiling("F"));

        Iterable<String> strings = integerST.iterator();
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
