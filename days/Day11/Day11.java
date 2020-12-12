import java.util.Arrays;

public class Day11 extends Day {
    String[] input;
    int width;
    int height;
    char[][] spots;
    
    Day11() {
        //cache input
        inputFile = "days/Day11/test-input11.txt";
        //inputFile = "days/Day11/input11.txt";
        input = getInput().split("\n");
        height = input.length;
        width = input[0].length();
        spots = new char[height][width];
        for (int i=0; i < input.length; i++) spots[i] = input[i].toCharArray();
    }

    /**
    Find how many seats are occupied once the arrangement is stable using these rules )
    1> is empty && all adjacent are empty -> becomes occupied
    2> is occupied && 4+ are occupied -> becomes empty
    3> otherwise, no change

    Presume floor counts as a constant empty

    test-input11 -> stabilizes at 37 seats after 6 loops

    ***** ACTUALLY HARDER TO MANAGE THAN THE PYTHON LISTS!!! ****
    I thought char arrays would be more well-behaved, but they seem to have
    the same issues :
     > assignment issues (values versus objects)
     > dependancies of values within a table (changing one item modifies multiple linked items)
     */
    public void part1() 
    {
        boolean first = true;
        int loops = 0;
        // linked objects, cannot change one without affecting the other
        char[][] nextSpots = spots;
        /* // seemingly independant items -> algo doesn't converge
        char[][] nextSpots = new char[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) nextSpots[i][j] = '.';
        }
        */
        /* // another seemingly independant declaration -> algo also hangs
        char[][] nextSpots = new char[height][width];
        for (int i = 0; i < height; i++){
            nextSpots[i] = Arrays.copyOf(spots[i], width);
        }
        */

        while (nextSpots != spots || first)
        {
            if (!first)
            {
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++) spots[i][j] = nextSpots[i][j]; // assigning items, not arrays (pointers to objects)
                }
            }
            first = false;
            loops++;

            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    // skip floor spots
                    if (spots[i][j] == '.') continue;

                    // scan occupied seats
                    int localOccupied = 0;
                    if (i > 0 && j > 0 && spots[i-1][j-1] == '#') localOccupied++;
                    if (i > 0 && spots[i-1][j] == '#') localOccupied++;
                    if (i > 0 && j < width - 1 && spots[i-1][j+1] == '#') localOccupied++;
                    if (j < width - 1 && spots[i][j+1] == '#') localOccupied++;
                    if (i < height - 1 && j < width - 1 && spots[i+1][j+1] == '#') localOccupied++;
                    if (i < height - 1 && spots[i+1][j] == '#') localOccupied++;
                    if (i < height - 1 && j > 0 && spots[i+1][j-1] == '#') localOccupied++;
                    if (j > 0 && spots[i][j-1] == '#') localOccupied++;

                    // apply rules to the seat
                    if (spots[i][j] == 'L' && localOccupied == 0) nextSpots[i][j] = '#';
                    else if (localOccupied >= 4) nextSpots[i][j] = 'L';
                    else nextSpots[i][j] = spots[i][j];
                }
            }
        }
        
        int occupied = 0;
        for (char[] r : nextSpots)
        {
            for (char c : r) occupied += c == '#' ? 1 : 0;
        }
        System.out.printf("%d loops, %d occupied seats\n", loops, occupied);
    }


    /**
     * 
     */
    public void part2() {
        
        System.out.printf("\n");
    }
}