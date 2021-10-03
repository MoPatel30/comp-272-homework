import java.util.*;

/**
 * Write a description of class ExtLinkedList here.
 *
 * @author Mohammed Patel
 * @version Homework #2 9/18
 */
public class ExtLinkedList<E> extends LinkedList<E>{

    public ExtLinkedList(){
    }

    /**
     * Question #1
     *
     * @param None
     * @return res - Linked List containing the second half of current list
     * Runtime: O(N^2) N - length of list, get() is O(N)
     * Space: O(N) N - length of list 
     */
    public ExtLinkedList<E> secondHalfList(){
        ExtLinkedList<E> res = new ExtLinkedList<>();
        
        // if the list is empty, return res (which is currently empty)
        if(this.size() == 0){
            return res;
        }
        
        int split = this.size() / 2;
        
        if(this.size() % 2 != 0){
            split += 1;
        }
        
        for(int i = split; i < this.size(); i++){
            res.add(this.get(i));
        }
        
        return res;
    }
    
    
     /**
     * Question #2.1
     *
     * @param None
     * @return res Linked List containing all ODD index values of current list
     * Runtime: O(N^2) N - length of list, get() is O(N)
     * Space: O(N) N - length of list
     */
    public ExtLinkedList<E> oddList(){
        ExtLinkedList<E> res = new ExtLinkedList<>();
        
        for(int i = 0; i < this.size(); i++){
            if(i % 2 != 0){
                res.add(this.get(i));
            }
        }
        
        return res;
    }
    
     /**
     * Question #2.2
     *
     * @param None
     * @return res Linked List containing all EVEN index values of current list
     * Runtime: O(N^2) N - length of list, get() is O(N)
     * Space: O(N) N - length of list
     */
    public ExtLinkedList<E> evenList(){
        ExtLinkedList<E> res = new ExtLinkedList<>();
        
        for(int i = 0; i < this.size(); i++){
            if(i % 2 == 0){
                res.add(this.get(i));
            }
        }
        
        return res;
    }
    
    
    public static void main(String[] args){        
        ExtLinkedList<Integer> list1 = new ExtLinkedList<>();
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        
        ExtLinkedList<Integer> list2 = new ExtLinkedList<>();
        list2.add(5);
        list2.add(4);
        list2.add(3);
        list2.add(2);
        list2.add(1);
        list2.add(0);    
        
        System.out.println("Question #1");
        System.out.println("List #1: " + list1.secondHalfList());
        System.out.println("List #2: " + list2.secondHalfList());
        System.out.println("");
                
        System.out.println("Question #2");
        System.out.println("List #1 Even: " + list1.evenList());
        System.out.println("List #1 Odd: " + list1.oddList());
        System.out.println("List #2 Even: " + list2.evenList());
        System.out.println("List #2 Odd: " + list2.oddList());
        System.out.println("");
    }   
}
