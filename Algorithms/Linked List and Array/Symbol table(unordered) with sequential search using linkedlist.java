import java.util.*;

public class Main {
    public static void main(String[] args) {
      ST<String, Integer> table = new ST<>();
      
      table.add("A", 1);
      table.add("B", 2);
      table.add("C", 3);
      
      table.traverse();
      System.out.println(table.getValue("A"));
      
      
  }
}

public class ST<Key extends Comparable<Key>, Value> {
  
  private Node<Key, Value> root;
  private int size;
  
  public void add(Key key, Value val) {
    if (!isEmpty()) {
      Node<Key, Value> previousNode = root;
      Node<Key, Value> currentNode = root.getNextNode();
      while (currentNode != null) {
        if (currentNode.getKey().equals(key) ) {
          currentNode.setValue(val);
        }
        previousNode = currentNode;
        currentNode = currentNode.getNextNode();
      }
    }
    Node<Key, Value> node = new Node<>(key, val);
    node.setNextNode(root);
    root = node;
    size++;
  }
  
  public boolean contains(Key key) {
    if (isEmpty()) return false;
    Node<Key, Value> currentNode = root;
    while (currentNode != null ) {
      if (currentNode.getKey().equals(key)) return true;
      currentNode = currentNode.getNextNode();
    }
    return false;
  }
  public Value getValue(Key key) {
    if (isEmpty()) return null;
    Node<Key, Value> currentNode = root;
    while (currentNode != null ) {
      if (currentNode.getKey().equals(key)) return currentNode.getValue();
      currentNode = currentNode.getNextNode();
    }
    return null;
  }
  
  public void traverse(){
    if (isEmpty()) {
      System.out.println("List is empty!");
    }
    traverse(root);
  }
  
  public void traverse(Node<Key, Value> node) {
    if (node == null) {
      return;
    }
    System.out.println(node);
    traverse(node.getNextNode());
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public void delete(Key key) {
    if (root.getNextNode().equals(key)) {
      root = root.getNextNode();
    } else {
      Node<Key, Value> previousNode = root;
      Node<Key, Value> currentNode = root.getNextNode();
      while (currentNode != null) {
        if (currentNode.getKey().equals(key) ){
          previousNode.setNextNode(currentNode.getNextNode() ); 
          break;
        }
        previousNode = currentNode;
        currentNode = currentNode.getNextNode();
      }
    }
    size--;
  }
  
  public int size() {
    return size;
  }
  
  public class Node<Key, Value> {
    private final Key key;
    private Value val;
    private Node<Key, Value> nextNode;
    
    public Node(Key key, Value val) {
      this.key = key;
      this.val = val;
      this.nextNode = null;
    } 
    
    public Key getKey() {
      return key;
    }
    
    public Value getValue() {
      return val;
    }
    
    public void setValue(Value val) {
      this.val = val;
    }
    
    public Node<Key, Value> getNextNode() {
      return nextNode;
    }
    
    public void setNextNode(Node<Key, Value> nextNode) {
      this.nextNode = nextNode;
    }
    
    public String toString() {
      String strings = "Key: " + key + " Value: " + val;
      return strings;
    }
    
    public boolean equals(Object y) {
      if (y == this) return true;
      if (y == null) return false;
      if (y.getClass() != this.getClass()) return false;
      
      Node<Key, Value> that = (Node<Key, Value>) y;
      if (this.key != that.key) return false;
      if (this.val != that.val) return false;
      return true;
    }
  }
}
