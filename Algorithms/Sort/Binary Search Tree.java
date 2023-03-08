import java.util.*;

public class Main {
    public static void main(String[] args) {
      System.out.println("Hello, World!");
  }
}

public interface ITree<T extends Comparable<T>> {
  
  ITree<T> insert(T data);
  
  void delete(T data);
  
  void traverse();
  
  T getMax();
  
  T getMin();
  
  boolean isEmpty();
}

public class BinarySearchTree<T extends Comparable<T>> implements ITree<T> {
  private Node<T> root;
  
  @Override
  public boolean isEmpty() {
    return root == null;
  }
  
  @Override
  public T getMin() {
    if (isEmpty()) return null;
    return getMin(root);
  }
  
  private T getMin(Node<T> node) {
    if (node.getLeftChild() == null) return node.getData();
    return getMin(node.getLeftChild());
  }
  
  
  /*
  @Override
  public T getMin() {
    if (isEmpty()) return null;
    return getMin(root, root.getLeftChild())
  }
  
  private T getMin(Node<T> node, Node<T> leftChild) {
    if (leftChild == null) return node.getData();
    return getMin(leftChild, leftChild.getLeftChild());
  }
  */
  
  /*
  @Override
  public T getMin() {
    if (isEmpty()) return null;
  }
  Node<T> node = root;
  while (node.getLeftChild() != null) {
    node = node.getLeftChild();
  }
  return node.getData();
  */
  @Override
  public T getMax() {
    if (isEmpty()) return null;
    return getMax(root);
  } 
  
  private T getMax(Node<T> node) {
    if (node.getRightChild() == null) return node.getData();
    return getMax(node.getRightChild());
  }
  
  @Override
  public void traverse() {
    traverse(root);
  }
  
  private void traverse(Node<T> node) {
    if (node != null ) {
      traverse(node.getLeftChild());
      System.out.println(node);
      traverse(node.getRightChild());
    }
  }
  
  /*
  @Override
  public void traverse() {
    if (isEmpty()) return null;
    return traverse(root);
  }
  
  private void traverse(Node<T> node) {
    if (node.getLeftChild() == null) {
      if (node.getRightChild == null) {
        return System.out.println(node);
      }
      else return traverse(node.getRightChild());
    }
    return traverse(node.getLeftChild());
  }
  */
  // Heavenly recursive way
  @Override
  public ITree<T> insert(T data) {
    root = insert(data, root);
    return this;
  }
  
  private Node<T> insert(T data, Node<T> node) {
    if (node == null) return new Node<>(data);
    
    // Unlinks the node for insert if there is space(null)
    if (data.compareTo(node.getData()) < 0 ) {
      node.setLeftChild(insert(data, node.getLeftChild()));
    } else if (data.compareTo(node.getData()) > 0 ) {
      node.setRightChild(insert(data, node.getRightChild()));
    }
    
    // Need to reassign the node. node.setRightChild( node.getRightChild() );
    return node;
  }
  
  /*
  @Override
  public ITree<T> insert(T data) {
    if (isEmpty()) root = new Node<>(data);
    else insert(data, root);
    return this; // can be used for chain insertion calls
  }
  
  private void insert(T data, Node<T> node) {
    if (data.compareTo(node.getData()) < 0) { // go left
      if (node.getLeftChild() == null) {
        node.setLeftChild(new Node<>(data));
      } else {
        return insert(data, node.getLeftChild());
      }
    } else if (data.compareTo(node.getData()) > 0) { // go right
      if (node.getRightChild() == null) {
        node.setRightChild(new Node<>(data));    
      } else {
        return insert(data, node.getRigthChild());
      }
    }
  }
  */
  
  @Override
  public void delete(T data) {
    root = delete(data, root);
  }
  
  private Node<T> delete(T data, Node<T> node) {
    if (node == null) return null; // Reassign null, set(.get)
    
    if (data.compareTo(node.getData()) < 0 ) {
      node.setLeftChild(delete(data, node.getLeftChild()));
    } else if (data.compareTo(node.getData()) > 0) {
      node.setRightChild(delete(data, node.getRightChild()));
    } else { // data == .getData()
      // One Child or Leaf Node #onechildpolicy OwO
      if (node.getLeftChild() == null) {
        return node.getRightChild(); // can be null
      } else if (node.getRightChild() == null) {
        return node.getLeftChild(); // can be null
      } else {
        //Two Children
        // Find the closest data of the deleted node, 
        // the biggest smaller child(leftChild) or the smallest bigger child(rightChild)
        node.setData(getMax(node.getLeftChild()));
        // Since now there is 2 of the same data, we need to delete it.
        // This second delete is guaranteed to have only 1 or no child.
        node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        /*
        node.setData(getMin(node.getRightChild()));
        node.setRightChild(delete(node.getData(), node.getRightChild()));
        */
      }
    }
    return node; // Need to reassign the node. node.setRightChild( node.getRightChild() );
  }
}

public class Node<T extends Comparable<T>> {
  
  private T data;
  private Node<T> leftChild;
  private Node<T> rightChild;
  
  public Node(T data) {
    this.data = data;
  }
  
  public T getData(){
    return data;
  }
  public void setData(T data) {
    this.data = data;
  }
  
  public Node<T> getLeftChild() {
    return leftChild;
  }
  
  public Node<T> getRightChild() {
    return rightChild;
  }
  
  public void setLeftChild(Node<T> leftChild) {
    this.leftChild = leftChild;
  }
  
  public void setRightChild(Node<T> rightChild ) {
    this.rightChild = rightChild;
  }
  
  public String toString() {
    String strings = "Data: " + data;
    return strings;
  }
}
