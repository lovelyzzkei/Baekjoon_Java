import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class pb_1059 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());
        int[] S = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> listS = new ArrayList<>();
        listS.add(0);   // S의 원소들이 모두 n보다 클 수도 있으므로 하한선을 정해줌
        for (int s: S) {
            listS.add(s);
        }

        
        // 오름차순으로 정렬
        listS.sort(Comparator.naturalOrder());


        // S에서 n이 포함될 수 있는 구간을 구함
        int idx = -1;
        for (int i = 0; i < listS.size() - 1; i++){
            if (listS.get(i) < n && n < listS.get(i+1)) {
                idx = i;
                break;
            }
        }

        // 여기서 idx가 -1이면 n이 포함될 수 있는 구간이 없다는 것 -> 0
        int cnt = 0;
        if (idx != -1) {
            // 그렇지 않으면 (n-첫값) * (끝값-n) - 1
            cnt = (n - listS.get(idx)) * (listS.get(idx+1) - n) - 1;
        }

        bw.write(String.format("%d", cnt));
        bw.flush();
        bw.close();
    }
}
