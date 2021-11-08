/**
 * Write a description of class Selection here.
 *
 * @author Mohammed Patel
 * @version HW7 11/7
 */
import java.util.*;
import java.util.Random;

public class Selection <E extends Comparable<E>> {
    int k;
    ArrayList<E> input = new ArrayList<>();
    
    // constructor
    public Selection(int num, ArrayList<E> values){
        k = num;
        input = values;
    }
 
    // quicksort algorithm -- O(N*logN) runtime
    public void quickSort(ArrayList<E> arr, int start, int end) {
        if (arr == null || arr.size() == 0) {
            return;
        }

        if (start >= end) {
            return;
        }

        int middle = start + (end - start) / 2;
        E pivot = arr.get(middle);
 
        int i = start, j = end;
        while (i <= j) {
            while (arr.get(i).compareTo(pivot) == -1) {
                i++;
            }
            while (arr.get(j).compareTo(pivot) == 1) {
                j--;
            }

            if (i <= j) {
                E temp = arr.get(i);
                arr.set(i,arr.get(j));
                arr.set(j,temp);
                i++;
                j--;
            }
        }
        if (start < j){
            quickSort(arr,start, j);
        }
        if (end > i){
            quickSort(arr,i, end);
        }
    }
    
    
    /** 
    * algorithm 1B
    */
    public E algorithmOne(){    
        System.out.print("Pre Time (ms)");
        System.out.println(System.currentTimeMillis());   
        
        ArrayList<E> kElements = new ArrayList<>();
        
        for(int i = 0; i < input.size(); i++){
            if(kElements.size() != k){
                kElements.add(input.get(i));
            }
            else{                    
                if(input.get(i).compareTo(kElements.get(0)) > 0){
                    kElements.remove(0);
                    kElements.add(input.get(i));
                }
            }
            quickSort(kElements, 0, kElements.size()-1);
        }
        
        System.out.print("Post Time (ms)");
        System.out.println(System.currentTimeMillis());
        
        return kElements.get(0);      
    }
    
    
    /** 
    * algorithm 6A 
    */
    public E algorithmTwo(){     
        System.out.print("Pre Time (ms)");
        System.out.println(System.currentTimeMillis());
        
        MaxHeap<E> heap = new MaxHeap<>();
        int count = k;
        E res = null;
        
        for(int i = 0; i < input.size(); i++){
            heap.addHeap(input.get(i));            
        }
        
        while(count != 0){
            res = heap.removeHeap();
            count--;
        }
        
        System.out.print("Post Time (ms)");
        System.out.println(System.currentTimeMillis());
        
        return res;    
    }
    
    
    /** 
    * algorithm 6B 
    */
    public E algorithmThree(){
        System.out.print("Pre Time (ms)");
        System.out.println(System.currentTimeMillis());
        
        Queue<E> kLargestElements = new PriorityQueue<E>();
                
        for(int i = 0; i < input.size(); i++){
            if(kLargestElements.size() < k){
                kLargestElements.add(input.get(i));                 
            }
            else{ 
                if(input.get(i).compareTo(kLargestElements.peek()) > 0){
                    kLargestElements.poll();
                    kLargestElements.add(input.get(i));
                }
            }
        }
        
        System.out.print("Post Time (ms)");
        System.out.println(System.currentTimeMillis());
        
        return kLargestElements.peek();      
    }
        
    
    public static void main(String[] args){       
        ArrayList<Integer> listOne = new ArrayList<>();

        for(int i = 0; i < 100000; i++){
            listOne.add(1 + (int)(Math.random() * 100000));
        }

        Selection<Integer> testOne = new Selection<>(10000, listOne);
        
        System.out.println(testOne.algorithmOne());  
        System.out.println(testOne.algorithmTwo());   
        System.out.println(testOne.algorithmThree());        
    }
}
