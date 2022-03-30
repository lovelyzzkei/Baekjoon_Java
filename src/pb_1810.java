import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1810 {

    static int n, f;
    static HashMap<String, Double> stones;
    static final double INF = 1_000_001;

    static double distance(int y, int x, int ny, int nx) {
        return (Math.sqrt(Math.pow(y-ny, 2) + Math.pow(x-nx, 2)));
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        f = Integer.parseInt(input[1]);

        stones = new HashMap<>();
        stones.put("0 0", 0.0);   // 시작점

        for (int i = 0; i < n; i++) {
            stones.put(br.readLine().strip(), INF);
        }

        PriorityQueue<Double[]> pq = new PriorityQueue<Double[]>(new Comparator<Double[]>() {
            @Override
            public int compare(Double[] o1, Double[] o2) {
                return o1[0] < o2[0] ? -1 : 1;
            }
        });


        pq.add(new Double[] {0.0, 0.0, 0.0}); // [거리, x, y]


        long ret = -1;

        while (!pq.isEmpty()) {
            Double[] curr = pq.poll();
            double dist = curr[0];
            double x = curr[1];
            double y = curr[2];


            if ((int) y == f) {
                ret = Math.round(dist);
                break;
            }

            for (int dy = -2; dy <= 2; dy++) {
                for (int dx = -2; dx <= 2; dx++) {
                    int ny = (int) y + dy;
                    int nx = (int) x + dx;
                    String next = nx + " " + ny;

                    if (stones.get(next) != null) {
                        double cost = distance((int)y, (int)x, ny, nx);
                        if (dist + cost < stones.get(next)){
                            stones.put(next, dist + cost);
                            pq.add(new Double[] {dist+cost, (double) nx, (double) ny});
                        }
                    }
                }
            }

            // for (Double[] d : pq) {
            //     System.out.print(Arrays.toString(d) + " ");
            // }
            // System.out.println();
        }


        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
