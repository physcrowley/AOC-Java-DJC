import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Data structure ideas.
 * Acyclic directed graph
 * HashMap K=colour, V=HashMap of contained backs (K=colour, V=quant)
 */
public class Day7 extends Day {
    String input;
    Map<String, Map<String, Integer>> bags;
    List<Integer> numBags;
    
    Day7() {
        inputFile = "days/Day7/input7.txt";
        input = getInput();
        bags = new HashMap<>();
        numBags = new ArrayList<>();
        makeASG(input, bags);
    }

    /**
     * Get data and put it into HashMaps for use in the challenges.
     * 
     * The outer HashMap is a bag:listOfContainedBags pairing
     * 
     * The inner HashMap is a bag:quantity pairing listing all bags contained within
     * a specific colour of bag.
     * 
     * The Strings for each colour-contains rule are all of the format
     * <colour> bags contain <number> <colour> bag[s][, <number> <colour> bag[s]]{0-}.
     */
    void makeASG(String s, Map m)
    {
        for (String line : s.split("\n"))
        {
            line = line.replaceAll("bag[s]*", ""); // remove meaningless word
            Scanner getRule = new Scanner(line);
            getRule.useDelimiter("contain"); // split key from values at this word
            String outer = getRule.next().replaceAll(" ", ""); // get key colour
            getRule.useDelimiter(","); // split contained colours by ,
            Map<String, Integer> inside = new HashMap<>();
            while(getRule.hasNext())
            {
                // fixer line... the 'contain' word wasn't flushed, and \n allows nextLine()
                String insideBag = getRule.next().replaceAll("contain", "")+"\n";
                Scanner getBags = new Scanner(insideBag);
                int quantity = 0; 
                String colour = "";
                if (getBags.hasNextInt()) // there is another colour
                {
                    quantity = getBags.nextInt();
                    colour = getBags.nextLine().replaceAll("[ .]*", "");
                }
                else // the "no other" bag has no quantity
                {
                    quantity = 0;
                    colour = getBags.nextLine().replaceAll("[ .]*", "");
                }
                getBags.close();
                inside.put(colour, quantity);
            }
            
            m.put(outer, inside);
            getRule.close();
        }
        //System.out.println(m);
    }
    
    /** Search bags to see if they contain the specified bag colour
     * 
     * Used for part 1
     * 
     * @param bag - the colour of interest
     * @param list - the HashMap that caches all bags containing the colour of interest
     */
    void getContainingBags(String bag, Map<String, Integer> list)
    {
        for (String b : bags.keySet()) // get all the bag colours
        {
            for(String insideBags : bags.get(b).keySet()) // see which colours each contains
            {
                if (insideBags.equals(bag)) // if it contains the colour of interest
                {
                    list.put(b, bags.get(b).get(bag)); // add it to the list
                    getContainingBags(b, list); // and see which bags contain this colour
                }
            }
        }
    }

    /** Counts all the bags contained in a single bag of one colour
     * 
     * Used for part 2
     * 
     * @param list is the list of bags that a specific bag contains, stored as colour:quantity pairs
     * @return the number of contained bags tallied
     *          This number is the quantity of this colour bag plus the same quantity times
     *          all the bags it contains, repeated for each colour bag contained in any level
     *          of the tree.
     */
    int getContainingBagCount(Map<String, Integer> list)
    {
        int toAdd = 0;
        for (String b : list.keySet())
        {
            if (b.equals("noother")) // contains no other (0)
            {
                toAdd = list.get(b);
            }
            else // add these bags and all the ones they contain
            {
                toAdd += list.get(b) * (1 + getContainingBagCount(bags.get(b)));
            }
        }
        return toAdd;
    }
    
    /**
     * How many bag colors can eventually contain at least one shiny gold bag?
     * 4 with test input
     */
    public void part1() {   
        String bag = "shinygold";
        Map<String, Integer> containingBags = new HashMap<>();

        getContainingBags(bag, containingBags);

        System.out.printf("Number of bags = %d\n", containingBags.size());
    }


    /**
     * How many individual bags are required inside your single shiny gold bag?
     * 32 with test input
     * 126 with test2 input
     */
    public void part2() {
        String bag = "shinygold";;
        int count = getContainingBagCount(bags.get(bag));        

        System.out.printf("Total = %d\n", count);
    }
}