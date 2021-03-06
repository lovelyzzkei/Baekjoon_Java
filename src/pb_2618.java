import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2618 {

    static int n, w;
    static int[] caseX, caseY, caseCop;
    static HashMap<Integer, Integer> cache;

    static int moveCops(int first, int second, int event) {
        String key = first + " " + second + " " + event;
        if (event == w+1) return 0;
        if (cache.get(event) != null) return cache.get(event);

        int firstToEvent = (first == 0) 
        ? Math.abs((caseX[event] - 1)) + Math.abs((caseY[event] - 1)) 
        : Math.abs((caseX[event] - caseX[first])) + Math.abs((caseY[event] - caseY[first]));
        int secondToEvent = (second == 0) 
        ? Math.abs((caseX[event] - n)) + Math.abs((caseY[event] - n)) 
        : Math.abs((caseX[event] - caseX[second])) + Math.abs((caseY[event] - caseY[second]));
        
        int moveFirst = firstToEvent + moveCops(event, second, event + 1);
        int moveSecond = secondToEvent + moveCops(first, event, event + 1);
        
        if (moveFirst <= moveSecond) {
            cache.put(event, moveFirst);
            caseCop[event] = 1;
            return moveFirst;
        } else {
            cache.put(event, moveSecond);
            caseCop[event] = 2;
            return moveSecond;
        }



        // for (int[][] arr : cache) {
        //     for (int[] tmp : arr) {
        //         System.out.print(Arrays.toString(tmp) + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());

        caseX = new int[w+1];
        caseY = new int[w+1];
        caseCop = new int[w+1];

        for (int i = 1; i <= w; i++) {
            String[] input = br.readLine().split(" ");
            caseX[i] = Integer.parseInt(input[0]);
            caseY[i] = Integer.parseInt(input[1]);
        }


        cache = new HashMap<>();
        // for (int i = 0; i <= w+1; i++) {
        //     cache.add(new HashMap<String, Integer>());
        // }


        int ret = moveCops(0, 0, 1);       
        bw.write(ret + "\n");

        for (int i = 1; i <= w; i++) {
            bw.write(caseCop[i] + "\n");
        }

        bw.flush();
        bw.close();

    }
}
