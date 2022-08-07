import java.io.*;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;  


public class App {
     public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter fighter 1: ");
        String fighter1 = sc.nextLine();
        System.out.println("Enter fighter 2: ");
        String fighter2 = sc.nextLine();
        double fighterStats[] = new double[2];
        fighterStats[0] = getFighter1Details(fighter1);
        fighterStats[1] = getFighter2Details(fighter2);
        // run an alg to find max score possible out of all fighters -- present user with fighter's demon rating 
        double fighter1WinPct = (long) 0.0;
        double fighter2WinPct = (long) 0.0;
        if (fighterStats[0] > fighterStats[1]) {
            fighter1WinPct = (1 - (fighterStats[1])/(fighterStats[0])) * 100 + 50;
            fighter2WinPct = 100 - fighter1WinPct;
        } else {
            fighter2WinPct = (1 - (fighterStats[0])/(fighterStats[1])) * 100 + 50;
            fighter1WinPct = 100 - fighter2WinPct;
        }
        System.out.println(fighter1 + "'s Win Probability: " + Math.round(fighter1WinPct) + "%");
        System.out.println(fighter2 + "'s Win Probability: " + Math.round(fighter2WinPct) + "%");
        String winner = fighter1WinPct > fighter2WinPct ? fighter1 : fighter2;
        System.out.println("The winner of this fight is " + winner);

        
    }

    private static double getFighter1Details(String fighter1) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        double positives1 = 0;
        JSONArray fighters = (JSONArray) parser.parse(new FileReader("C:\\Users\\desim\\Desktop\\raw_fighter_details.json"));
        for (Object o : fighters) {
            JSONObject person = (JSONObject) o;
            String name = (String) person.get("fighter_name");
            if (name.equalsIgnoreCase(fighter1)) {
                String td_acc = (String) (person.get("TD_Acc"));
                String reach = (String) (person.get("Reach"));
                String td_def = (String) (person.get("TD_Def"));
                String str_acc = (String) (person.get("Str_Acc"));
                String str_def = (String) (person.get("Str_Def"));
                String height = (String) (person.get("Weight"));
                String weight = (String) (person.get("Weight"));
                Object td_avg = person.get("TD_Avg");
                Object sapm = person.get("SApM");
                Object slpm = person.get("SLpM");
                Object sub_avg = person.get("Sub_Avg");
                positives1 = longDoubleCaster(positives1, td_avg, sapm, slpm, sub_avg);
                positives1 += Double.parseDouble(td_acc) + Double.parseDouble(td_def) + Double.parseDouble(str_acc) + Double.parseDouble(str_def) + Double.parseDouble(weight) +
                    + Double.parseDouble(reach) + Double.parseDouble(height);
            }
        }
        return positives1;
    }

    private static double longDoubleCaster(double positives, Object td_avg, Object sapm, Object slpm, Object sub_avg) {
        if (td_avg instanceof Long) {
            positives += (long) td_avg;
        } else if (td_avg instanceof Double) {
            positives += (double) td_avg;
        }
        if (sapm instanceof Long) {
            positives += (long) sapm;
        } else if (sapm instanceof Double) {
            positives += (double) sapm;
        }
        if (slpm instanceof Long) {
            positives += (long) slpm;
        } else if (slpm instanceof Double) {
            positives += (double) slpm;
        }
        if (sub_avg instanceof Long) {
            positives += (long) sub_avg;
        } else if (sub_avg instanceof Double) {
            positives += (double) sub_avg;
        }
        return positives;
    }

    private static double getFighter2Details(String fighter2) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        double positives2 = 0;
        JSONArray fighters = (JSONArray) parser.parse(new FileReader("C:\\Users\\desim\\Desktop\\raw_fighter_details.json"));
        for (Object o : fighters) {
            JSONObject person = (JSONObject) o;
            String name = (String) person.get("fighter_name");
            if (name.equalsIgnoreCase(fighter2)) {
                String td_acc = (String) (person.get("TD_Acc"));
                String reach = (String) (person.get("Reach"));
                String td_def = (String) (person.get("TD_Def"));
                String str_acc = (String) (person.get("Str_Acc"));
                String str_def = (String) (person.get("Str_Def"));
                String height = (String) (person.get("Weight"));
                String weight = (String) (person.get("Weight"));
                Object td_avg = person.get("TD_Avg");
                Object sapm = person.get("SApM");
                Object slpm = person.get("SLpM");
                Object sub_avg = person.get("Sub_Avg");
                positives2 = longDoubleCaster(positives2, td_avg, sapm, slpm, sub_avg);
                positives2 += Double.parseDouble(td_acc) + Double.parseDouble(td_def) + Double.parseDouble(str_acc) + Double.parseDouble(str_def) + Double.parseDouble(weight) +
                     + Double.parseDouble(reach) + Double.parseDouble(height);
            }
        }
    return positives2;
    }
}
