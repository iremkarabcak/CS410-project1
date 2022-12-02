//İREM KARABACAKOGULLARINDAN S018220 CS410 PROJECT 1
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\irem_karabacakogullarindan_s018220\\src\\NFA1.txt");
        //File file= new File("C:\\irem_karabacakogullarindan_s018220\\src\\NFA2.txt");


        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        while (line != null) {
            listOfStrings.add(line);
            line = br.readLine();
        }
        br.close();

        ArrayList<String> states = new ArrayList<>();
        ArrayList<String> alphabet = new ArrayList<>();
        ArrayList<String> transitions = new ArrayList<>();
        String startState = "";
        ArrayList<String> finalStates = new ArrayList<>();

        int read_state = 0;
        for (String s : listOfStrings) {
            if (s.equals("ALPHABET")) {
                read_state = 1;
                continue;
            } else if (s.equals("STATES")) {
                read_state = 2;
                continue;
            } else if (s.equals("FINAL")) {
                read_state = 3;
                continue;
            } else if (s.equals("TRANSITIONS")) {
                read_state = 4;
                continue;
            } else if (s.equals("START")) {
                read_state = 5;
                continue;
            } else if (s.equals("END")) {
                continue;
            }

            if (read_state == 1) {
                alphabet.add(s);
            } else if (read_state == 2) {
                states.add(s);
            } else if (read_state == 3) {
                finalStates.add(s);
            } else if (read_state == 4) {
                transitions.add(s);
            } else if (read_state == 5) {
                startState = s;
            }
        }

        ArrayList<String> dfa_transitions = new ArrayList<>();
        ArrayList<String> states_to_visit = new ArrayList<>();

        for (String alp : alphabet) {
            String new_final_state = "";
            for (String transition : transitions) {
                String[] transition_start_state = transition.split(" ");
                if (transition_start_state[1].equals(alp) && transition_start_state[0].equals(startState)) {
                    new_final_state += transition_start_state[2];
                }
            }
            String dfa_transition = startState + " " + alp + " " + new_final_state;
            dfa_transitions.add(dfa_transition);
            states_to_visit.add(new_final_state);
        }
        for (String state : states_to_visit) {
            for (String dfa_transition_ : dfa_transitions) {
                String[] dfa_transition_start_state = dfa_transition_.split(" ");
                if (dfa_transition_start_state[0].equals(state)) {
                    states_to_visit.remove(state);
                }
            }
        }

        while(true){
            String state = states_to_visit.get(0);
            if (state.length() == 1) {
                for (String alp : alphabet) {
                    String new_final_state = "";
                    for (String transition : transitions) {
                        String[] transition_start_state = transition.split(" ");
                        if (transition_start_state[1].equals(alp) && transition_start_state[0].equals(state)) {
                            new_final_state += transition_start_state[2];
                        }
                    }
                    String dfa_transition = state + " " + alp + " " + new_final_state;
                    dfa_transitions.add(dfa_transition);
                    boolean found = false;
                    for(String state_to_visit: states_to_visit){
                        if(state_to_visit.equals(new_final_state))found=true;
                    }
                    if(!found)states_to_visit.add(new_final_state);
                }
                break;
            } else {
                String[] nfa_states = state.split("");
                for (String alp : alphabet) {
                    String new_final_state = "";
                    for (String nfa_state : nfa_states) {
                        for (String transition : transitions) {
                            String[] transition_start_state = transition.split(" ");
                            if (transition_start_state[1].equals(alp) && transition_start_state[0].equals(nfa_state)) {
                                if(!new_final_state.contains(transition_start_state[2]))
                                    new_final_state += transition_start_state[2];
                            }
                        }
                    }
                    String dfa_transition = state + " " + alp + " " + new_final_state;
                    dfa_transitions.add(dfa_transition);
                    boolean found = false;
                    for(String state_to_visit: states_to_visit){
                        if(state_to_visit.equals(new_final_state))found=true;
                    }
                    if(!found)states_to_visit.add(new_final_state);
                }
            }
            states_to_visit.remove(state);
            for(int i = 0; i < states_to_visit.size(); i++) {
                String state_ = states_to_visit.get(i);
                for (String dfa_transition_ : dfa_transitions) {
                    String[] dfa_transition_start_state = dfa_transition_.split(" ");
                    if (dfa_transition_start_state[0].equals(state_)) {
                        states_to_visit.remove(state_);
                    }
                }
            }
            if(states_to_visit.size() == 0)break;
        }
        System.out.println("ALPHABET \n"+alphabet);
        System.out.println("STATES \n"+states);
        System.out.println("START\n"+startState);
        System.out.println("FINAL \n"+finalStates);
        System.out.println("TRANSITIONS\n"+ dfa_transitions);
    }
}
