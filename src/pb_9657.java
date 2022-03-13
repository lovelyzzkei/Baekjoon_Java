import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_9657 {

    static int n;
    static boolean[][] cache;

    static boolean takeStones(int numOfStones, int person) {
        if (numOfStones == 0) return cache[numOfStones][person] = true;
        if (numOfStones < 0) return cache[numOfStones][person] = false;
        if (cache[numOfStones][person]) return cache[numOfStones][person];
        return cache[numOfStones][person] = (takeStones(numOfStones-1, (person+1)%2) 
        || takeStones(numOfStones-3, (person+1)%2) 
        || takeStones(numOfStones-4, (person+1)%2));
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        
        cache = new boolean[n+1][2];
        for(boolean[] arr : cache) {
            Arrays.fill(arr, false);
        }
        

        boolean ret = takeStones(n, 0);

        for (boolean[] arr : cache) {
            System.out.println(Arrays.toString(arr));
        }
        
        bw.write((cache[0][0]) ? "SK" : "CY");
        bw.flush();
        bw.close();

    }
}
