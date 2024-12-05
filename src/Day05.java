import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Day05 {
    public static void main(String[] args) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input\\Day5Example.txt"));
            HashMap<String, HashSet<String>> printRules = new HashMap<>();
            ArrayList<ArrayList<String>> printUpdates = new ArrayList<>();
            bufferedReader.lines().forEach(line -> {
                if(line.contains("|")){
                    String[] ll = line.split("\\|");
                    String key = ll[1];
                    String value = ll[0];
                    printRules.compute(key, (k, v) -> {
                        if(v == null){
                            v = new HashSet<>();
                        }
                        v.add(value);
                        return v;
                    });
                } else if(line.contains(",")){
                    printUpdates.add(new ArrayList<>(Arrays.asList(line.split(","))));
                }
            });
            int total = 0;
            for (ArrayList<String> printUpdate : printUpdates) {
                ArrayList<String> disallowed = new ArrayList<>();
                boolean success = true;
                for (String s : printUpdate) {
                    if(disallowed.contains(s)){
                        success = false;
                    }
                    if(printRules.get(s) != null){
                        disallowed.addAll(printRules.get(s));
                    }
                }

                if(success){
                    int toAdd = Integer.parseInt(printUpdate.get(printUpdate.size()/2));
                    total += toAdd;
                } else {

                }
            }

            System.out.println(total);


        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
