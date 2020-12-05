import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

public class Day5 extends Day {
    String[] lines;
    int[][] seats; // boarding pass, [row, column, id]
    Day5() {
        inputFile = "days/Day5/input5.txt";
        lines = getInput().split("\n");
        seats = new int[lines.length][3];
    }

    
    /** Recursive bit-shifting operation */
    int getSpot(String s, int sPos, int low, int high)
    {
        if (low == high) return low; // stop condition
        
        int shift = (high + 1 - low) / 2; // bit shift

        if (s.charAt(sPos) == 'B' || s.charAt(sPos) == 'R') low += shift;
        else high -= shift;
        return getSpot(s, sPos + 1, low, high);
    }

    /**
     * 
     */
    public void part1() {   
        int maxID = 1;
        int maxIDIndex = 0;
        
        for (int i = 0; i < lines.length; i++) {
            seats[i][0] = getSpot(lines[i], 0, 0, 127);
            seats[i][1] = getSpot(lines[i], 7, 0, 7);
            seats[i][2] = seats[i][0] * 8 + seats[i][1]; // row * 8 + column
            if (seats[i][2] >= maxID) 
            {
                maxID = seats[i][2];
                maxIDIndex = i;
            }
        }

        System.out.printf("Pass=%s, Row=%d, Column=%d, ID=%d\n", 
            lines[maxIDIndex], seats[maxIDIndex][0], seats[maxIDIndex][1], seats[maxIDIndex][2]);
    }

    /**
     * 
     */
    public void part2() {
        Map<Integer, Integer> mSeats = new HashMap<>();
        for (int[] s : seats) mSeats.put(s[2], s[2]); // map of IDs

        //System.out.println(128*8 - mSeats.size()); // worrying about empty seats
        //System.out.println((128*8 - mSeats.size())/8); // and corresponding # of rows
        for (int i = 0; i < 128*8; i++)
        {
            //int row = i / 8, column = i % 8;
            if (!mSeats.containsKey(i)) // returns only one result without skipping rows
            //if (!(mSeats.containsKey(i) && (row > 10 && row < 118)))
            {
                if (mSeats.containsKey(i-1) && mSeats.containsKey(i+1))
                {
                    System.out.println(i);
                }
            }
        }
    }
}