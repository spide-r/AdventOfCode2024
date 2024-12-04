import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Day01 {

    public static void main(String[] args) {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("bigboys\\Day1.txt"));
            bufferedReader.lines().forEach(line ->
            {
                String[] parts = line.split(" {3}");
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));

            });
            left.sort(Comparator.naturalOrder());
            right.sort(Comparator.naturalOrder());
            long distance = partOneV2(left, right);
            System.out.println("Final Distance is " + distance);
            long score = partTwoV2(left, right);

            System.out.println("Final Score is " + score);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int partTwo(ArrayList<Integer> left, ArrayList<Integer> right){
        int score = 0;
        for (int i = 0; i < left.size(); i++) {
            int rightInt = right.get(i);
            if(left.contains(rightInt)){
                score += rightInt;
            }
        }
        return score;
    }

    public static long partTwoV2(ArrayList<Integer> left, ArrayList<Integer> right){
        long score = 0L;

        int leftIdx = 0;
        for (int rightInt : right) {
            int leftInt = left.get(leftIdx);
            while (leftInt < rightInt) {
                leftIdx++;
                leftInt = left.get(leftIdx);
            }
            if (leftInt == rightInt) {
                score += rightInt;
            }
        }
        return score;
    }


    public static int partOne(ArrayList<Integer> left,ArrayList<Integer> right){
        int distance = 0;
        for (int i = 0; i < left.size(); i++) {
            int leftInt = left.get(i);
            int rightInt = right.get(i);
            int diff = Math.abs(leftInt - rightInt);
            distance += diff;
        }
        return distance;
    }
    public static long partOneV2(ArrayList<Integer> left,ArrayList<Integer> right ){
        long distance = 0L;
        for (int i = 0; i < left.size(); i++) {
            int leftInt = left.get(i);
            int rightInt = right.get(i);
            int diff = Math.abs(leftInt - rightInt);
            distance += diff;
        }
        return distance;
    }
}
