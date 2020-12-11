# Lessons learned

## Day 1

It's super important to know how to read files and parse text

## Day 3, 4, 5

Data structures simplify algorithms... it is handy to be familiar with their methods.

## Day 6

A logical error on file input (splitting input into String[] entries by '\n' and defining groups as being followed by a blank line '\n' made it so the last group was never tabulated) cost me a lot of time and head-scratching because the algorithm SHOULD have been working (it was).

This taught me that using a small test input file (the examples in each problem) instead of only iterating over partial input could have helped me see the problem sooner.

### Youtube -> Reducible

Just watched the Dynamic programming episode and it seems to apply directly to Day 7's first problem. Acyclical directed graphs for the relationships between bag colours.

## Day 7

Setting up the data structure was the hardest part -> parsing the text correctly into the nested HashMaps.

The second hardest part was just putting down the math in code without making mistakes when considering the data structures and recursion.

Using paper and pencil to sketch out and manually go through the examples was the most effective way of figuring out the algorithm.

## Day 8

This problem, part 2, was more of a logic problem -> realising only the negative jumps needed to be looked at.

The rest was finding out how to test changing each jump in succession without messing up the other instructions by accident. I had a few experiments with going backwards and forwards through a stack before just giving up and iterating from the beginning with each new possible change. The stack idea would have required caching indices and the acc value for different change points... maybe another day

## Day 9

I am probably too tired... I can see the relationships but can't easily translate it to code

## Day 10 - switched to Python

I don't want to explore data structures and homemade sorting algorithms... I need to write code faster so I am going to use Python... worked amazingly.
