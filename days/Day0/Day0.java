import java.util.List;
import java.util.LinkedList;

public class Day0 extends Day {
    Day0() {
        inputFile = "days/Day0/input0.txt";
    }

    private List<Integer> sumOfSquaresList = new LinkedList<Integer>();
    /**
     * 
     */
    public void part1() {
        String input = this.getInput();
        //System.out.println(input);
        //for (byte c : input.getBytes()) System.out.println(c); //found CRLF line endings -> changed to LF
        int sumOfSquares = 0;
        /* // original
        for (String line : input.split("\n")) {
            int num = Integer.parseInt(line);
            sumOfSquares += num * num;
        }
        */
        
        // /* // with cache for part 2
        int i = 0;
        for (String line : input.split("\n")) {
            int num = Integer.parseInt(line);
            this.sumOfSquaresList.add(num * num);
            sumOfSquares += this.sumOfSquaresList.get(i);
            i++;
        }
        //System.out.println(sumOfSquaresList);
        // */


        System.out.println(String.format("Sum of squares: %d", sumOfSquares));
    }

    /**
     * 
     */
    public void part2() {
        String input = this.getInput();
        int sumOfCubes = 0;
        /* // original
        for (String line : input.split("\n")) {
            int num = Integer.parseInt(line);
            sumOfCubes += num * num * num;
        }
        */

        // /* // with cache from part1
        int i = 0;
        for (String line : input.split("\n")) {
            int num = Integer.parseInt(line);
            sumOfCubes += this.sumOfSquaresList.get(i) * num;
            i++;
        }
        // */

        System.out.println(String.format("Sum of cubes: %d", sumOfCubes));
    }
}