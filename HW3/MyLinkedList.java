/**
 * Write a description of class MyLinkedList here.
 *
 * @author Mohammed Patel
 * @version HW3 9/27
 */

import java.util.*;
public class MyLinkedList<E>{
    Node<E> first;
    Node<E> last;
    int size;
    
    public MyLinkedList(){
       first = null;
       last = null;
       size=0;
    }
    
    public boolean isEmpty() {   
        return (size==0);      
    }
    
    public void addFirst(E info) {    
        Node<E> n =new Node<>();
        n.setInfo(info);
        
       if (isEmpty())  
           last=n;
           
    
       else {
           n.setNext(first);
           first.setPrev(n);    
       }
       first=n;
       size++;   
    }
    
    public E removeFirst() {       
        if (!isEmpty()) {       
            E val = first.getInfo();
                if (size>1) {
               
                first.getNext().setPrev(null);
                first=first.getNext();
                size--;             
            }
            else if (size==1) {              
                 first=null;
                 last=null;
                 size--;       
            }
            return val;  
        }
        else 
            throw new NoSuchElementException();    
    }
    
    public E removeLast() {
        
        if (!isEmpty()) {
            E val = last.getInfo();
            if (size>1) {
            last.getPrev().setNext(null);
            last=last.getPrev();
            size--;
       }
       else if (size==1) {        
         first=null;
         last=null;
         size--;
       }
       return val;
       }
       else   
        throw new NoSuchElementException();
}   

    //incomplete code below
    //handle empty list situation    
    public E remove(int k) {
        
        if (!isEmpty()) {
            Node<E> temp = first;
        
            if ((k>=0) && (k<size)) {
                if (k==0) return removeFirst();
                else if (k==size-1) return removeLast();
                else {
                   // get to k
                   // int i=0;
                    for (int i=0;i<k;i++){
                        temp = temp.getNext();
                    }
                    E val=temp.getInfo();
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    size--;
                    return val;
                }
            }
            else throw new IndexOutOfBoundsException();
        }
        else {
            System.out.println("list empty ..");
            throw new NoSuchElementException();
        }
    }
    
    // adds an item to the end of the list with info field set to val
    public void addLast(E val) {
        Node<E> newVal = new Node<>();   
        newVal.setInfo(val);
        newVal.setPrev(last);
        
        if(last != null){   
            last.setNext(newVal);
            last = newVal;
        }
        else{
            last = newVal;
        }
        
        size++;
    }
    
    // prints all items in the list from first to last taking care of situations when the list is empty
    // use exception handling
    public void printListForward() {
        Node<E> temp = first;
        
        if(!isEmpty()){
            for(int i = 0; i < size; i++){
                System.out.print(temp.getInfo() + ", ");
                temp = temp.getNext();
            }
        }
        else{
            System.out.println("list empty ..");
            throw new NoSuchElementException();
        }      
    }
    
    // prints all items in the list from last to first taking care of situations when the list is em
    // use exception handling
    public void printListBackward() {
        Node<E> temp = last;
        
        if(!isEmpty()){
            for(int i = 0; i < size; i++){
                System.out.print(temp.getInfo() + ", ");
                temp = temp.getPrev();
            }
        }
        else{
            System.out.println("list empty ..");
            throw new NoSuchElementException();
        }         
    }
    
    //returns true if and only if the list contains at least one element e such that 
    //Objects.equals(o,e)
    //return false if the list is empty
    public boolean contains(Object o) {
        if(!isEmpty()){
            Node<E> temp = first;
            
            for(int i = 0; i < size; i++){
                if(Objects.equals(temp.getInfo(), o)){
                    return true;
                }
                temp = temp.getNext();
            }
        }
        else{
            System.out.println("list empty ..");
            throw new NoSuchElementException(); 
        }
        return false;
    }
    
    // brings the current list back to an empty list
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
    // returns the info value stored at node i 
    // throw IndexOutOfBounds exception of i is out of bounds or the list is empty
    public E get(int i) {
        if(i < 0 || i >= size){
            System.out.println("index out of bounds ..");
            throw new IndexOutOfBoundsException();
        }
        else if(!isEmpty()){
            Node<E> temp = first;
            for(int j = 0; j < size; j++){
                if(i == j){
                    return temp.getInfo();
                }
                temp = temp.getNext();
            }            
        }
        else{
            System.out.println("list empty ..");
            throw new IndexOutOfBoundsException(); 
        }
        return null;
    }
    
    // compares this MyLinkedList with the parameter otherList 
    // and returns true if the linkedlists have identical values from beginning to end
    // same size and this.get(i).equals(otherList.get(i)) should be true for all i
    // lists can be empty in which case return true
    //should run in O(n) time where n is the size of each list.
    public boolean equals(Object otherList) {  
        if(!(otherList instanceof MyLinkedList)){
            return false;
        } 
        if(this == otherList){
            return true;
        }
        
        MyLinkedList<E> other = (MyLinkedList)otherList;
        Node<E> listOne = first;
        Node<E> listTwo = other.first;
        
        while(listOne != null && listTwo != null){
             if(listOne.getInfo() != listTwo.getInfo()){
                 return false;          
             }
             listOne = listOne.getNext();
             listTwo = listTwo.getNext();      
        }
        
        return listOne == null && listTwo == null; // check if both are bull, or same size.
    }
    
    public static void main(String[] args){       
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        MyLinkedList<Integer> list3 = new MyLinkedList<>();
        MyLinkedList<Integer> list4 = new MyLinkedList<>(); //empty
        
        // System.out.println(list1.remove(2)); // error
        // System.out.println(list2.remove(5)); // error
        
        list1.addFirst(1);
        list1.addLast(2);
        list1.addLast(3);

        list2.addFirst(1);
        list2.addLast(2);
        list2.addLast(3);
        
        list3.addFirst(1);
        list3.addLast(2);
        list3.addLast(4);
        
        list1.printListForward(); // 1, 2, 3
        list1.printListBackward(); // 3, 2, 1
        
        list3.printListForward(); // 1, 2, 4
        list3.printListBackward(); // 4, 2, 1
  
        // list4.printListForward(); // Error
        // list4.printListBackward(); // Error
        
        System.out.println(list1.equals(list2)); // true
        System.out.println(list1.equals(list3)); // false
        System.out.println(list1.equals(list4)); // false
        
        System.out.println(list1.contains(2)); // true
        System.out.println(list1.contains(8)); // false
        // System.out.println(list4.contains(2)); // error (empty list)

        list2.clear();
        // System.out.println(list2.get(2)); // error (empty list)
        System.out.println(list1.get(1)); // 2
        // System.out.println(list4.get(3)); // error (out of bounds)
    }
}
    
    