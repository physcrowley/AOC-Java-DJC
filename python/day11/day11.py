#input_file = "test-input11.txt"
#input_file = "python/day11/test-input11.txt"
input_file = "input11.txt"

"""
Cellular automata
"""
width = 0
height = 0
with open(input_file) as file:
    
    raw = file.read().split("\n")
    width = len(raw[0])
    height = len(raw)
    spots = []
    for row in raw:
        spots.append(list(row)) # manage strings as lists

#for r in spots: print(r)

def part1(spots):
    """ 
    Find how many seats are occupied once the arrangement is stable using these
    rules :
    1> is empty and all adjacent are empty -> becomes occupied
    2> is occupied and 4+ are occupied -> becomes empty
    3> otherwise, no change

    Presume floor counts as a constant empty

    test-input11 -> stabilizes at 37 seats
    """
    first = True
    loops = 0
    # need to declare and fill with each element completely 
    # independantly from other lists - including other rows
    # because assignment links to the same object in memory and 
    # changing one changes all the others that are linked
    next_spots = []
    for i in range(height):
        next_spots.append([])
        for j in range(width): next_spots[i].append('.')
    

    while next_spots != spots:
        if not first:
            for i in range(height):
                for j in range(width): spots[i][j] = next_spots[i][j]
        first = False
        loops += 1

        for i in range(height):
            for j in range(width):
                # skip floor spots
                if spots[i][j] == '.': continue
                
                # get empty seats
                local_occupied = 0
                if i > 0 and j > 0 and spots[i-1][j-1] == '#': 
                    local_occupied += 1
                if i > 0 and spots[i-1][j] == '#':
                    local_occupied += 1
                if i > 0 and j < width - 1 and spots[i-1][j+1] == '#': 
                    local_occupied += 1
                if j < width - 1 and spots[i][j+1] == '#': 
                    local_occupied += 1
                if i < height - 1 and j < width - 1 and spots[i+1][j+1] == '#': 
                    local_occupied += 1
                if i < height - 1 and spots[i+1][j] == '#': 
                    local_occupied += 1
                if i < height - 1 and j > 0 and spots[i+1][j-1] == '#': 
                    local_occupied += 1
                if j > 0 and spots[i][j-1] == '#': 
                    local_occupied += 1
                
                # apply rules to seat
                if spots[i][j] == 'L' and local_occupied == 0: next_spots[i][j] = '#'
                elif local_occupied >= 4: next_spots[i][j] = 'L'
                else: next_spots[i][j] = spots[i][j]

        
    occupied = 0
    for  r in next_spots: 
        for c in r:
            if c == '#': occupied += 1
    
    print(f"{loops} loops, {occupied} occupied seats")


    


def part2():
    """
    """
    pass


if __name__ == "__main__":
    part1(spots)
    part2()