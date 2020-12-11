#input = "test-input10b.txt"
input = "input10.txt"

with open(input) as file:
    
    raw = file.read().split("\n")
    nums = []
    for n in raw:
        nums.append(int(n))

def part1():
    """ 
    String "adapters together" in increasing order and tabulate differences.

    Multiply number of 1-differences with number of 3-differences.

    Last adapter always counts as a 3-difference

    test-input10 -> 7 1s and 5 3s => 35
    test-input10b -> 22 1s and 10 3s => 220
    """

    nums.sort()

    diffs = [0, 0, 0]

    diff = nums[0] # diff from ground (0)
    diffs[diff - 1] += 1

    for i in range(len(nums) - 1):
        diff = nums[i + 1] - nums[i]
        diffs[diff - 1] += 1
    diffs[2] += 1 # for the last one which is always a diff of 3

    print(diffs[0] * diffs[2])

def part2():
    """
    """
    pass


if __name__ == "__main__":
    part1()
    part2()