public class Day3 extends Day {
    String[] input; // each element a line of the input file
    int lines; 
    int columns;
    
    Day3() {
        inputFile = "days/Day3/input3.txt";
        input = this.getInput().split("\n");
        lines = input.length;
        columns = input[0].length();
    }

    /**
     * count times the char on a slope of right 3 down 1
     * from the top left corner is the '#' symbol (a tree).
     * 
     * When you get to the rightmost char, wrap around to
     * the leftmost char and continue until you hit the
     * bottom row.
     */
    public void part1() {
        int trees = 0, x = 0, y = 0;

        //while(y < 12) // small tests
        while(y < lines - 1)
        {
            // go right
            if (x + 3 > columns - 1)
            {
                x = (x + 3) - columns;
            }
            else x += 3;
            
            //go down
            y++;

            //count
            //System.out.print(input[y].substring(x, x+1) + " ");
            trees += "#".equals(input[y].substring(x, x+1)) ? 1 : 0;
            

        }
        System.out.printf("trees=%d\n", trees);
    }

    /**
     * count times the char on various slopes
     * from the top left corner is the '#' symbol (a tree).
     * 
     * When you get to the rightmost char, wrap around to
     * the leftmost char and continue until you hit the
     * bottom row.
     * 
     * The slopes are (RD) 11, 31, 51, 71 and 12.
     * 
     * Find the product of the number of trees on each slope.
     * 
     * base code copied from part1()
     */
    public void part2() {
        int[] trees = {0,0,0,0,0};
        int[] x = {0,0,0,0,0};
        int[] y = {0,0,0,0,0};
        int[] dx = {1,3,5,7,1};
        int[] dy = {1,1,1,1,2};

        //while(y[0] < 6) // small tests
        while(y[0] < lines - 1)
        {
            for (int i = 0; i < 5; i++)
            {
                // go down <- moved above check to avoid ArrayIndexOutOfBounds error
                y[i] += dy[i];
                
                // do nothing if at bottom of hill
                if (y[i] >= lines) continue;
                
                // go right
                if (x[i] + dx[i] > columns - 1)
                {
                    x[i] = (x[i] + dx[i]) - columns;
                }
                else x[i] += dx[i];

                //count
                //System.out.print(input[y[i]].substring(x[i], x[i]+1) + " ");
                trees[i] += "#".equals(input[y[i]].substring(x[i], x[i]+1)) ? 1 : 0;
            }
            //System.out.println();
        }


        long treeProd = 1; //used int and it overflowed
        for(int i = 0; i<5; i++) 
        {
            treeProd *= trees[i];
            System.out.printf("Slope (R%dD%d): %d trees\n", dx[i], dy[i], trees[i]);
        }
        System.out.printf("treeProd=%d\n", treeProd);
    }
}
