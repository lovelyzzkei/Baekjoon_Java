import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_20924 {

    static int n, r, gigaNode;
    static ArrayList<ArrayList<Integer[]>> tree;
    static boolean[] visited;

    static int getColumn(int curr) {
        int numOfChild = 0;
        int nextNode = 0;
        int nextCost = 0;

        for (Integer[] next : tree.get(curr)) {
            int node = next[0];
            int cost = next[1];

            if (!visited[node]) {
                nextNode = node;
                nextCost = cost;
                numOfChild++;
            }
        }

        visited[curr] = true;
        if (numOfChild == 0 || numOfChild >= 2) {
            gigaNode = curr;
            return 0; 
        }
        else return nextCost + getColumn(nextNode);
    }


    static int getBranch(int curr) {
        visited[curr] = true;

        int dist = 0;
        for (Integer[] next : tree.get(curr)) {
            int nextNode = next[0];
            int nextCost = next[1];
            if (!visited[nextNode]) {
                dist = Math.max(dist, nextCost + getBranch(nextNode));
            }
        }

        return dist;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

       
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        r = Integer.parseInt(input[1]);

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }


        for (int i = 0; i < n-1; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);

            tree.get(a).add(new Integer[] {b, d});
            tree.get(b).add(new Integer[] {a, d});
        }

        visited = new boolean[n+1];
        int column = getColumn(r);
        int branch = getBranch(gigaNode);
        
        bw.write(column + " " + branch);        
        bw.flush();
        bw.close();

    }
}
