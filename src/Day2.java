import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input\\Day2.txt"));
            final int[] safeAmt = {0};
            final int[] safeAmtPt2 = {0};
            bufferedReader.lines().forEach(line ->
            {
                List<Integer>  valuesImmute = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
                ArrayList<Integer> values = new ArrayList<>(valuesImmute);
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

    public static boolean isSafe(ArrayList<Integer> report){
        int lastDelta = 0;
        for (int i = 0; i < report.size() - 1; i++) {
            int current = report.get(i);
            int next = report.get(i+1);
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

    public static boolean isSafePart2(ArrayList<Integer> report){
        int lastDelta = 0;
        for (int i = 0; i < report.size() - 1; i++) {
            int currentLevel = report.get(i);
            int next = report.get(i+1);
            int currentDelta = currentLevel - next;


            if(Math.abs(currentDelta) > 3 || currentDelta == 0 ){

                return safeAfterOneRemove(report);
            }

            if(i == 0){
                lastDelta = currentDelta;
                continue;
            }

            if((lastDelta > 0 && currentDelta < 0 ) || (lastDelta < 0 && currentDelta > 0)){

                return safeAfterOneRemove(report);
            }
        }
        return true;
    }

    private static boolean safeAfterOneRemove(ArrayList<Integer> report){
        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> copied = (ArrayList<Integer>) report.clone();
            copied.remove(i);
            if(isSafe(copied)){
                return true;
            }
        }
        return false;
    }
}
