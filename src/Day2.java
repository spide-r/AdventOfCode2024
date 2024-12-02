import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day2 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input\\Day2.txt"));
            final int[] safeAmt = {0};
            final int[] safeAmtPt2 = {0};
            bufferedReader.lines().forEach(line ->
            {
                int[] values = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                boolean safe = isSafe(values);
                boolean safe2 = isSafePart2(values);
                if(safe){
                    safeAmt[0]++;
                }

                if(safe2){
                    safeAmtPt2[0]++;
                }

            });
            System.out.println("Safe amount is: " + safeAmt[0]);
            System.out.println("Safe amount is: " + safeAmtPt2[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isSafe(int[] report){
        int lastDelta = 0;
        for (int i = 0; i < report.length-1; i++) {
            int current = report[i];
            int next = report[i+1];
            int currentDelta = current - next;

            if(Math.abs(currentDelta) > 3 || Math.abs(currentDelta) < 1 ){
                return false;
            }

            if(i == 0){
                lastDelta = currentDelta;
                continue;
            }

            if((lastDelta > 0 && currentDelta < 0 ) || (lastDelta < 0 && currentDelta > 0)){
                return false;
            }
        }
        return true;
    }

    public static boolean isSafePart2(int[] report){
        int lastDelta = 0;
        for (int i = 0; i < report.length; i++) {
            int current = report[i];
            int next = report[i+1];
            int currentDelta = current - next;

            if(Math.abs(currentDelta) > 3 || Math.abs(currentDelta) < 1 ){
                return false;
            }

            if(i == 0){
                lastDelta = currentDelta;
                continue;
            }

            if((lastDelta > 0 && currentDelta < 0 ) || (lastDelta < 0 && currentDelta > 0)){
                return false;
            }
        }
        return true;
    }
}
