import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ABC {


    int getMinimumCostToConstruct(
          int numTotalEdgeNodes,
          int numTotalAvailableNetworkRoutes,
          List<List<Integer>> networkRoutesAvailable,
          int numNewNetworkRoutesConstruct,
          List<List<Integer>> constNewNetworkRoutes) {
      
        int[] union = new int[numTotalEdgeNodes + 1];
        for (int i = 0; i <= numTotalEdgeNodes; i++) {
            union[i] = i;
        }
        String s;
        for (List<Integer> route : networkRoutesAvailable) {
            connect(union, route.get(0), route.get(1));
        }

        Set<Integer> connectedComponents = new HashSet<Integer>();

        for (int i = 1; i < union.length; i++) {
            connectedComponents.add(root(union, i));
        }

        Collections.sort(constNewNetworkRoutes, new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2) - o2.get(2);
            }
        });

        int totalCost = 0;

        for (int i = 0; i < constNewNetworkRoutes.size(); i++) {
            if (connectedComponents.size() == 1) {
                return totalCost;
            }

            int p = constNewNetworkRoutes.get(i).get(0);
            int q = constNewNetworkRoutes.get(i).get(1);
            int cost = constNewNetworkRoutes.get(i).get(2);

            if (root(union, p) != root(union, q)) {
                int rootP = root(union, p);
                connect(union, p, q);
                connectedComponents.remove(rootP);
                totalCost += cost;
            }
        }


        return connectedComponents.size() == 1 ? totalCost : -1;
  }

  private void connect(int[] union, int p, int q) {
      int rootP = root(union, p);
      int rootQ = root(union, q);
      union[rootP] = rootQ;
  }

  private int root(int[] union, int p) {
      while (p != union[p]) {
          p = union[p];
      }

      return p;
  }

  public static void main(String[] args) {
        ABC mim = new ABC();
        List<List<Integer>> roads = new ArrayList<List<Integer>>();
        roads.add(Arrays.asList(1, 4));
        roads.add(Arrays.asList(4, 5));
        roads.add(Arrays.asList(2, 3));
        List<List<Integer>> newRoads = new ArrayList<List<Integer>>();
      newRoads.add(Arrays.asList(1, 2, 5));
      newRoads.add(Arrays.asList(1, 3, 10));
      newRoads.add(Arrays.asList(1, 6, 2));
      newRoads.add(Arrays.asList(5, 6, 5));

    System.out.println(
      mim.getMinimumCostToConstruct(6, 3, roads, 4, newRoads));
  }
}

/*
1. Need to find connected components in given graph- use union find to do that.
    - create union array for all vertices
    - map each vertex to its parent, based on edges
2. Find roots of connected components in union find
    - for each vertex, find the top most root it belongs to
    - add it to set to get total connected components
3. Now since try to merge the connected components:
 3.1 we need minimum cost to merge so sort constNewNetworkRoutes in decending order. 
 3.2 actual merging:
   - for each possible edge 
     see if both end points belong to different connected components
     if so, add the edge, add its cost to total cost and merge the connected components.
     
time complexity: 
union find setup: O(vertices)
union find- find root element: O(vertices) in worst case
sorting of new edges: O(Klog(K)) - where K is size of constNewNetworkRoutes
*/