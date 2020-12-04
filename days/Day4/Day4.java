import java.util.HashMap;
import java.util.Map;


public class Day4 extends Day {
    /** used in part1 to build the passports HashMap */
    String[] lines;

    /** built in part1, reused in part2 */
    Map<Integer, Map<String, String>> passports = new HashMap<>();

    Day4() {
        inputFile = "days/Day4/input4.txt";
        lines = getInput().split("\n\n"); //empty lines separate records
    }

    /**
     * find all valid passports, ignoring passports missing only the cid field
     */
    public void part1() { 
        int valid = 0;  
        
        //for (int i = 0; i < 5; i++) // initial testing
        for (int i = 0; i < lines.length; i++) 
        {
            // separate record into entries ( \n between entries, : between K and V)
            String[] entries = lines[i].split("[: \n]+");

            // hashmap for each record
            Map<String, String> record = new HashMap<>();
            for (int j = 0; j < entries.length - 1; j += 2)
            {
                record.put(entries[j], entries[j+1]); // successive entries are K and V
            }
            // add record to passports HashMap
            passports.put(i, record);
            // count valid passports
            if (passports.get(i).size() == 8) valid++; //all fields
            else if(passports.get(i).size() == 7 && 
                    !passports.get(i).containsKey("cid")) valid++; //all fields except cid
        }
        System.out.printf("valid passports = %d\n", valid);
    }

    /**
     * as in part1, but with conditions on each passport field
     */
    public void part2() {
        int valid = 0;
        for (int i = 0; i < passports.size(); i++)
        {
            Map<String, String> record = passports.get(i);
            
            //basic check
            if ( !(record.size()==8 || (record.size()==7 && !record.containsKey("cid"))) )
            {
                continue;
            }

            //detailled check
            // byr
            int byr = Integer.parseInt(record.get("byr"));
            if(byr < 1920 || byr > 2002) continue;

            // iyr
            int iyr = Integer.parseInt(record.get("iyr"));
            if(iyr < 2010 || iyr > 2020) continue;

            // eyr
            int eyr = Integer.parseInt(record.get("eyr"));
            if(eyr < 2020 || eyr > 2030) continue;

            // hgt
            String sHgt = record.get("hgt");
            String units = sHgt.substring(sHgt.length() - 2);
            if ( !(units.equals("cm") || units.equals("in")) ) continue;
            int iHgt = Integer.parseInt(sHgt.substring(0, sHgt.length() - 2));
            switch(units)
            {
                case "in": 
                    if(iHgt < 59 || iHgt > 76) continue;
                    break;
                case "cm":
                    if(iHgt < 150 || iHgt > 193) continue;
            }

            // hcl
            String hcl = record.get("hcl");
            if(hcl.length() != 7) continue;
            if(!hcl.matches("[#][0-9a-f]{6}")) continue;

            // ecl
            String ecl = record.get("ecl");
            boolean goodEcl = false;
            for(String c : new String[] {"amb","blu","brn","gry","grn","hzl","oth"})
            {
                goodEcl = ecl.equals(c);
                if (goodEcl) break;
            }
            if (!goodEcl) continue;

            //pid
            String pid = record.get("pid");
            if(pid.length() != 9) continue;
            if(!pid.matches("[0-9]{9}")) continue;
            
            // if loop hasn't been "continued" -> valid
            valid++;
        }
        System.out.printf("valid after detailed check = %d\n", valid);
    }
}