
public class GFG {
  public static void main(String[] args) {
    int[] b = {1,2,3};
    /*
     * {} {1} {1,1,1} {1,2,3} {3,2,1} {3,0,0,3}
     */
    int res = getStoredVolume(b);
    System.out.println(res);
  }

  public static int getStoredVolume(int[] barriers) {

    int[] b = barriers;
    int vol = 0;
    for (int i = 0; i < b.length - 1;) {
      int max = i + 1;
      for (int j = i + 1; j < b.length; j++) {
        if (b[j] > b[i]) {
          max = j;
          break;
        }
        if (b[j] > b[max]) {
          max = j;
        }
      }
      vol += Math.min(b[i], b[max]) * (max - i);
      i = max;
    }
    return vol;
  }
  
  
  
  public static int getStoredVolume2(int[] barriers) {

    int[] b = barriers;
    int vol = 0;
    int[] pMax = new int[b.length];
    pMax[b.length-1] = 0;
    for(int i = b.length-2; i>= 0; i--){
      pMax[i] = Math.max(pMax[i+1], b[i+1]);
    }
    
    for (int i = 0; i < b.length - 1;) {
      int max = i+1;
      for(;max<pMax.length; max++){
        if(b[max] == pMax[max-1]){
          //found maximum
          break;
        }
      }
      vol += Math.min(b[i], b[max]) * (max - i);
      i = max;
    }
    return vol;
  }
}

  
  
  
  
  
  
  
  