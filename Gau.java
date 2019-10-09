import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Gau {
  public static void main(String[] args) {
  
   // Scanner sc = new Scanner(System.in);
    List<String> ip2 = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      while(true){
        String s = br.readLine();
        if(s == "" || s == null)
          break;
        ip2.add(s);
      }
    } catch (Exception e) {
    }
    
    
//    String input = sc.nextLine();
//    String[] ip = input.split("\n");
//    while (sc.hasNext()) {
//      ip2.add(sc.nextLine());
//    }
    
    for(String s: ip2){
      List<String> parsed = parseLine(s);
      System.out.println(parsed.get(0)+ ", " 
        + parsed.get(6)+" years old, is from "
        +parsed.get(5)+" and is interested in "
        +parsed.get(3));
    }
 }
  
  
  private static List<String> parseLine(String line) {
    
    List<String> res = new ArrayList<String>();
    StringBuffer bf = new StringBuffer();
    boolean quote = false;
    boolean escapequote = false;
    for(int i=0; i< line.length(); i++){
      char c = line.charAt(i);
      if(c == '\"' && line.charAt(i+1)== '\"'){
        //end of quotes in escape
        escapequote = !escapequote;
        bf.append('\"');
        i++;
      }
      else if(c == '\"'){
        quote = !quote;
      }
      else if(c == ','){
        if(quote){
          bf.append(c);
        }else {
          res.add(bf.toString());
          bf = new StringBuffer();
        }
      }
      else {
        bf.append(c);
      }
    }
    res.add(bf.toString());
    return res;
  }

  


}


