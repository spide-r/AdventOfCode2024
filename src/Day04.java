import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Day04 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("bigboys\\Day4.txt"));

            HashMap<Integer, String> crossword = new HashMap<>();
            final int[] lineCount = {0};
            bufferedReader.lines().forEach(line ->
            {
                crossword.put(lineCount[0], line);
                lineCount[0]++;
            });
            final int[] xmasCount = {0};
            final int[] x_masCount = {0};
            crossword.forEach((i, s) -> {
                for (int j = 0; j < s.length(); j++) {
                    if(checkVertical(crossword, i, j)){
                        xmasCount[0]++;
                    }

                    if(checkHorizontal(crossword, i, j)){
                       xmasCount[0]++;
                    }

                    if(checkDiagonalRight(crossword, i, j)){
                        xmasCount[0]++;
                    }

                    if(checkDiagonalLeft(crossword, i, j)){
                        xmasCount[0]++;
                    }
                    if(checkX_MAS(crossword, i, j)){
                        x_masCount[0]++;
                    }
                }
            });

            System.out.println("XMAS Count: " + xmasCount[0]);
            System.out.println("X-MAS Count: " + x_masCount[0]);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean checkX_MAS(HashMap<Integer, String> crossword, int i, int j){
        if(i + 2 >= crossword.size() || j + 2 >= crossword.get(i).length()){
            return false;
        }

        String potentalX_MAS = String.valueOf(crossword.get(i).charAt(j)) + crossword.get(i).charAt(j + 2) + //header
                crossword.get(i + 1).charAt(j + 1) + // middle a
                crossword.get(i + 2).charAt(j) + crossword.get(i + 2).charAt(j + 2); // footer
        return isX_MAS(potentalX_MAS);

    }

    public static boolean isXMAS(String word){
        return word.equals("XMAS") || word.equals("SAMX");
    }

    public static boolean isX_MAS(String word){
        return word.equals("MSAMS") || word.equals("SMASM") || word.equals("MMASS") || word.equals("SSAMM");
    }

    public static boolean checkVertical(HashMap<Integer, String> crossword, int i, int j){
        if(i + 3 >= crossword.size()){
            return false;
        }

        StringBuilder potentialXMAS = new StringBuilder();
        for (int k = i; k < i+4; k++) {
            potentialXMAS.append(crossword.get(k).charAt(j));
        }
        return isXMAS(potentialXMAS.toString());
    }

    public static boolean checkHorizontal(HashMap<Integer, String> crossword, int i, int j){
        if(j + 3 >= crossword.get(i).length()){

            return false;
        }

        StringBuilder potentialXMAS = new StringBuilder();
        for (int k = j; k < j+4; k++) {
            potentialXMAS.append(crossword.get(i).charAt(k));
        }
        return isXMAS(potentialXMAS.toString());
    }

    public static boolean checkDiagonalRight(HashMap<Integer, String> crossword, int i, int j){
        if(i + 3 >= crossword.size() || j + 3 >= crossword.get(i).length()){
            return false;
        }
        //check diagonal right
        StringBuilder potentialXMAS = new StringBuilder();
        for (int k = 0; k < 4; k++) {

            potentialXMAS.append(crossword.get(i+k).charAt(j+k));
        }

        return isXMAS(potentialXMAS.toString());

    }

    public static boolean checkDiagonalLeft(HashMap<Integer, String> crossword, int i, int j){

        if(i + 3 >= crossword.size() || j - 3 < 0){
            return false;
        }

        //check diagonal left
        StringBuilder potentialXMAS = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            potentialXMAS.append(crossword.get(i+k).charAt(j-k));
        }
        return isXMAS(potentialXMAS.toString());
    }
}
