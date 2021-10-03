/**
 * Write a description of class MyBigInteger here.
 *
 * @author Mohammed Patel
 * @version HW3 9/27
 */

import java.util.*;
public class MyBigInteger{

    MyLinkedList<Integer> bigI;
    
    public MyBigInteger () {   
        bigI =new MyLinkedList<>();
    }
    
    // takes a numerically valued String p and stores it one digit at a time in the linked list
    // example, MyBigInteger("383023322") will store the integer 383023322 in the linked list 
    // one digit per node. 
    public MyBigInteger(String p) {
        bigI = new MyLinkedList<>();
        boolean hasZeros = true;

        for(int i = 0; i < p.length(); i++){  

            if(!Character.isDigit(p.charAt(i))){
                throw new ClassCastException();
            }
            else if(String.valueOf(p.charAt(i)).equals("0") && hasZeros){
                continue;
            }
            else if(!hasZeros){
                bigI.addLast(Integer.parseInt(String.valueOf(p.charAt(i))));
            }
            else{
                hasZeros = false;
                bigI.addFirst(Integer.parseInt(String.valueOf(p.charAt(i))));
            }
        }   
    }
    
    //add(..) adds this MyBigInteger to other MyBigInteger and returns the sum as a MyBigInteger
    // the original Big Integers don't change.
    public MyBigInteger add(MyBigInteger other) {
        int sum = 0;
        int firstCount = bigI.size;
        int secondCount = other.bigI.size;
        Node<Integer> firstList = bigI.first;
        Node<Integer> secondList = other.bigI.first;
 
        while(firstCount != 0){
            sum += firstList.getInfo() * Math.pow(10, firstCount - 1);
            firstList = firstList.getNext();
            firstCount -= 1;
        }
        
        while(secondCount != 0){
            sum += secondList.getInfo() * Math.pow(10, secondCount -1);
            secondList = secondList.getNext();
            secondCount -= 1;
        }
        
        MyBigInteger res = new MyBigInteger(String.valueOf(sum));
        return res;
    }
    
    // returns true if and only if the two big integers are equal
    public boolean equals(MyBigInteger other) {
        return bigI.equals(other.bigI);
    }
    
    // returns true if and only if this MyBigInteger is less than other MyBigInteger
    // implemented assuming no negative numbers. Would just need to add extra checks to handle negative value cases
    public boolean lessThan(MyBigInteger other) {
        if(bigI.isEmpty() || other.bigI.isEmpty()){ // return false if one is empty bc unable to make comparison 
            return false;
        }
        else if(!bigI.isEmpty() && !other.bigI.isEmpty()){
            if(bigI.size < other.bigI.size){
                return true;
            }
            else if(bigI.size > other.bigI.size){
                return false;
            }
            else{
                Node<Integer> listOne = bigI.first;
                Node<Integer> listTwo = other.bigI.first;
                for(int i = 0; i < bigI.size; i++){ 
                    if(listOne.getInfo() > listTwo.getInfo()){ // is bigger
                        return false;          
                    }
                    else if(listOne.getInfo() < listTwo.getInfo()){ // is smaller
                        return true;
                    }                    
                    listOne = listOne.getNext();
                    listTwo = listTwo.getNext();               
                }  
                return false; // both numbers are equal numbers
            }
        }
        else{ // both empty so return false, fallback option.
            return false;
        }      
    }
 
    public static void main(String[] args){
        MyBigInteger num1 = new MyBigInteger("500"); // 500
        MyBigInteger num2 = new MyBigInteger("00250"); // 250
        MyBigInteger num3 = new MyBigInteger(""); // empty list
        // MyBigInteger num4 = new MyBigInteger("vrmo1203"); // error (reject)
        // MyBigInteger num5 = new MyBigInteger("000123k"); // error (reject)
        MyBigInteger num6 = new MyBigInteger("1234"); // 1234
        MyBigInteger num7 = new MyBigInteger("1234"); // 1234
        
       
        num2.bigI.printListForward(); // 2, 5, 0
        
        System.out.println(num1.lessThan(num2)); // false
        System.out.println(num2.lessThan(num1)); // true
        System.out.println(num3.lessThan(num2)); // false (empty list)
        
        System.out.println(num6.lessThan(num7)); // false bc they're equal but getting true
        System.out.println(num6.lessThan(num1)); // false
        System.out.println(num1.lessThan(num6)); // true

        MyBigInteger res1 = num1.add(num2); // 750
        MyBigInteger res2 = num1.add(num6); // 1734
        MyBigInteger res3 = num2.add(num6); // 1484

        System.out.println(num6.equals(num7)); // true
    }
    
}