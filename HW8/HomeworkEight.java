/**
 * Write a description of class HomeworkEight here.
 *
 * @author Mohammed Patel
 * @version HW8 11/16
 */
import java.io.*; 
import java.util.*;
import java.util.ArrayDeque;  
import java.util.Deque; 

public class HomeworkEight{
    
    int numOfVertices;
    Graph data; 
    
    /**
    * Constructor for objects of class HomeworkEight
    * Builds the undirected connected graph of the enron email text file
    */
    public HomeworkEight(String fileName, int size){
        numOfVertices = size;
        data = new Graph(size);
        try{  
            FileInputStream file = new FileInputStream(fileName);       
            Scanner sc = new Scanner(file);  

            while(sc.hasNextLine()){ 
                String temp = sc.nextLine();
                if(fileName == "Email-Enron.txt"){                    
                    String[] arrOfStr = temp.split("\t");
                    int nodeOne = Integer.parseInt(arrOfStr[0]);
                    int nodeTwo = Integer.parseInt(arrOfStr[1]);
                    data.addEdge(nodeOne, nodeTwo);
                }
                else if(fileName == "Graph.txt"){
                    String[] arrOfStr = temp.split(",");
                    int nodeOne = Integer.parseInt(arrOfStr[0]);
                    int nodeTwo = Integer.parseInt(arrOfStr[1]);
                    data.addEdge(nodeOne, nodeTwo);
                }
            }  
            sc.close(); 
        }  
        catch(IOException e){  
            e.printStackTrace();  
        }  
    }

    
    /** Question 1.1 - Depth First Traversal
    * 
    * Find the total number of connected components
    * 
    */ 
    public int questionOneDFS(){
        boolean[] visited = new boolean[numOfVertices];
        int count = 0;
        
        for (int n = 0; n < numOfVertices; n++) {
            if (!visited[n]) {
                count++;
                System.out.println(count);
                questionOneDFSHelper(n, visited);
            }
        }
        
        return count;
    }
    public void questionOneDFSHelper(int n, boolean[] visited){
        ArrayList<Integer> neighbors = data.getNeighbors(n);
        visited[n] = true; 
        
        for(int i = 0; i < neighbors.size(); i++){
            int node = neighbors.get(i);
            
            if(!visited[node]){
                questionOneDFSHelper(node, visited);
            }
        }
    }
      
    /** Question 1.2 - Disjoint Set Union Operation 
    * 
    * Find the total number of connected components
    * 
    */ 
    public int questionOneDSU(){
        int[] idx = new int[numOfVertices];
        
        for(int i = 0; i < idx.length; i++){
            idx[i] = i;
        }
        
        for(int j = 0; j < numOfVertices; j++){
            ArrayList<Integer> neigh = data.getNeighbors(j);
            
            for(int node: neigh){
                union(idx, j, node);
            }
        }
        
        HashSet<Integer> comps = new HashSet<>();
        
        for(int k = 0; k < idx.length; k++){
            comps.add(find(idx, k));
        }
        
        return comps.size();
    }   
    public void union(int[] idx, int u, int v){
        int p1 = find(idx, u);
        int p2 = find(idx, v);        
        idx[p1] = p2;
    }
    public int find(int[] idx, int k){
        if(idx[k] != k){
            idx[k] = find(idx, idx[k]);
        }
        
        return idx[k];
    }
       
    /** Question 1.3 - Breadth First Traversal
    *
    * Find the total number of connected components
    * 
    */  
    public int questionOneBFS(){
        boolean nodes[] = new boolean[numOfVertices]; 
        Deque<Integer> deque = new ArrayDeque<Integer>();  
        int count = 0;  
        
        for(int j = 0; j < numOfVertices; j++){
            if(!nodes[j]){
                deque.add(j);
                nodes[j] = true; 
                count++;                
            }
            
            while (deque.size() != 0){  
                int n = deque.poll();         
                ArrayList<Integer> neighbors = data.getNeighbors(n);
                
                for (int i = 0; i < neighbors.size(); i++){ 
                    int neigh = neighbors.get(i);
                    
                    if (!nodes[neigh]){  
                        nodes[neigh] = true;  
                        deque.add(neigh);
                    }  
                }
            }
        }
        
        return count;
    }
    
    
    /** Question #2 (Modified BFS)
    * 
    * Find the size of the largest connected component
    * 
    */
    public int questionTwo(){
        boolean nodes[] = new boolean[numOfVertices]; 
        Deque<Integer> deque = new ArrayDeque<Integer>();  
        int count = 0;  
        int count2 = 0;
        
        for(int j = 0; j < numOfVertices; j++){
            if(!nodes[j]){
                deque.add(j);
                nodes[j] = true; 
                count = Math.max(count, count2);
                count2 = 1;
            }
            
            while (deque.size() != 0){  
                int n = deque.poll();         
                ArrayList<Integer> neighbors = data.getNeighbors(n);
                
                for (int i = 0; i < neighbors.size(); i++){ 
                    int neigh = neighbors.get(i);

                    if (!nodes[neigh]){  
                        nodes[neigh] = true;  
                        deque.add(neigh);
                        count2++;
                    }  
                }
            }
        }
        
        return count;
    }    
 
    
    /** Question #3 (Modified BFS)
    * 
    * Find number of connected components that are trees
    * 
    */
    public int questionThree(){
        boolean nodes[] = new boolean[numOfVertices]; 
        Deque<Integer> deque = new ArrayDeque<Integer>(); 
        HashSet<Integer> visited = new HashSet<>();
        boolean isNotCycle = false;
        int count = 0;  
        
        for(int j = 0; j < numOfVertices; j++){
            if(!nodes[j]){
                deque.add(j);
                nodes[j] = true; 
                
                if(isNotCycle){
                    count++;
                }
                
                isNotCycle = true;
                visited.clear();
                visited.add(j);
            }
            
            while (deque.size() != 0){  
                int n = deque.poll();         
                ArrayList<Integer> neighbors = data.getNeighbors(n);
                
                for (int i = 0; i < neighbors.size(); i++){ 
                    int neigh = neighbors.get(i);
                    
                    if(visited.contains(neigh)){
                        isNotCycle = false;
                    }

                    if (!nodes[neigh]){  
                        visited.add(neigh);
                        nodes[neigh] = true;  
                        deque.add(neigh);
                    }  
                }
            }
        }
        
        return count;
    }    
   
    
    public static void main(String[] args){
        // Enron Email Text - size: 36,692
        HomeworkEight test = new HomeworkEight("Email-Enron.txt", 36692);

        //System.out.println(test.questionOneDFS());  // 1065
        System.out.println(test.questionOneDSU());    // 1065
        System.out.println(test.questionOneBFS());    // 1065
        System.out.println(test.questionTwo());       // 33696 
        System.out.println(test.questionThree());     // 0
        
        
        // Graph Text - size: 1,000,000
        HomeworkEight test2 = new HomeworkEight("Graph.txt", 1000000); 

        //System.out.println(test2.questionOneDFS()); // 2496
        System.out.println(test2.questionOneDSU());   // 2496
        System.out.println(test2.questionOneBFS());   // 2496
        System.out.println(test2.questionTwo());      // 997485 
        System.out.println(test2.questionThree());    // 2475   
        
    }    
}