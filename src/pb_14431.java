import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14431 {

    static int x1, y1, x2, y2;
    static int n;
    static boolean[] prime;
    static HashMap<String, Integer> dist;
    static final int INF = 10000;

    static double uclidean(int x, int y, int nx, int ny) {
        return (Math.sqrt(Math.pow(x-nx, 2)+Math.pow(y-ny,2)));
    }

    static boolean isDouble(double cost) {
        System.out.println(cost + " " + (cost != (int) cost));
        return (cost != (int) cost);
    }

    static void calcPrime() {
        prime = new boolean[INF+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int i = 2; i * i <= INF; i++) {
            if (prime[i]) {
                for (int j = i*i; j <= INF; j += i) {
                    prime[j] = false;
                }
            }
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        x1 = Integer.parseInt(input[0]);
        y1 = Integer.parseInt(input[1]);
        x2 = Integer.parseInt(input[2]);
        y2 = Integer.parseInt(input[3]);

        dist = new HashMap<>();
        dist.put(x1+" "+y1, 0);
        dist.put(x2+" "+y2, INF);

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            dist.put(br.readLine(), INF);
        }

        calcPrime();

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b)->(a[0] - b[0]));
        pq.add(new Integer[] {0, x1, y1});

        int ret = -1;
        while (!pq.isEmpty()) {
            Integer[] curr = pq.poll();
            int d = curr[0];
            int x = curr[1];
            int y = curr[2];

            if (x == x2 && y == y2) {
                ret = d;
                break;
            }

            if (dist.get(x+" "+y) < d) continue;
            for (String next : dist.keySet()) {
                input = next.split(" ");
                int nx = Integer.parseInt(input[0]);
                int ny = Integer.parseInt(input[1]);
                double cost = uclidean(x, y, nx, ny);
                int costInt = (int) Math.floor(cost);

                if (prime[costInt]) {
                    if (d + costInt < dist.get(next)) {
                        dist.put(next, d + costInt);
                        pq.add(new Integer[] {d + costInt, nx, ny});
                    }
                }
            }

            // for (Integer[] i : pq) {
            //     System.out.print(Arrays.toString(i) + " ");
            // }
            // System.out.println();

        }
        if (ret == 0) ret = -1;
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
