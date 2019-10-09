
public class T1 {

  public static double solution(double[][] readings, long endTime) {
    
    
    if(readings.length == 0 || readings == null || endTime == 0){
      return 0;
    }
    double cSpeed = readings[0][1]; //kmph
    double cSpeedTime = readings[0][0];
    double totalDist = 0;
    int i=0;
    for(i = 1; i< readings.length && readings[i][0]<= endTime ; i++){
      //calculate dist travelled
      totalDist += cSpeed* convertSecondsToHours(readings[i][0]-cSpeedTime);
      cSpeed = readings[i][1];
      cSpeedTime = readings[i][0];
    }
    
//    if(i< readings.length){ // entries exceeded
      totalDist += cSpeed* convertSecondsToHours(endTime - cSpeedTime);
//    }
//    else{
//      totalDist = cSpeed* convertSecondsToHours(Math.min(endTime, arg1))
//    }
    return totalDist;
  }
  
  private static double convertSecondsToHours(double sec) {
    return sec/3600;
  }
  
  public static void main(String[] args) {
    double[][] data = {{0,90},{300,80}};
    System.out.println(solution(data, 600));
    
  }
}
