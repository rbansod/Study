import java.util.ArrayList;
import java.util.HashMap;

public class DEF {

  public static final int TRUCK_SPACE_LEAVE_EMPTY = 30;

  ArrayList<Integer> IDsOfPackages(int truckSpace, ArrayList<Integer> packages) {


    HashMap<Integer, ArrayList<Integer>> valueMap = new HashMap<Integer, ArrayList<Integer>>();
    for (int i = 0; i < packages.size(); i++) {
      if (valueMap.containsKey(packages.get(i))) {
        valueMap.get(packages.get(i)).add(i);
      } else {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        indices.add(i);
        valueMap.put(packages.get(i), indices);
      }
    }

    ArrayList<Integer> sol = new ArrayList<Integer>(2);
    int cMax =0;
    int toFill = truckSpace - TRUCK_SPACE_LEAVE_EMPTY;
    for (int i = 0; i < packages.size(); i++) {
      int remaining = toFill - packages.get(i);
      if (valueMap.containsKey(remaining)) {
        for (Integer index : valueMap.get(remaining)) {
          if (!index.equals(i)) {
            if (sol.size() != 2) {
              sol.add(i);
              sol.add(index);
              cMax = Math.max(packages.get(i), packages.get(index));
            }else{
              if(cMax < packages.get(i) || cMax< packages.get(index)){
                sol.clear();
                sol.add(i);
                sol.add(index);
                cMax = Math.max(packages.get(i), packages.get(index));
              }
            }
          }
        }
      }
    }

    return sol;
  }
  
  
  public static void main(String[] args) {
    DEF obj = new DEF();
    ArrayList<Integer> listOfIds = new ArrayList<Integer>();
    listOfIds.add(30);
    listOfIds.add(30);
    listOfIds.add(30);
    listOfIds.add(30);
    listOfIds.add(30);

    System.out.println(obj.IDsOfPackages(90,listOfIds));
  }
}

/*
1. Create HashMap of integer, List<integer> to preprocess input list for easy lookup.
2. for each element in input do:
    - find corresponding cost of paired element
    - if there are element(s) of such cost (can be more than one)
      check if it is not same element (i.e. not repeated same package)
        if solution list is empty
          add pair to solution list
          keep track of max element in the solution list
        if solution list is not empty
          see if any one of current elements is larger than both in solution list
            if so, clear the list and add current pair to solution.
3. return solution list.

time complexities:
building of HashMap - O(n)
processing: 
  each element is visited once, and is paired only with possible matches
  work case complexity: O(n*k)
    where n = size of input array
          k = length of elements with same space

*/