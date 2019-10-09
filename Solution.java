import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Solution {
  // If you need extra classes, you can define them privately here within class Solution

  static void parseConfiguration(List<String> configurationLines) {
    // Your code here. Writes to standard output.
    HashMap<String,GraphNode> nodesMap = parseToGraph(configurationLines);
    List<GraphNode> reverseNodes = topologicalSort(nodesMap);
    //Collections.reverse(reverseNodes);
    for(GraphNode n : reverseNodes){
      for(GraphNode child : n.children){
        for(Map.Entry<String, String> e: nodesMap.get(n.name).config.entrySet()){
          if(!child.config.containsKey(e.getKey())){
            child.config.put(e.getKey(), e.getValue());
          }
        }
      }
    }
    
    Collections.sort(reverseNodes, new Comparator<GraphNode>() {

      @Override
      public int compare(GraphNode arg0, GraphNode arg1) {
        // TODO Auto-generated method stub
        return arg0.name.compareTo(arg1.name);
      }
    });
    
    for(GraphNode n : reverseNodes){
      printNode(n);
      System.out.println();
    }
  }
  
  static List<GraphNode> topologicalSort(HashMap<String,GraphNode> nodesMap){
    List<GraphNode> nodes = new ArrayList<>();
    Stack<GraphNode> stack = new Stack<>();
    Set<GraphNode> visited = new HashSet<>();
    for(GraphNode n: nodesMap.values()){
      if(!visited.contains(n)){
        topologicalSortUtil(n, stack, visited, nodes);
      }
    }
    
    while(!stack.isEmpty()){
      nodes.add(stack.pop());
    }
    return nodes;
  }
  
  static void topologicalSortUtil(GraphNode node, Stack<GraphNode> stack, Set<GraphNode> visited, List<GraphNode> nodes){
    visited.add(node);
    for(GraphNode n : node.children){
      if(!visited.contains(n)){
        topologicalSortUtil(n, stack, visited, nodes);
      }
    }
    stack.push(node);
  }
  
  static void printNode(GraphNode node){
    System.out.println("["+node.name+"]");
    for(Map.Entry<String, String> e : node.config.entrySet()){
      System.out.println(e.getKey()+ " = "+ e.getValue());
    }
//    for(GraphNode gn : node.children){
//      System.out.println("     child: "+gn.name);
//    }
  }
  
  static HashMap<String,GraphNode> parseToGraph(List<String> configurationLines){
    
    HashMap<String,GraphNode> nodes = new HashMap();
    GraphNode cNode = null;
    for(String s: configurationLines){
      if(s.trim().length()<1){
        continue;
      }
      //System.out.println(s);
      if(s.charAt(0)=='['){
        String name="";
        String parent = null;
        if(s.contains(":")){
          name = s.substring(1, s.indexOf(":"));
          parent = s.substring(s.indexOf(":")+1, s.length()-1);
        }else {
          name = s.substring(1, s.length()-1);
        }
        cNode = nodes.getOrDefault(name, new GraphNode());
        cNode.name = name;
        nodes.put(name, cNode);
        if(parent != null){
          GraphNode p = nodes.getOrDefault(parent, new GraphNode());
          p.name = parent;
          p.children.add(cNode);
          nodes.put(parent, p);
        }
      }
      else {
        String[] data = s.split(" = ");
        cNode.config.put(data[0], data[1]);
      }
    }
    
    return nodes;
  }
  

  static class GraphNode{
    String name;
    TreeMap<String, String> config;
    Set<GraphNode> children;
    
    public GraphNode() {
     config = new TreeMap<String, String>();
     children = new HashSet<>();
    }
  }
  
  
  
  
  
  
  
  
  // DO NOT MODIFY MAIN()
  public static void main(String[] args) {
    List<String> input = new ArrayList<String>();

    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dekulkar\\Desktop\\Work\\workspace\\eclipse\\test\\src\\input.txt"))) {
      while ((line = br.readLine()) != null) {
        input.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    parseConfiguration(input);
  }
  
}