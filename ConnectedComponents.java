import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ConnectedComponents {

  
  private Set<Integer> getConnectedComponents(int vertices, List<List<Integer>> data){
    
    int[] roots = new int[vertices];
    for (int i = 0; i < roots.length; i++) {
      roots[i] = i;
    }
    
    for(List<Integer> edge : data){
      roots[edge.get(0)-1] = edge.get(1)-1;
    }
    
    HashSet<Integer> distinctRoots = new HashSet<Integer>();
    for(int i=0; i< roots.length; i++){
      int j=i;
      while(roots[j] != j)
        j = roots[j];
      distinctRoots.add(j+1);
    }
    
    return distinctRoots;
    
  }
  
 
  public static void main(String[] args) {
    List<List<Integer>> data = new ArrayList<List<Integer>>();
    data.add(Arrays.asList(1,2,5));
    data.add(Arrays.asList(1,3,10));
    data.add(Arrays.asList(1,6,2));
    data.add(Arrays.asList(5,6,5));
    
    new ConnectedComponents().getConnectedComponents(6, data);
  }
  
  
  
  
}
