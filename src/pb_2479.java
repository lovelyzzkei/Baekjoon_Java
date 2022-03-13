import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2479 {    

    static int n, k;
    static ArrayList<String> nthBinaryCode;
    static ArrayList<ArrayList<String>> binaryCode;
    static HashMap<String, Boolean> visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Get n, k
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        /* Initialize ArrayList binaryCode to store the input code by the number of 1 */
        binaryCode = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            binaryCode.add(new ArrayList<String>());
        }

        nthBinaryCode = new ArrayList<String>();
        nthBinaryCode.add("");

        // Get each code and store in binaryCode 
        // Sort each input by the number of 1 using Integer.bitCount(i)
        for (int i = 0; i < n; i++) {
            String code     = br.readLine();
            int numOfOneBit = Integer.bitCount(Integer.parseInt(code, 2));

            binaryCode.get(numOfOneBit).add(code);
            nthBinaryCode.add(code);
        }

        // Get start and end number of code
        input = br.readLine().split(" ");
        String start = nthBinaryCode.get(Integer.parseInt(input[0]));
        String end   = nthBinaryCode.get(Integer.parseInt(input[1]));
        

        // Initialize queue for BFS : {code, number of code}
        Deque<String[]> q = new ArrayDeque<>();
        q.add(new String[] {start, Integer.parseInt(input[0])+" "});

        // Initialize visited as HashMap
        visited = new HashMap<>();
        visited.put(start, true);

        // Start searching
        String ret = "";
        boolean isArrived = false;

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {

                String[] currState = q.pop();
                String currCode = currState[0];
                String currPath = currState[1];

                if (currCode.equals(end)) {
                    ret = currPath;
                    isArrived = true;
                    break;
                }

                int numOfOneBit = Integer.bitCount(Integer.parseInt(currCode, 2));
                for (int one = numOfOneBit-1; one <= numOfOneBit+1; one++) {
                    if (one < 0 || one > k) continue;
                    for (String next : binaryCode.get(one)) {

                        int currToBinary = Integer.parseInt(currCode, 2);
                        int nextToBinary = Integer.parseInt(next, 2);
                        int xor = nextToBinary^currToBinary;
                        // System.out.println(currToBinary + " " + nextToBinary + " " + xor);

                        if (Integer.bitCount(xor) == 1 && visited.get(next) == null) {
                            visited.put(next, true);
                            q.add(new String[] {next, currPath + nthBinaryCode.indexOf(next) + " "});
                        }
                    }
                }


            }

            if (isArrived) break;
        }

        bw.write((isArrived ? ret : -1+""));

        
        bw.flush();
        bw.close();

    }
}

