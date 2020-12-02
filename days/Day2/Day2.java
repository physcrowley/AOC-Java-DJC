import java.io.*;
import java.util.Scanner;

public class Day2 extends Day {
    Day2() {
        inputFile = "days/Day2/input2.txt";
    }

    
    private Scanner openScanner()
    {
        try 
        {
            File f = new File("days/Day2/input2.txt"); // using this.inputFile wasn't working
            if (!f.exists()) 
            {
                System.out.println(String.format("%s not found", "days/Day2/input2.txt"));
                return null;
            }
            return new Scanner(f);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    
    /**
     * find number of valid passwords according to the policy.
     * input structure (each line)
     * x-y a: pwd
     * 
     * x is the minimum number of times letter a must appear in pwd
     * y is the maximum number of times the letter must appear in pwd
     */
    public void part1() {
        int validPWDs = 0;
        Scanner lineEater = openScanner();
        lineEater.useDelimiter("[-: \n]+"); // characters used to separate "next"

            /* It is also possible to use this on a String, with the split() method
            *   ex. : String[] s = line.split("[-: \n]+");
            *   returning an array like {"1", "3", "x", "xxwxxxk"}
            *   Then you can use Integer.parseInt(s[0]), s[3].toCharArray(), etc. to extract values
            */
            
        //for(int i = 0; i < 10; i++) // test code on first 10 elements
        while (lineEater.hasNextLine())
        {
            int min = lineEater.nextInt();
            int max = lineEater.nextInt();
            char key = lineEater.next().toCharArray()[0];
            char[] pwd = lineEater.next().toCharArray();
            if (lineEater.hasNextLine()) lineEater.nextLine();
            
            int reps = 0;
            for (char c : pwd)
            {
                if (c == key) reps++;
            }
            if (reps >= min && reps <= max) validPWDs++;
        }
        lineEater.close();

        System.out.printf("valid: %d\n", validPWDs);
    }

    /**
     * find number of valid passwords according to the policy.
     * input structure (each line):
     * x-y a: pwd
     * 
     * x is the 1st position 
     * y is the 2nd position 
     * 
     * a must appear exactly once in pwd at locations x and y 
     * 
     * All code copied from part1() -> only the logic for counting
     * valid pwds was changed
     * 
     */
    public void part2() {
        int validPWDs = 0;
        Scanner lineEater = openScanner();
        lineEater.useDelimiter("[-: \n]+"); // characters used to separate "next"
        //for(int i = 0; i < 10; i++) // test code on first 10 elements
        while (lineEater.hasNextLine())
        {
            int min = lineEater.nextInt();
            int max = lineEater.nextInt();
            char key = lineEater.next().toCharArray()[0];
            char[] pwd = lineEater.next().toCharArray();
            if (lineEater.hasNextLine()) lineEater.nextLine();
            
            int reps = 0;
            if (pwd[min - 1] == key) reps++;
            if (max - 1 <= pwd.length)
            {
                if (pwd[max - 1] == key) reps++;
            }
            if (reps == 1) 
            {
                validPWDs++;
                //System.out.println(pwd); // for initial error checking
            }
        }
        lineEater.close();

        System.out.printf("valid: %d\n", validPWDs);
    }
}
