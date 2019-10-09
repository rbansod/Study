import java.util.Arrays;
import java.util.PriorityQueue;

public class Painter {

  public static void main(String[] args) {

    int[] a = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
    int k = 3;
    System.out.println(getMaxCost(a, k));
  }

  public static int getMaxCost(int[] boards, int painters) {

    int sum = Arrays.stream(boards).sum();
    float davg = sum / painters;
    int maxCost = 0;
    int cCost = 0;
    int i = 0;
    while (true) {
      if (i == boards.length)
        break;
      if (Math.abs(davg - cCost) < Math.abs(davg - (cCost + boards[i]))) {
        // partition this..
        if (maxCost < cCost)
          maxCost = cCost;
        sum -= cCost;
        painters--;
        davg = sum / painters;
        davg = Math.round(davg);
        cCost = boards[i];
        i++;
      } else {
        cCost = cCost + boards[i];
        i++;
      }
    }

    return maxCost>cCost? maxCost: cCost;
  }
  
  

}
