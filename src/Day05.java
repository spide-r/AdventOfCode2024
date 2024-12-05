import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

public class Day05 {
    public static void main(String[] args) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input\\Day5.txt"));
            HashMap<String, HashSet<String>> printRulesInverted = new HashMap<>();
            HashMap<String, HashSet<String>> printRules = new HashMap<>();
            ArrayList<ArrayList<String>> printUpdates = new ArrayList<>();
            bufferedReader.lines().forEach(line -> {
                if(line.contains("|")){
                    String[] ll = line.split("\\|");
                    String value = ll[1];
                    String key = ll[0];
                    printRulesInverted.compute(value, (k, v) -> {
                        if(v == null){
                            v = new HashSet<>();
                        }
                        v.add(key);
                        return v;
                    });

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
            int badTotal = 0;
            for (ArrayList<String> printUpdate : printUpdates) {
                ArrayList<String> disallowed = new ArrayList<>();
                boolean success = true;
                for (String s : printUpdate) {
                    if(disallowed.contains(s)){
                        success = false;
                        break;
                    }
                    if(printRulesInverted.get(s) != null){
                        disallowed.addAll(printRulesInverted.get(s));
                    }
                }

                if(success){
                    int toAdd = Integer.parseInt(printUpdate.get(printUpdate.size()/2));
                    total += toAdd;
                } else {
                    ArrayList<String> sortedBadValue = customQuicksort(printUpdate, printRules, printRulesInverted);
                    int badValue = Integer.parseInt(sortedBadValue.get(sortedBadValue.size()/2));
                    badTotal += badValue;
                }
            }

            System.out.println(total);
            System.out.println(badTotal);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> customQuicksort(ArrayList<String> list, HashMap<String, HashSet<String>> sortingRules, HashMap<String, HashSet<String>> sortingRulesInverted){
        if(list.size() < 2){
            return list;
        }

        int pivotIdx = 0; //bad choice of pivot!!! maybe change?
        String pivot = list.get(pivotIdx);
        HashSet<String> rulesForPivot = sortingRules.getOrDefault(pivot, new HashSet<>()); // greater than - pivot must be placed *before* these numbers
        HashSet<String> rulesForPivotInverse = sortingRulesInverted.getOrDefault(pivot, new HashSet<>()); //less than - pivot must be placed *after* these numbers

        ArrayList<String> before = new ArrayList<>();
        ArrayList<String> middle = new ArrayList<>();
        ArrayList<String> after = new ArrayList<>();
        for (String value : list){
            if(rulesForPivot.contains(value)){
                after.add(value);
            } else if(rulesForPivotInverse.contains(value)){
                before.add(value);
            } else {
                middle.add(value);
            }
        }

        ArrayList<String> sortedList = new ArrayList<>();
        sortedList.addAll(customQuicksort(before, sortingRules, sortingRulesInverted));
        sortedList.addAll(customQuicksort(middle, sortingRules, sortingRulesInverted));
        sortedList.addAll(customQuicksort(after, sortingRules, sortingRulesInverted));


        return sortedList;
    }
}
