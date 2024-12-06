import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day06 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input\\Day6Example.txt"));
            List<String> inputLong = bufferedReader.lines().toList();
            int width = inputLong.get(0).length();
            int depth = inputLong.size();

            char[][] input = new char[width][depth];
            int guardX = -99;
            int guardY = -99;
            for (int i = 0; i < inputLong.size(); i++) {
                //System.out.println(inputLong.get(i));
                char [] line = inputLong.get(i).toCharArray();
                for (int j = 0; j < width; j++) {
                    char c = line[j];
                    if(c == '^' || c == 'v' || c == '<' || c == '>'){
                        guardX = i;
                        guardY = j;
                    }
                    input[i][j] = line[j];
                }
            }


            //
            boolean guardExists = true;
            int guardUniquePositions = 1; //including the guards starting position
            int obstacles = 0;
            //System.out.println(Arrays.deepToString(input).replace("],", "\n"));

            while (guardExists){
                char guard = input[guardX][guardY];
                //System.out.println(guard + " at " + guardX + guardY);

                if(checkObstacleAtPoint(input, guardX, guardY, guard)){
                    obstacles++;
                }

                int newX = guardX;
                int newY = guardY;
                if(guard == '^'){
                    newX = guardX - 1;
                } else if(guard == 'v'){
                    newX = guardX + 1;
                } else if(guard == '>'){
                    newY = guardY + 1;
                } else if(guard == '<'){
                    newY = guardY - 1;
                }

                if(newX < 0 || newY < 0 || newX >= depth || newY >= width){
                        guardExists = false;
                        //System.out.println("OOB at " + guardX + " " + guardY);
                        input[guardX][guardY] = 'X';
                        continue;
                }
                char newLoc = input[newX][newY];
                if(newLoc == '#'){
                    char newGuard = rotateGuard(guard);
                    input[guardX][guardY] = newGuard;
                    continue; // rotated the guard - no need to check for oob since we haven't moved anywhere
                } else {
                    if(input[newX][newY] != 'X'){
                        guardUniquePositions++;
                    }
                    input[guardX][guardY] = 'X'; //todo do we need this?
                    input[newX][newY] = guard;
                    guardX = newX;
                    guardY = newY;
                }
            }
            System.out.println("Guard steps are: " + guardUniquePositions);
            System.out.println("Obstacles are: " + obstacles);

            //System.out.println(Arrays.deepToString(input).replace("],", "\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkObstacleAtPoint(char[][] inputToCopy, int currentX, int currentY, char guard){

        char[][] input = Arrays.copyOf(inputToCopy, inputToCopy.length);
        char g = guard;
        ArrayList<String> pastBump = new ArrayList<>();
        int newX = currentX;
        int newY = currentY;
        input[getNewX(g, newX)][getNewY(g, newY)] = '#';
        System.out.println(Arrays.deepToString(input).replace("],", "\n").replaceAll(", ", ""));

        while (true){
            int[] result = getRayResult(input, newX, newY, g);
            if(result[0] == '-'){
                return false; //not a loop - we leave the board
            }

            if(result[0] == '#'){
                String bump = newY + "|" + newY+ "|" + g;
                if(pastBump.contains(bump)){ // loop!
                    System.out.println(bump);
                    System.out.println("---- Loop ----");
                    return true;
                }
                pastBump.add(bump);
                g = rotateGuard(g);
            }

            newX = getNewX(g, currentX);
            newY = getNewY(g, currentY);
        }
    }


    public static int[] getRayResult(char[][]input, int guardX, int guardY, char guard){
        //guard determines direction
        int width = input[0].length;
        int depth = input.length;
        int newX = guardX;
        int newY = guardY;
        int oldX = newX;
        int oldY = newY;
        while (true){
            newX = getNewX(guard, newX);
            newY = getNewY(guard, newY);
          //  System.out.println("New: " + newX + "|" + newY);

            // are we out of bounds? if so, return the last known position of our guard
            if (newX < 0 || newY < 0 || newX >= width || newY >= depth) {
                return new int[]{'-', guardX, guardY};
            }

            //did we hit a wall? if so, return the last known guard position
            if(input[newX][newY] == '#'){
              //  System.out.println("Dist: " + distanceTraveled);
                return new int[]{'#', oldX, oldY};
            }

            oldX = newX;
            oldY = newY;

        }

    }


    public static int getDeltaX(char guard){
        int x = 0;
        if(guard == '^'){
            x = -1;
        } else if(guard == 'v'){
            x = 1;
        }
        return x;
    }

    public static int getDeltaY(char guard){
        int y = 0;
        if(guard == '>'){
            y = 1;
        } else if(guard == '<'){
            y = -1;
        }
        return y;
    }

    public static int getNewX(char guard, int x){
        if(guard == '^'){
            x = x - 1;
        } else if(guard == 'v'){
            x = x + 1;
        }
        return x;
    }

    public static int getNewY(char guard, int y){
        if(guard == '>'){
            y = y + 1;
        } else if(guard == '<'){
            y = y - 1;
        }
        return y;
    }

    public static char rotateGuard(char c){
        return switch (c) {
            case 'v' -> '<';
            case '<' -> '^';
            case '^' -> '>';
            case '>' -> 'v';
            default -> 'g';
        };
    }


}
