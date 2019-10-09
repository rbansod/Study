import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Jag {

  public static int lastStoneWeight(List<Integer> a){
    
    
    List<Integer> li = new ArrayList<>(a);
    //li.remove(0);
    
    if(li.size()==0)
      return 0;
    
    Collections.sort(li);
    int t = li.get(li.size()-1);
    for(int i=li.size()-2; i>= 0; i--){
        t = Math.abs(li.get(i)-t);        
    }
    return t;
  }
  
  
  public static void main(String[] args) {
    //List<Integer> a = Arrays.asList(46188086,339992587,742976890,801915058,393898202,717833291,843435009,361066046,884145908,668431192,586679703,792103686,85425451,246993674,134274127,586374055,923288873,292845117,399188845,842456591,410257930,333998862,16561419,624279391,459765367,969764608,508221973,82956997,437034793,553121267,554066040,199416087);
    List<Integer> a = Arrays.asList(2,4,5);
    System.out.println(lastStoneWeight(a));
  }
}
