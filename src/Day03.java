import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("bigboys\\Day3.txt"));
            final long[] finalAmtPt1 = {0};
            final long[] finalAmtPt2 = {0};
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do(n't)?\\(\\)");
            final boolean[] enabled = {true};
            bufferedReader.lines().forEach(line ->
            {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    String found = matcher.group();
                    if(found.startsWith("mul")){
                        long first = Integer.parseInt(found.substring(4, found.indexOf(',')));
                        long second = Integer.parseInt(found.substring(found.indexOf(',') + 1, found.indexOf(")")));
                        if(enabled[0]){
                            finalAmtPt2[0] += first * second;
                        }
                        finalAmtPt1[0] += first * second;
                    }

                    if(found.startsWith("don't")){
                        enabled[0] = false;
                    }

                    if(found.startsWith("do(")){
                        enabled[0] = true;
                    }

                }
            });
            System.out.println("Final Amount is " + finalAmtPt1[0]);
            System.out.println("Final Amount is " + finalAmtPt2[0]);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
