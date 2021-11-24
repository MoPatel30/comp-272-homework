/**
 * Write a description of class HomeworkNine here.
 *
 * @author Mohammed Patel
 * @version HW9 11/24
 */
import java.util.*;
import java.io.*; 

public class HomeworkNine {
    
    ArrayList<WeightedEdge> wes;
    int[] set;
    int[] height;
    int numOfVertex;
    int numOfEdges;
    int count;       
    
    public HomeworkNine() {
        wes = new ArrayList<>();
        
        try{  
            FileInputStream file1 = new FileInputStream("artist_edges.txt");       
            FileInputStream file2 = new FileInputStream("Weights.txt");       
            Scanner sc1 = new Scanner(file1);  
            Scanner sc2 = new Scanner(file2);  
            int maxV = 0;
            
            while(sc1.hasNextLine()){ 
                String edges = sc1.nextLine();
                String weight = sc2.nextLine();
                String[] arrOfStr = edges.split("\t");
                
                int nodeOne = Integer.parseInt(arrOfStr[0]);
                int nodeTwo = Integer.parseInt(arrOfStr[1]);
                int curEdgeWeight = Integer.parseInt(weight);
                  
                wes.add(new WeightedEdge(nodeOne, nodeTwo, curEdgeWeight));  
                numOfEdges++;
                
                int n = Math.max(nodeOne, nodeTwo);
                maxV = Math.max(maxV, n);
            } 
            numOfVertex = maxV + 1;
            sc1.close();
            sc2.close();
            
            height = new int[numOfVertex];
            set = new int[numOfVertex];
            
            for(int k = 0; k < numOfVertex; k++){
                set[k] = k;
            }
        }  
        catch(IOException e){  
            e.printStackTrace();  
        }       
    }
    
    
    /**
     * Algorithm to return the minimum spanning tree using Kruskal's method
    */
    public ArrayList<WeightedEdge> KruskalMST() {
  
        PriorityQueue<WeightedEdge> pq = new PriorityQueue(wes);
        ArrayList<WeightedEdge> edgesMST = new ArrayList<>();
        int size = numOfVertex;
        
        int EdgesOfTree = 0;
        int counter = 0;
        
        while(EdgesOfTree <= size-2){
            WeightedEdge e = pq.poll();
            
            if(e != null){
                int p1 = find3(e.v1);
                int p2 = find3(e.v2);
                
                if(p1 != p2){
                    edgesMST.add(e);
                    merge3(p1, p2);
                    EdgesOfTree++;
                }
            }
            counter++;
        }
        count = counter;
        
        return edgesMST;
    }
        
    public int find3(int x){
        int r = x;
        
        while(set[r] != r){
            r = set[r];
        }
        
        int i = x;
        while(i != r){
            int j = set[i];
            set[i] = r;
            i = j;
        }
        
        return r;
    }
    
    public void merge3(int a, int b){
        if(height[a] == height[b]){
            height[a] = height[a] + 1;
            set[b] = a;
        }
        else{
            if(height[a] > height[b]){
                set[b] = a;
            }
            else{
                set[a] = b;              
            }
        }          
    }
    
    
    public static void main(String[] args) {
        HomeworkNine test = new HomeworkNine();              
        ArrayList<WeightedEdge> ans = test.KruskalMST();
        double sum = 0;
        
        for(WeightedEdge edge: ans){
            sum += edge.getWeight();
        }        
        
        System.out.println("Max Vertex Label: " + test.numOfVertex); // 50515
        System.out.println("Vertex Set Size: " + test.numOfVertex);  // 50515 
        System.out.println("Number of Edges: " + test.numOfEdges);   // 819306        
        System.out.println("Number of edges considered for finding MST: " + test.count); // 818976
        System.out.println("Minimum weight of spanning tree: " + sum); // 6.4959317E7
    }
    
}
