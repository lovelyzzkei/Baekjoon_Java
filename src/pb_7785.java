import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_7785 {    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Boolean> log = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            String name = input[0];
            String userLog = input[1];

            if (userLog.equals("enter")) {
                log.put(name, true);
            } else {
                log.put(name, false);
            }
        }

        ArrayList<String> enterPerson = new ArrayList<>();
        for (String s : log.keySet()) {
            if (log.get(s)) {
                enterPerson.add(s);
            }
        }

        Collections.sort(enterPerson, Collections.reverseOrder());
        for (String s : enterPerson) {
            System.out.println(s);
        }
        
        bw.flush();
        bw.close();

    }
}
