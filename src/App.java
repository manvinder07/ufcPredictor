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
        double fighter1WinPct = 0.0;
        double fighter2WinPct = 0.0;
        if (fighterStats[0] > fighterStats[1]) {
            fighter1WinPct = (1 - (fighterStats[1])/(fighterStats[0])) * 100 + 50;
            fighter2WinPct = 100 - fighter1WinPct;
        } else {
            fighter2WinPct = (1 - (fighterStats[0])/(fighterStats[1])) * 100 + 50;
            fighter1WinPct = 100 - fighter2WinPct;
        }
        System.out.println(fighter1 + "'s Win Probability: " + Math.round(fighter1WinPct) + "%");
        System.out.println(fighter2 + "'s Win Probability: " + Math.round(fighter2WinPct) + "%");

        
    }

    private static double getFighter1Details(String fighter1) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        double positives1 = 0;
        JSONArray fighters = (JSONArray) parser.parse(new FileReader("C:\\Users\\desim\\Desktop\\raw_fighter_details.json"));
        for (Object o : fighters) {
            JSONObject person = (JSONObject) o;
            String name = (String) person.get("fighter_name");
            if (name.equals(fighter1)) {
                String td_acc = (String) (person.get("TD_Acc"));
                td_acc = td_acc.replaceAll("%", "");
                String reach = (String) (person.get("Reach"));
                reach = reach.replaceAll("\"", "");
                String td_def = (String) (person.get("TD_Def"));
                td_def = td_def.replaceAll("%", "");
                String str_acc = (String) (person.get("Str_Acc"));
                str_acc = str_acc.replaceAll("%", "");
                String str_def = (String) (person.get("Str_Def"));
                str_def = str_def.replaceAll("%", "");
                String height = (String) (person.get("Weight"));
                height= height.replaceAll("\\s+", "").replaceAll("'", "").replaceAll("\"", "");
                String weight = (String) (person.get("Weight"));
                weight = weight.replaceAll(" lbs.", "");
                long sub_avgL = 0;
                double sub_avgD = 0.0;
                if (person.get("Sub_Avg") instanceof Long) {
                    sub_avgL = (long) person.get("Sub_Avg");
                }
                if (person.get("Sub_Avg") instanceof Double) {
                    sub_avgD = (double) person.get("Sub_Avg");
                }
                positives1 = (double) person.get("TD_Avg") + (double) person.get("SApM") + (double) person.get("SLpM");
                positives1 += Double.parseDouble(td_acc) + Double.parseDouble(td_def) + Double.parseDouble(str_acc) + Double.parseDouble(str_def) + Double.parseDouble(weight) +
                    (sub_avgD) + (double) (sub_avgL) + Double.parseDouble(weight) + Double.parseDouble(reach);
            }
            // add reach
        }
        return positives1;
    }

    private static double getFighter2Details(String fighter2) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        double positives2 = 0;
        JSONArray fighters = (JSONArray) parser.parse(new FileReader("C:\\Users\\desim\\Desktop\\raw_fighter_details.json"));
        for (Object o : fighters) {
            JSONObject person = (JSONObject) o;
            String name = (String) person.get("fighter_name");
            if (name.equals(fighter2)) {
                String td_acc = (String) (person.get("TD_Acc"));
                td_acc = td_acc.replaceAll("%", "");
                String reach = (String) (person.get("Reach"));
                reach = reach.replaceAll("\"", "");
                String td_def = (String) (person.get("TD_Def"));
                td_def = td_def.replaceAll("%", "");
                String str_acc = (String) (person.get("Str_Acc"));
                str_acc = str_acc.replaceAll("%", "");
                String str_def = (String) (person.get("Str_Def"));
                str_def = str_def.replaceAll("%", "");
                String height = (String) (person.get("Weight"));
                height= height.replaceAll("\\s+", "").replaceAll("'", "").replaceAll("\"", "");
                String weight = (String) (person.get("Weight"));
                weight = weight.replaceAll(" lbs.", "");
                long sub_avgL = 0;
                double sub_avgD = 0.0;
                if (person.get("Sub_Avg") instanceof Long) {
                    sub_avgL = (long) person.get("Sub_Avg");
                }
                if (person.get("Sub_Avg") instanceof Double) {
                    sub_avgD = (double) person.get("Sub_Avg");
                }
                positives2 = (double) person.get("TD_Avg") + (double) person.get("SApM") + (double) person.get("SLpM");
                positives2 += Double.parseDouble(td_acc) + Double.parseDouble(td_def) + Double.parseDouble(str_acc) + Double.parseDouble(str_def) + Double.parseDouble(weight) +
                    (sub_avgD) + (double) (sub_avgL) + Double.parseDouble(weight) + Double.parseDouble(reach);
        }
    }
    return positives2;
    }
}
