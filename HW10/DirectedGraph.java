/**
 * Write a description of class DirectedGraph here.
 *
 * @author Mohammed Patel
 * @version HW10 12/10
 */
import java.security.*;
import java.util.*;
import java.io.*;

public class DirectedGraph  {
    HashMap<Integer,ArrayList<Integer>> mapping;
    ArrayList<DirectedNodeList> dGraph;
    ArrayList<Integer> temp;
    boolean [] marked;
    int[] finishing;
    static int edges = 0;
    int num = 0;
    int vertices;
    
    
    public DirectedGraph() {
        dGraph = new ArrayList<>();
        vertices = 0;       
    }
    
    
    public void createDirectedGraph(){
        BufferedReader reader;
        
        try{
            reader = new BufferedReader(new FileReader("Slashdot0902.txt"));
            
            //ignore beginning 4 lines
            String line = reader.readLine();
            for(int i = 0; i < 4; i++){
                line = reader.readLine();           
            }
            
            while (line != null) {
               String[] info = line.split("\t");
                
               int vOne = Integer.valueOf(info[0]);
               int vTwo = Integer.valueOf(info[1]);
                
               this.addEdge(vOne,vTwo);
               edges += 1;
               line = reader.readLine();               
            }
            reader.close();      
        }
        catch(IOException e){
            System.out.println(e);
        }    
    }
    
    
    public DirectedGraph(int n) {
      vertices = n;
      mapping = new HashMap<>();
      dGraph = new ArrayList<>(n);
      temp = new ArrayList<>();
      marked= new boolean[n];
      finishing = new int[n];  
    
      for (int i = 0;i < vertices; i++){
        dGraph.add(new DirectedNodeList());
      }
    }
    
    
    public void addEdge(int u, int v) {       
       if (u>=0 && u<vertices && v>=0 && v<vertices){ 
          if (u != v){
              getNeighborList(u).addToOutList(v);
              getNeighborList(v).addToInList(u);
          }
       }
       else throw new IndexOutOfBoundsException();
    }
    
    
    public DirectedNodeList getNeighborList(int u) {
        return dGraph.get(u);
    }
    
    
    public void printAdjacency(int u) {
       DirectedNodeList dnl = getNeighborList(u);
       
       System.out.println ("vertices going into "+u+"  "+dnl.getInList());
       System.out.println ("vertices going out of "+u+"  "+dnl.getOutList());
       System.out.println();
    }
    
    
    public void postOrderDepthFirstTraversal() {
       for (int i = 0; i < vertices; i++){
            if (!marked[i]){
                postOrderDFT(i);   
            }
       } 
       
       marked = new boolean[vertices];
    }
    
    
    public void postOrderDFT(int n){      
        marked[n] = true;
        
        for (Integer u: dGraph.get(n).getInList()){
            if (!marked[u]){
                postOrderDFT(u);
            }
        }
        
        finishing[num] = n; 
        num++;
    }
       
        
    public void depthFirstTraversal() {
        for (int i = num-1; i >= 0; i--){ 
           if (!marked[i]){
               temp.clear();
               DFT(finishing[i]); 
               mapping.put(i,temp);               
           }
        }
    }
    
    
    public void DFT(int n){
        temp.add(n);
        marked[n]=true;
        
        for (Integer u: dGraph.get(n).getOutList()){
            if (!marked[u]){
                DFT(u);
            }
        }
    }
    
    
    public void findMaxSize(){
        int max = 0;
        
        for (Map.Entry<Integer, ArrayList<Integer>> entry : mapping.entrySet()) {
            max = Math.max(entry.getValue().size(), max);
        }
        
        System.out.println("Biggest: " + max);
    }
    
    
    public void createReducedGraph(HashMap<Integer,ArrayList<Integer>> map){   
        HashSet<Integer> sameEdge = new HashSet<>();
        DirectedGraph reduced = new DirectedGraph(10599);

        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            ArrayList<Integer> valOne = entry.getValue(); 
            int vertexOne = entry.getKey();
            
            for(Integer j: valOne){ 
                sameEdge.add(j);
            }
            
            for (Map.Entry<Integer, ArrayList<Integer>> entry2 : map.entrySet()){ 
                ArrayList<Integer> valTwo = entry2.getValue();
                int vertexTwo = entry2.getKey();                
                
                if(vertexOne != vertexTwo){ 
                    for(Integer k: valTwo){
                        if(!sameEdge.add(k)){
                            reduced.addEdge(vertexTwo, k);
                        }
                    }
                }
            }      
        }            
    }
 
    
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(82168);
        
        // 1
        graph.createDirectedGraph();
        System.out.println(graph.dGraph.size()); 
        System.out.println(edges); 
        graph.postOrderDepthFirstTraversal(); 
        
        // 2
        graph.depthFirstTraversal();
        System.out.println("Size: "+ graph.mapping.size()); 
        
        // 3
        graph.findMaxSize(); 
        
        // 4
        graph.createReducedGraph(graph.mapping); 
        
    }    
}  