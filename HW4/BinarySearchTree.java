/**
 * Write a description of class BinarySearchTree here.
 *
 * @author Mohammed Patel
 * @version HW4 10/3
 */
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    BinaryTree<E> tree = null;
    
    // empty constructor
    public BinarySearchTree() {
        tree = new BinaryTree<>();     
    }

    // nonempty constructor
    public BinarySearchTree(E val) {
        tree = new BinaryTree<>(val);     
    }
    
    /** returns true if BST has val else false.
    * Runtime: O(log(n)) (worst-case O(N) if linear tree)
    * Space: O(1) no extra space used
    */
    public boolean contains (E val) {  
        if(tree.root == null){
            return false;
        }
        
        Node<E> node = tree.root;
        
        while(node != null){
            if(val.compareTo(node.getInfo()) < 0){  
                node = node.getLeft();           
            }
            else if (val.compareTo(node.getInfo()) > 0){
                node = node.getRight();
            }
            else{
                return true;
            }
        }
        
        return false;
    }
    
    // inserts val at the right place satisfying search tree properties, should handle if the tree is empty
    // if value is already there then donâ€™t insert it again
    /**
    * Runtime: O(log(n)) (worst-case O(N) if linear tree)
    * Space: O(1) no extra space used
    */
    public void insert(E val) {
        if(tree.root == null){
            tree.root = new Node(val);
            tree.size++;
        }
        else{
            Node<E> node = tree.root;
            Node<E> prev =null;

            while(node != null){
                prev = node;

                if(val.compareTo(node.getInfo()) < 0){
                    node = node.getLeft();
                }
                else if(val.compareTo(node.getInfo()) > 0){ 
                    node = node.getRight();                    
                }
                else{
                    return;
                }
            }

            if(val.compareTo(prev.getInfo()) > 0){
                tree.addRight(prev,val);
                tree.size++;
            }
            if(val.compareTo(prev.getInfo()) < 0){
                tree.addLeft(prev,val);
                tree.size++;
            }
        }            
    }

    /** returns the minimum value stored in the tree
    * Runtime: O(log(n)) worst-case O(N) if linear tree
    * Space: O(1) no extra space used
    */
    public E findMin() {       
        Node<E> node = tree.root;       
        
        while(node.getLeft() != null){
            node = node.getLeft();
        }   
        
        return node.getInfo();
    }

    /** returns the minimum value stored in the tree
    * Runtime: O(log(n)) worst-case O(N) if linear tree
    * Space: O(1) no extra space used
    */    
   public E findMax() {        
        Node<E> node = tree.root;
        
        while(node.getRight() != null){
            node = node.getRight();
        }        
       
        return node.getInfo();
    }
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> bt= new BinarySearchTree<>();
        
        bt.insert(5);
        bt.insert(10);
        bt.insert(3);
        bt.insert(20);
        bt.insert(8);
        bt.insert(4);
        
        System.out.print(bt.findMin());
    }            
}
