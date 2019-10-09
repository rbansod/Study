import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Yelp {

  
  public static List<Chain> detectAndOrderChainBusiness(List<Business> businesses, String location) {
    
    HashMap<String,Set<Integer>> chains = new HashMap();
    for(Business b : businesses){
      if(b.location.equals(location)){
        if(chains.containsKey(b.name)){
          chains.get(b.name).add(new Integer(b.id));
        }else{
          HashSet<Integer> s = new HashSet<>();
          s.add(new Integer(b.id));
          chains.put(b.name, s);
        }
      }
    }
    
    List<Chain> chainList = new ArrayList<Chain>();
    for(Map.Entry<String, Set<Integer>> e : chains.entrySet()){
      chainList.add(new Chain(e.getKey(), e.getValue().size()));
    }
    
    Collections.sort(chainList, new Comparator<Chain>() {
      @Override
      public int compare(Chain arg0, Chain arg1) {
       if(arg0.frequency != arg1.frequency)
         return arg1.frequency - arg0.frequency;
       return arg0.name.compareTo(arg1.name);
      }
    });
    
    return chainList;
  }
  
  
  public static void main(String[] args) {
    
  }
}

class Chain {
  String name;
  Integer frequency;
  Chain(String name, Integer frequency) {
    this.name = name;
    this.frequency = frequency;
  }
}


class Business {
  String name;
  String location;
  String id;
  Business(String name, String location, String id) {
    this.name = name;
    this.location = location;
    this.id = id;
  }
}
