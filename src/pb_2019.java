import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2019 { 

    static int n;
    static HashMap<Integer[], ArrayList<Integer[]>> segments;
    static HashMap<Integer[], Boolean> visited, finished;
    static int[] ret = new int[61];

    static int calcPolygon(Integer[] curr, Integer[] last) {
        if (curr.equals(last)) {
            System.out.println("return: " + Arrays.toString(curr) + " " + Arrays.toString(last));
            return 0;
        }
        int size = 1;
        for (Integer[] next : segments.get(curr)) {
            if (!next.equals(last) && visited.get(next) && !finished.get(next)) {
                System.out.println(Arrays.toString(curr) + " " + Arrays.toString(next));
                size += calcPolygon(next, last);

            }

        }
        return size;
    }

    static void dfs(Integer[] curr, Integer[] prev) {
        visited.put(curr, true);
        for (Integer[] next : segments.get(curr)) {
            System.out.println("curr: " + Arrays.toString(curr) + " next: " + Arrays.toString(next));

            for (Integer[] key : visited.keySet()) {
                System.out.print(Arrays.toString(key) + " " + visited.get(key));
            }
            System.out.println();

            if (!next.equals(prev)) {
                if (visited.get(next) && !finished.get(next)) {
                    System.out.println(Arrays.toString(curr) + " " + Arrays.toString(next));
                    int size = calcPolygon(next, curr);
                    ret[size]++;
                }
                else {
                    dfs(next, curr);
                }
            } 
            
        }
        finished.put(curr, true);
        
    } 


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 선분의 정보를 입력받음
        n = Integer.parseInt(br.readLine());
        segments = new HashMap<>();
        visited = new HashMap<>();
        finished = new HashMap<>();


        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            Integer[] dot1 = new Integer[] {Integer.parseInt(input[0]), Integer.parseInt(input[1])};
            Integer[] dot2 = new Integer[] {Integer.parseInt(input[2]), Integer.parseInt(input[3])};

            if (segments.get(dot1) == null) {
                segments.put(dot1, new ArrayList<>());
            }
            if (segments.get(dot2) == null) {
                segments.put(dot2, new ArrayList<>());
            }

            segments.get(dot1).add(dot2);
            segments.get(dot2).add(dot1);

            if (!visited.containsKey(dot1)) {
                visited.put(dot1, false);
            }
            if (!visited.containsKey(dot2)) {
                visited.put(dot2, false);
            }
            
            finished.put(dot1, false);
            finished.put(dot2, false);
        }

        System.out.println(segments.toString());


        // 좌표에 있는 모든 점들에 대하여 dfs를 수행하며 다각형의 가짓수를 구함
        
        for (Integer[] dot : segments.keySet()) {
            if (!visited.get(dot)) {
                dfs(dot, new Integer[] {-1, -1});

               
            }
        }

        System.out.println(Arrays.toString(ret));


        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
