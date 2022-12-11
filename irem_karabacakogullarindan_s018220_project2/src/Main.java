//irem karabacakogullarından s018220 cs410 project 2
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.sun.jmx.snmp.ThreadContext.contains;

public class Main {

    private static Object List;
    private static Object String;

    public static <rules> void main(String[] args) throws IOException {

        File file = new File("C:\\irem_karabacakogullarindan_s018220_project2\\src\\G1.txt");
        //File file = new File("C:\\irem_karabacakogullarindan_s018220_project2\\src\\G2.txt");

        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        int lineLength = line.length();

        while (line != null) {
            listOfStrings.add(line);
            line = br.readLine();
        }
        br.close();

        ArrayList<String> non_terminal = new ArrayList<>();
        ArrayList<String> terminal = new ArrayList<>();
        ArrayList<String> rules = new ArrayList<>();
        String startState = "";
        ArrayList<String> finalStates = new ArrayList<>();
        // Map<String, List<String>> nonTerminalListMap(List<String> rules)= new Map<>();

        int read_state = 0;
        for (String s : listOfStrings) {
            if (s.equals("NON-TERMINAL")) {
                read_state = 1;
                continue;
            } else if (s.equals("TERMINAL")) {
                read_state = 2;
                continue;
            } else if (s.equals("RULES")) {
                read_state = 3;
                continue;
            } else if (s.equals("START")) {
                read_state = 4;
                continue;
            }

            if (read_state == 1) {
                non_terminal.add(s);
            } else if (read_state == 2) {
                terminal.add(s);
            } else if (read_state == 3) {
                rules.add(s);
            } else if (read_state == 4) {
                startState = s;
            }
        }
        System.out.println(listOfStrings);


        ArrayList<String> rulesToRead = new ArrayList<>();
        ArrayList<String> rulesToRemove = new ArrayList<>();
        List<String> sublist_A = rules.subList(0, 1); //rules başı

        String replaceCharacter = " ";


        for (int i = 0; i < lineLength; i++) ;
        {
            assert line != null;
            if (line.substring(line.lastIndexOf(":") + 1).equals("e")) { //after the : symbol, we look for the epsilon

                for (int j = 0; j < lineLength; j++) {
                    if (line.substring(line.lastIndexOf(":") + 1).contains(line.substring(0, 1))) {
                        String fitting = line.substring(line.lastIndexOf(":") + 1).replace(line, "");
                        String fittingNew = fitting.replace("", "");
                    }
                }
                br.close();
            }

            /*
            boolean leftovers = (line.substring(line.lastIndexOf(":") + 1).contains(line.substring(0, 1)));
            String leftovers2 = new Boolean(leftovers).toString();
            if (leftovers2.length() > 2) {

            }
          private static Map<String, List<String>> nonterminalListMap(ArrayList<String> rules){

        }


            Map<String, List<String>> nonterminalsListMap = new HashMap<>() {
                List<List<Integer>> leftovers = rules(":", 3);
               String leftovers = rules.t(":",2);

                }
            }



            */
        }
    }
}
