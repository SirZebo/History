import java.util.*;

public class Main {
    public static void main(String[] args) {
      List<Integer> integers = new LinkedList<>();
      integers.insert(10);
      integers.insert(20);
      integers.insert(30);
      integers.insert(40);
      
      integers.traverse();
      
      integers.remove(20);
      
      integers.traverse();
      System.out.println("running");
  }
}

public interface List<T> {
  
  void insert(T data);
  
  void remove(T data);
  
  void traverse();
  
  boolean isEmpty();
}



public class LinkedList<T extends Comparable<T>> implements List<T> {
  
  private Node<T> root;
  private int size;
  
  public void insert(T data) {
    Node<T> node = new Node<>(data);
    node.setNextNode(root);
    root = node;
    size++;
  }
  
  public void remove(T data) {
    if (root.getNextNode().equals(data)) {
      root = root.getNextNode();
    } else {
      Node<T> previousNode = root;
      Node<T> currentNode = root.getNextNode();
      while (currentNode != null) {
        if (currentNode.getData().equals(data) ){
          previousNode.setNextNode(currentNode.getNextNode() ); 
          break;
        }
        previousNode = currentNode;
        currentNode = currentNode.getNextNode();
      }
    }
    size--;
  }
//Recursion


  public void traverse(){
    if (isEmpty()) {
      System.out.println("List is empty!");
    }
    traverse(root);
  }
  
  public void traverse(Node<T> node) {
    if (node == null) {
      return;
    }
    System.out.println(node);
    traverse(node.getNextNode());
  }

/*
  public void traverse(){
    if (isEmpty()) {
      System.out.println("List is empty!");
    }
    Node<T> currentNode = root;
    while (currentNode != null) {
      System.out.println(currentNode);
      currentNode = currentNode.getNextNode();
    }
  }
*/

  public boolean isEmpty() {
    return size == 0;
  }
  
  public class Node<T> {
    
    private final T data;
    private Node<T> nextNode;
    
    public Node(T data, Node<T> nextNode) {
      this.data = data;
      this.nextNode = nextNode;
    }
    
    public Node(T data) {
      this.data = data;
      this.nextNode = null;
    }
    
    public Node getNextNode() {
      return nextNode;
    }
    
    public void setNextNode(Node<T> nextNode) {
      this.nextNode = nextNode;
    }
    
    public T getData() {
      return data;
    }
    
    @Override
    public String toString() {
      String strings = "" + data;
      return strings;
    }
    
  }
}
