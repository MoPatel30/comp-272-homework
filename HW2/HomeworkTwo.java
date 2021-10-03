import java.util.*;

/**
 * Write a description of class HomeworkTwo here.
 *
 * @author Mohammed Patel
 * @version Homework #2 9/18
 */
public class HomeworkTwo{
    
    public HomeworkTwo(){
    }

    /**
     * Question #3
     *
     * @param   p - string, k - position, c - new character
     * @return  return a string with the kth character replaced by c. 
     * Runtime: O(N^2) N - length of string p, O(N) time to add two strings as well
     * Space: O(N) N - length of string
     */
    public String replaceChar(String p, int k, char c){
        String res = "";
        
        try{  
            res = p.replace(p.charAt(k), c);
        }
        catch(Exception e){
            System.out.println("k value is out of bounds " + e);
        }
        
        return res;
    }
    
    
    /**
     * Question #4
     *
     * i) O(N*sqrt(N)) - inner loop j counter is squared
     * ii.i) O(N^3) - 2N^3 is dominating term, drop the coeficient.
     * ii.ii) O(sqrt(N)) - N^1/2 is the dominating term.
     */
    
    
    /**
     * Question #5.1
     *
     * @param  arr - 2d int array
     * @return   print sum for each row 
     * Runtime: O(N^2) N - length of array (assuming NxN array) (MXN otherwise) 
     * Space: O(1) - no extra DS/memory used
     */
    public void rowSums(int[][] arr){
        int sum;
        for(int i = 0; i < arr.length; i++){
            sum = 0;
            for(int j = 0; j < arr[0].length; j++){
                sum += arr[i][j];
            }
            System.out.print(sum + ", ");
        }
    }
    
    /**
     * Question #5.2
     *
     * @param  arr - 2d int array
     * @return   print min for each column
     * Runtime: O(N^2) N - length of array (assuming NxN array) (MXN otherwise)
     * Space: O(1) - no extra DS/memory used
     */
    public void columnMins(int[][] arr){
        int min;
        for(int i = 0; i < arr.length; i++){
            min = arr[0][i];
            for(int j = 0; j < arr[0].length; j++){
                min = Math.min(min, arr[j][i]);
            }
            System.out.print(min + ", ");
        } 
    }
    
    
    /**
     * Question #6
     *
     * @param  nums - Linked List of Integers
     * @return  print running sum of list
     * Runtime: O(N) N - length of list
     * Space: Linear if Iterator requires memory/space, otherwise constant
     */
    public void prefixSums(LinkedList<Integer> nums){
        ListIterator<Integer> numsIterator= nums.listIterator();
        int count = 0;
        while(numsIterator.hasNext()){
            count += numsIterator.next();
            System.out.print(count + ", ");
        }
    }
    
    
    /**
     * Question #7
     *
     * @param nums - Linked List of Integers
     * @return  print running sum of the list in reverse
     * Runtime: O(N) N - length of list
     * Space: Linear if Iterator requires memory/space, otherwise constant
     */
    public void reversePrefixSums(LinkedList<Integer> nums){
        ListIterator<Integer> numsIterator= nums.listIterator(nums.size());
        int count = 0;
        while(numsIterator.hasPrevious()){
            count += numsIterator.previous();
            System.out.print(count + ", ");
        }
    }

    
    /**
     * Question #8
     *
     * @param  list1, list2 - Linked List of Strings
     * @return res - Linked List of Strings merged from param in alphabetical order
     * Runtime: O(N^2) N length of larger list (LL get() is linear)
     * Space: O(N+M) N - length of list1, M - length of list2
     */
    public LinkedList<String> mergeSortedLists(LinkedList<String> list1, LinkedList<String> list2){
        LinkedList<String> res = new LinkedList<String>();
        int i = 0;
        int j = 0;
        
        while(i < list1.size() || j < list2.size()){
            if(i >= list1.size()){
                res.add(list2.get(j));
                j++;
            }
            else if(j >= list2.size()){
                res.add(list1.get(i));
                i++;
            }
            else{
                if(list1.get(i).compareTo(list2.get(j)) < 0){
                    res.add(list1.get(i));
                    i++;
                }
                else if(list1.get(i).compareTo(list2.get(j)) > 0){
                    res.add(list2.get(j));
                    j++;
                }
                else{
                    res.add(list1.get(i));
                    res.add(list2.get(j));
                    i++;
                    j++;
                } 
            }
        }     
    
        return res;
    }
   
    
    /**
     * Question #9
     *
     * @param arr - array of ints, k - difference number
     * @return  uniquePairs - unique pairs in arr that differ by k
     * Runtime: O(N^2) N - length of arr
     * Space: O(M) M - number of unique pairs found
     */
    public ArrayList<ArrayList<Integer>> uniquePairs(int[] arr, int k){
        HashSet<int[]> uniquePairs = new HashSet<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(Math.abs(arr[i] - arr[j]) == k){
                    int[] temp1 = {arr[i], arr[j]};
                    int[] temp2 = {arr[j], arr[i]};
                    if(uniquePairs.contains(temp1) || uniquePairs.contains(temp2)){
                        continue;
                    }
                    else{
                        ArrayList<Integer> nums = new ArrayList<>();
                        nums.add(temp1[0]);
                        nums.add(temp1[1]);
                        res.add(nums);
                        uniquePairs.add(temp1);
                        uniquePairs.add(temp2);
                    }
                }
            }
        }
        return res;
    }
    
    
    public static void main(String[] args){
        HomeworkTwo test = new HomeworkTwo();
        
        System.out.println("Question #3");
        System.out.println(test.replaceChar("hello", 3, 'd'));
        System.out.println(test.replaceChar("algorithms", 2, 'v'));
        System.out.println(test.replaceChar("class", 0, 'g'));
        System.out.println(test.replaceChar("class", 10, 'g')); //raises exception error
        System.out.println("");
        
        int[][] arr = new int[3][3];
        int[] arr1 = {3, 2, 5};
        int[] arr2 = {1, 0, 4};
        int[] arr3 = {5, 6, 7};
        arr[0] = arr1;
        arr[1] = arr2;
        arr[2] = arr3; 

        System.out.println("Question #5");
        System.out.print("RowSums: ");
        test.rowSums(arr);
        System.out.println("");
        System.out.print("ColumnMins: ");
        test.columnMins(arr);
        System.out.println("");
        System.out.println("");

        LinkedList<Integer> nums = new LinkedList<>();
        nums.add(5);
        nums.add(3);
        nums.add(2);
        nums.add(9);
        nums.add(3);
        nums.add(15);
        nums.add(22);

        System.out.println("Question #6");
        System.out.print("PrefixSum: ");
        test.prefixSums(nums);
        System.out.println("");
        System.out.println("");

        System.out.println("Question #7");
        System.out.print("ReversePrefixSum: ");
        test.reversePrefixSums(nums);
        System.out.println("");
        System.out.println("");

        LinkedList<String> wordListOne = new LinkedList<>();
        wordListOne.add("apple");
        wordListOne.add("cat");
        wordListOne.add("dog");
        wordListOne.add("handle");
        wordListOne.add("zebra");
        
        LinkedList<String> wordListTwo = new LinkedList<>();
        wordListTwo.add("ball");
        wordListTwo.add("cat");
        wordListTwo.add("doggy");
        wordListTwo.add("unique");
        
        System.out.println("Question #8");
        System.out.println("Merge Sorted Lists: " + test.mergeSortedLists(wordListOne, wordListTwo));
        System.out.println("");

        int[] arrTestOne = {1, 4, 9, 12, 6, 15, 5, 13, 17};
        int[] arrTestTwo = {0, 5, 11, 29, 23, 17, 10};
        System.out.println("Question #9");
        System.out.println("Unique Pairs Array #1 Test #1 (k=3): " + test.uniquePairs(arrTestOne, 3)); 
        System.out.println("Unique Pairs Array #1 Test #2 (k=4): " + test.uniquePairs(arrTestOne, 4)); 
        System.out.println("Unique Pairs Array #2 Test #1 (k=5): " + test.uniquePairs(arrTestTwo, 5)); 
        System.out.println("Unique Pairs Array #2 Test #2 (k=6): " + test.uniquePairs(arrTestTwo, 6)); 
        System.out.println("");
    }
}
