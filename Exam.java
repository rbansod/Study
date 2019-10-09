import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Exam {


    static int check_log_history(List<String> events){
      
      Stack<String> st = new Stack<String>();
      HashSet<String> locked = new HashSet<String>();
      for(int i=0; i< events.size(); i++){
        String s = events.get(i);
        String[] data = s.split(" ");
        if(data[0].equals("ACQUIRE")){
            if(locked.contains(data[1]))
              return i+1;
            st.push(data[1]);
            locked.add(data[1]);
        }else{
            locked.remove(data[1]);
            if(!st.pop().equals(data[1]))
              return i+1;
        }
      }
      if(locked.size()!= 0)
        return events.size()+1;
      
      return 0;
      
    }
    
    
    

  public static void main(String[] args) {

//      int [] data = new int[]{1,2,5,9,9};
//      int res = new Exam().solution(data, 1);
    
       List<String> ip = new ArrayList<String>();
       ip.add("ACQUIRE 364");
       ip.add("ACQUIRE 84");
       ip.add("RELEASE 84");
      System.out.println(new Exam().check_log_history(ip));

  }
}
