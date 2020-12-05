import java.util.HashMap;
import java.util.Map;

public class Day5 extends Day {
    String[] lines;
    int[][] seats; // boarding pass, [row, column, id]
    Day5() {
        inputFile = "days/Day5/input5.txt";
        lines = getInput().split("\n");
        seats = new int[lines.length][3];
    }


    /** Recursive "bit-shifting" operation */
    int getSpot(String s, int sPos, int low, int high)
    {
        if (low == high) return low; // stop condition
        
        int shift = (high + 1 - low) / 2; // half the interval

        // update low or high value depending on character
        if (s.charAt(sPos) == 'B' || s.charAt(sPos) == 'R') low += shift;
        else high -= shift;
        
        // advance character and call with updated low-high combo
        return getSpot(s, sPos + 1, low, high);
    }


    /**
     * determine highest seat ID among provided boarding passes
     * 
     * Passes in format rrrrrrccc with binary seat ID specification
     * > each r is either 'F' or 'B' indicating front or back half
     *      of remaining range (initial is 0-127, 2^6) of rows
     * > each c is either 'L' or 'R' indicating front or back half
     *      of remaining range (initial is 0-7, 2^3) of columns
     * > seat ID is 8 * row + column
     * 
     */
    public void part1() {   
        int maxID = 1;
        int maxIDIndex = 0;
        
        for (int i = 0; i < lines.length; i++) {
            seats[i][0] = getSpot(lines[i], 0, 0, 127); // get row
            seats[i][1] = getSpot(lines[i], 7, 0, 7); // get column
            seats[i][2] = seats[i][0] * 8 + seats[i][1]; // row * 8 + column
            if (seats[i][2] >= maxID) 
            {
                maxID = seats[i][2]; // store maxID
                maxIDIndex = i;
            }
        }

        System.out.printf("Pass=%s, Row=%d, Column=%d, ID=%d\n", 
            lines[maxIDIndex], seats[maxIDIndex][0], seats[maxIDIndex][1], seats[maxIDIndex][2]);
    }


    /**
     * find my missing seat ID from boarding pass list
     * 
     * Info:
     * first and last rows are not full
     * my seat ID has neighbours (ID+1 and ID-1) in the pass list
     * 
     */
    public void part2() {
        Map<Integer, Integer> mSeats = new HashMap<>(); // for easier searching than an array
        for (int[] s : seats) mSeats.put(s[2], s[2]); // map of available IDs

        //System.out.println(128*8 - mSeats.size()); // worrying about empty seats
        //System.out.println((128*8 - mSeats.size())/8); // and corresponding # of rows
        
        // go through all possible seat IDs
        for (int i = 0; i < 128*8; i++)
        {
            //int row = i / 8; // useful if the rows actually had an impact
            
            // if empty
            if (!mSeats.containsKey(i)) // returns only one result even without skipping rows
            
            // if empty and not in first or last rows
            //if (!(mSeats.containsKey(i) && (row > 10 && row < 118)))
            {
                // and has neighbours
                if (mSeats.containsKey(i-1) && mSeats.containsKey(i+1))
                {
                    System.out.println(i);
                }
            }
        }
    }
}