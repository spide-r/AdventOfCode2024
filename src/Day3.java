import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input\\Day3.txt"));
            final int[] finalAmt = {0};
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do(n't)?\\(\\)");
            final boolean[] enabled = {true};
            bufferedReader.lines().forEach(line ->
            {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    String found = matcher.group();
                    if(found.startsWith("mul") && enabled[0]){
                        int first = Integer.parseInt(found.substring(4, found.indexOf(',')));
                        int second = Integer.parseInt(found.substring(found.indexOf(',') + 1, found.indexOf(")")));
                        finalAmt[0] += first * second;
                    }

                    if(found.startsWith("don't")){
                        enabled[0] = false;
                    }

                    if(found.startsWith("do(")){
                        enabled[0] = true;
                    }

                }
            });
            System.out.println("Final Amount is " + finalAmt[0]);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
