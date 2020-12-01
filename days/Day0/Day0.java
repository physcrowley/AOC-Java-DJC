public class Day0 extends Day {
    Day0() {
        inputFile = "days/Day0/input0.txt";
    }

    public void part1() {
        String input = this.getInput();
        int sumOfSquares = 0;
        for (String line : input.split("\n")) {
            int num = Integer.parseInt(line);
            sumOfSquares += num * num;
        }
        System.out.println(String.format("Sum of squares: %d", sumOfSquares));
    }

    public void part2() {
        String input = this.getInput();
        int sumOfCubes = 0;
        for (String line : input.split("\n")) {
            int num = Integer.parseInt(line);
            sumOfCubes += num * num * num;
        }
        System.out.println(String.format("Sum of cubes: %d", sumOfCubes));
    }
}