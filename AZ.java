public class AZ {

  public int solution(int[] heights) {

    int strokes = 0;
    for (int i = 0; i < heights.length; i++) {
      for (int j = 0; j < heights[i]; j++) {
        if (i == 0) {
          strokes++;
          continue;
        }
        if (j >= heights[i - 1]) {
          strokes++;
        }

      }
    }
    return strokes;
  }

  public int solution2(int[] heights) {
    if(heights==null || heights.length<=0) return 0;
    int strokes = heights[0];
    for (int i = 1; i < heights.length; i++) {
      if (heights[i] - heights[i - 1] > 0) {
        strokes += heights[i] - heights[i - 1];
      }
    }
    return strokes;
  }

  public static void main(String[] args) {
    // System.out.println(new AZ().solution2(new int[]{5,8}));
    System.out.println(new AZ().solution(new int[] { 1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2 }));
  }
}
