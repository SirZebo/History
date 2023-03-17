import java.util.*;

public class Main {
    public static void main(String[] args) {
      System.out.println("Hello, World!");
  }
}

public interface Tree <Key extends Comparable<Key>, Value> {
  
  Tree<Key, Value> insert(Key key, Value value);
  
  void delete(Key key);
  
  void traverse();
  
  Key getMax();
  
  Key getMin();
  
  boolean isEmpty();
}

public class RBT <Key extends Comparable<Key>, Value> implements Tree<Key, Value> {
  
  private Node<Key, Value> root;
  
  @Override 
  public Tree<Key, Value> insert(Key key, Value value) {
    Node<Key, Value> nodeToInsert = new Node<>(key, value);
    insert(nodeToInsert, root);
    recolorAndRotate(nodeToInsert); // start recoloring from the bottom where it is inserted to the root
    return this;
  }
  
  private Node<Key, Value> insert(Node<Key, Value> nodeToInsert, Node<Key, Value> node) {
    if (node == null) return nodeToInsert;
    if (nodeToInsert.getKey().compareTo(node.getKey()) < 0) {
      node.setLeftChild(insert(nodeToInsert, node.getLeftChild()));
      node.getLeftChild().setParent(node);
    }
    else if (nodeToInsert.getKey().compareTo(node.getKey()) > 0 ) {
      node.setRightChild(insert(nodeToInsert, node.getRightChild()));
      node.getRightChild().setParent(node);
    }
    return node;
  }
  
  @Override
  public void delete(Key key) {
    root = delete(key, root);
  }
  
  private Node<Key, Value> delete(Key key, Node<Key, Value> node) {
    if (node == null) return null;
    if (key.compareTo(node.getKey()) < 0 ) {
      node.setLeftChild(delete(key, node.getLeftChild()));
    }
    else if (key.compareTo(node.getKey()) > 0) {
      node.setRightChild(delete(key, node.getRightChild()));
    }
    else { // ==
      if (node.getRightChild() == null) {
        return node.getLeftChild();
      }
      else if (node.getLeftChild() == null) {
        return node.getRightChild();
      }
      else {
        node.setKey(getMax(node.getLeftChild()));
        node.setLeftChild(delete(node.getKey(), node.getLeftChild()));
      }
    }
    
    return node;
  }
  
  private void recolorAndRotate(Node<Key, Value> node) {
    Node<Key, Value> parent = node.getParent();
    // if parent is black, it is correct. Else 
    if (node != root && parent.getColor() == Color.RED) {
      // Cases
      //  1. Uncle is also RED. We color both parent and sibling black then grandParent RED;
      //  2. Uncle is BLACK while parent is RED;
      //    - Need to check if parent is in the middle (Left-right or Right-left)
      Node<Key, Value> grandParent = node.getParent().getParent();
      Node<Key, Value> uncle = !parent.isLeftChild() ? 
                            grandParent.getLeftChild() : grandParent.getRightChild();
      if (uncle != null && uncle.getColor() == Color.RED) {
        // color both parent and sibling black then grandParent RED
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(grandParent); // parent and grandParent are correct, check for grandParent's parent
      }
      else if (parent.isLeftChild()) {
        if (!node.isLeftChild()) { // Left-right
          rotateLeft(parent); // Make it to Left heavy
          rotateRight(grandParent);
          grandParent.flipColor();
          node.flipColor();
        } else {
          parent.flipColor(); // since node and nodeParent is RED, correcting parent to black
          grandParent.flipColor(); // Thus grandParent has to be the opposite of parent. 
          // Hindsight: grandParent will be child of parent, but it will still be opposite color of parent regardless
          rotateRight(grandParent);
          recolorAndRotate(parent);
        }
      }
      else if (!parent.isLeftChild()) { // parent.isRightChild()
        if (node.isLeftChild()) { // Right-left
          rotateRight(parent);
          rotateLeft(grandParent);
          grandParent.flipColor();
          node.flipColor();
        } else {
        parent.flipColor(); // Since node and parent is RED, need to correct parent color to BLACK
          grandParent.flipColor(); // Thus grandParent color need to be corrected to be opposite of parent.
          // Hindsight grandParent will be child of parent, but it will still be the opposit color
          rotateLeft(grandParent);
          recolorAndRotate(parent);
        }
      }
      root.setColor(Color.BLACK);
    }
  }
  
  private void rotateLeft(Node<Key, Value> node) {
    Node<Key, Value> newParent = node.getRightChild();
    node.setRightChild(newParent.getLeftChild()) ; // transfer the middle node, node.getRightChild().getLeftChild(); 
    if (node.getRightChild() != null) node.getRightChild().setParent(node);
    newParent.setParent(node.getParent());
    // node.getParent() is grandParent
    if (node.getParent() == null) root = newParent; // if node is root;
    else if (node.getParent().isLeftChild()) node.getParent().setLeftChild(newParent);
    else node.getParent().setRightChild(newParent);
    node.setParent(newParent);
    newParent.setLeftChild(node);
  }
  
  private void rotateRight(Node<Key, Value> node) {
    Node<Key, Value> newParent = node.getLeftChild();
    node.setLeftChild(newParent.getRightChild()); // transfer the middle node, node.getleftChild().getRightChild()
    if (node.getLeftChild() != null) node.getLeftChild().setParent(node);
    newParent.setRightChild(node);
    newParent.setParent(node.getParent());
    // node.getParent() is grandParent;
    if (node.getParent() == null) root = newParent; // node was the root
    else if (node.getParent().isLeftChild()) node.getParent().setLeftChild(newParent);
    else node.getParent().setRightChild(newParent);
    node.setParent(newParent);
  }
  
  @Override 
  public void traverse() {
    traverse(root);
  }
  
  private void traverse(Node<Key, Value> node) {
    if (node == null) return;
    traverse(node.getLeftChild());
    System.out.println(node);
    traverse(node.getRightChild());
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
  public Key getMax() {
    if (isEmpty()) return null;
    return getMax(root);
  }
  
  private Key getMax(Node<Key, Value> node) {
    if (node.getRightChild() == null) return node.getKey();
    return getMax(node.getRightChild());
  }
  
  @Override
  public boolean isEmpty() {
    return root == null;
  }
  
  private class Node<Key extends Comparable<Key>, Value> {
    
    private Key key;
    private Value value;
    private Node<Key, Value> leftChild;
    private Node<Key, Value> rightChild;
    private Node<Key, Value> parent;
    private Color color = Color.RED;
    
    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
    
    public Key getKey() {
      return key;
    }
    
    public void setKey(Key key) {
      this.key = key;
    }
    
    public Value getValue() {
      return value;
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
    
    public Node<Key, Value> getParent() {
      return parent;
    }
    
    public void setParent(Node<Key, Value> parent) {
      this.parent = parent;
    }
    
    public void setColor(Color color) {
      this.color = color;
    }
    
    public Color getColor() {
      return color;
    }
    
    public String toString() {
      String strings = "Key: " + key + " Value: " + value + " Color: " + color;
      return strings;
    }
    
    public boolean isLeftChild() {
      return this == parent.getLeftChild();
    }
    
    public void flipColor() {
      setColor(color == Color.RED ? Color.BLACK : Color.RED);
    }
  }
  
  enum Color {
    RED,
    BLACK;
  }
  
}  
