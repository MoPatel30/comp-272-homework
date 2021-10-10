/**
 * Write a description of class MaxHeap here.
 *
 * @author Mohammed Patel
 * @version HW5 10/10
 */

import java.util.*;
public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {   
    ArrayList<E> heapList;
    
    
    /** construct an empty Heap using ArrayList
    *   with root at index 0.
    *   don't use binary tree for implementing the heap.  
    *   
    *   Runtime: O(1)
    *   Space: O(1)
    */
    public MaxHeap(){
        heapList = new ArrayList<>();    
    }
    
    
    /** returns max value
    * 
    *   Runtime: O(1)
    *   Space: O(1)
    */
    public E findMax() {
        if(!heapList.isEmpty()){
            return heapList.get(0);
        }
        return null;
    }   

    
    /** adds a new value to the heap at the end of the Heap and 
    *   adjusts values up to the root to ensure Max heap property is satisfied.
    *   parent of node at i is given by the formula (i-1)/2
    *   throw appropriate exception
    *   
    *   Runtime: O(log(N))
    *   Space: O(1)
    */
    public void addHeap(E val) {
        this.heapList.add(val);
        this.siftUp(this.heapList.size()-1);
    }
    
    
    public void siftUp(int nodeIndex) { // log(N)
        E tmp = this.heapList.get(nodeIndex);
        
        while(nodeIndex > 0 && (tmp.compareTo(this.heapList.get((int)Math.floor((nodeIndex - 1)  / 2))) > 0)){
            this.heapList.set(nodeIndex, this.heapList.get((int)Math.floor((nodeIndex - 1)  / 2)));
            nodeIndex = (int)Math.floor((nodeIndex - 1)  / 2);
        }
        
        this.heapList.set(nodeIndex, tmp);
    }
    
        
    /** returns the max value at the root of the heap by swapping the last value 
    *   and percolating the value down from the root to preserve max heap property
    *   children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    *   bounds of the Heap index, namely, 0 ... size()-1.
    *   throw appropriate exception
    *   
    *   Runtime: O(log(N))
    *   Space: O(1)
    */
    public E removeHeap() {
        if(!this.heapList.isEmpty()){
            E tmp, res;
                    
            tmp = this.heapList.get(0);
            this.heapList.set(0, this.heapList.get(this.heapList.size()-1));
            this.heapList.set(this.heapList.size()-1, tmp);
            res = this.heapList.remove(this.heapList.size()-1);        
            siftDown(0);
                
            return res;
        }
        else{
            return null;
        }
    }
    
    
    public void siftDown(int nodeIndex){ // log(N)
        int leftChildIndex = nodeIndex*2 + 1;
        int rightChildIndex = nodeIndex*2 + 2;
        int heapSize = this.heapList.size();
        int maxIndex;
        E tmp;
          
        if (rightChildIndex >= heapSize) {
            if (leftChildIndex >= heapSize)
                return;
            maxIndex = leftChildIndex;
        } 
        else {
            if (this.heapList.get(leftChildIndex).compareTo(this.heapList.get(rightChildIndex)) > 0)
                maxIndex = leftChildIndex;
            else
                maxIndex = rightChildIndex;
        }
        
        if (this.heapList.get(nodeIndex).compareTo(this.heapList.get(maxIndex)) < 0) {
            tmp = this.heapList.get(maxIndex);
            this.heapList.set(maxIndex, this.heapList.get(nodeIndex));
            this.heapList.set(nodeIndex, tmp);
            siftDown(maxIndex);
        }
    }
    
 
    /** takes a list of items E and builds the heap and then prints 
    *   decreasing values of E with calls to removeHeap().
    *   
    *   Runtime: O(N*log(N)) N - length of list
    *   Space: O(N) - creating a new MaxHeap of size N - length of list
    */
    public void heapSort(List<E> list){
        if(!list.isEmpty()){
            MaxHeap<E> testHeap = new MaxHeap<>();
            testHeap.buildHeap(list);
            int length = testHeap.heapList.size();
            
            for(int i = 0; i < length; i++){ // also N*log(N) but not nested with other one
                System.out.print(testHeap.removeHeap() + ", ");
            }
        }
    }
     
    
    /** merges the other maxheap with this maxheap to produce a new maxHeap. 
    *   
    *   Runtime: O(N*log(N)) - N length of other list/heap (linear for-loop * log(N) add method)
    *   Space: O(1) 
    */
    public void heapMerge(MaxHeap<E> other){     
        if(!other.heapList.isEmpty()){
            for(int i = 0; i < other.heapList.size(); i++){
                this.addHeap(other.heapList.get(i));
            }   
        }   
    }
     
    
    /** takes a list of items E and builds the heap by calls to addHeap(..)
    *   
    *   Runtime: O(N*log(N)) (linear for-loop * log(N) add method)
    *   Space: O(1)
    */
    public void buildHeap(List<E> list) {
        if(!list.isEmpty()){
            for(int i = 0; i < list.size(); i++){
                this.addHeap(list.get(i));
            }      
        }
    }
    
    
    public static void main(String[] args){
        MaxHeap<Integer> testHeapOne = new MaxHeap<>();
        MaxHeap<Integer> testHeapTwo = new MaxHeap<>();
        MaxHeap<String> testHeapThree = new MaxHeap<>();
        MaxHeap<String> testHeapFour = new MaxHeap<>();
 
        ArrayList<Integer> listOne = new ArrayList<>();
        ArrayList<Integer> listTwo = new ArrayList<>();
        ArrayList<String> listThree = new ArrayList<>();
        ArrayList<String> listFour = new ArrayList<>();
        
        // populate lists
        listOne.add(8);
        listOne.add(10);
        listOne.add(5);
        listOne.add(9);
        listOne.add(7);
        listOne.add(2);

        listTwo.add(81);
        listTwo.add(63);
        listTwo.add(27);
        listTwo.add(9);
        listTwo.add(123);
        listTwo.add(54);
        
        listThree.add("dog");
        listThree.add("apple");
        listThree.add("hyena");
        listThree.add("algorithms");
        listThree.add("baby");
        listThree.add("zebra");
        
        listFour.add("orange");
        listFour.add("basketball");
        listFour.add("basketballs");
        listFour.add("ball");
        listFour.add("corn");
        listFour.add("monster");
        
        testHeapOne.buildHeap(listOne);                     
        for(int i = 0; i < testHeapOne.heapList.size(); i++){
            System.out.print(testHeapOne.heapList.get(i) + ", "); // 10, 9, 5, 8, 7, 2
        }
        
        System.out.println("");
        testHeapOne.heapSort(listOne); // 10, 9, 8, 7, 5, 2              
        System.out.println("");  
        
        testHeapTwo.buildHeap(listTwo);  
        for(int i = 0; i < testHeapTwo.heapList.size(); i++){
            System.out.print(testHeapTwo.heapList.get(i) + ", "); // 123, 81, 54, 9, 63, 27
        } 
        
        System.out.println("");        
        testHeapTwo.heapSort(listTwo); // 123, 81, 63, 54, 27, 9              
        System.out.println("");        
    
        testHeapThree.buildHeap(listThree);                     
        for(int i = 0; i < testHeapThree.heapList.size(); i++){
            System.out.print(testHeapThree.heapList.get(i) + ", "); // zebra, baby, hyena, algorithms, apple, dog
        }
        
        System.out.println("");        
        testHeapThree.heapSort(listThree); // zebra, hyena, dog, baby, apple, algorithms              
        System.out.println(""); 
        
        testHeapFour.buildHeap(listFour);                     
        for(int i = 0; i < testHeapFour.heapList.size(); i++){
            System.out.print(testHeapFour.heapList.get(i) + ", "); 
            // orange, corn, monster, ball, basketball, basketballs
        } 
        
        System.out.println("");        
        testHeapFour.heapSort(listFour); // orange, monster, corn, basketballs, basketball, ball             
        System.out.println(""); 
              
        testHeapOne.heapMerge(testHeapTwo);   
        for(int i = 0; i < testHeapOne.heapList.size(); i++){
            System.out.print(testHeapOne.heapList.get(i) + ", "); 
            // 123, 81, 27, 54, 63, 10, 5, 8, 9, 7, 9, 2
        }
        
        System.out.println("");        

        testHeapThree.heapMerge(testHeapFour); 
        for(int i = 0; i < testHeapThree.heapList.size(); i++){
            System.out.print(testHeapThree.heapList.get(i) + ", "); 
            // zebra, monster, orange, corn, basketball, dog, hyena, algorithms, baby, apple, ball, basketballs
        }
    }
}
