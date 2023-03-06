import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_8979 {    

    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>(){
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[1] > o2[1]) {
                    return -1;
                } else if (o1[1] == o2[1]) {
                    if (o1[2] > o2[2]) {
                        return -1;
                    } else if (o1[2] == o2[2]) {
                        if (o1[3] > o2[3]) {
                            return -1;
                        } else if (o1[3] < o2[3]) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < n; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        }

        Integer[] first = pq.poll();

        int rank = 1;
        int gold = first[1];
        int silver = first[2];
        int bronze = first[3];
        while (true) {
            Integer[] curr = pq.poll();

            rank++;
            if (gold == curr[1] && silver == curr[2] && bronze == curr[3]) {
                rank--;
            }
            else {
                gold = curr[1];
                silver = curr[2];
                bronze = curr[3];
            }

            if (k == curr[0]) break;
        }
        
        bw.write(rank + "");
        bw.flush();
        bw.close();

    }
}
