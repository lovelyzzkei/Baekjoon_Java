import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1039 {    

    static char[] currArr;
    static final int INF = 99999;

    static String swap(int i, int j) {
        char[] copyArr = new char[currArr.length];
        System.arraycopy(currArr, 0, copyArr, 0, currArr.length);
        char tmp = copyArr[i];
        copyArr[i] = copyArr[j];
        copyArr[j] = tmp;
        return String.valueOf(copyArr);
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<String> q = new ArrayDeque<>();

        String[] input = br.readLine().split(" ");
        String n = input[0];
        int k = Integer.parseInt(input[1]);

        q.add(n);

        int cnt = 0;
        int maxNumber = 0;

        while (!q.isEmpty() && cnt < k) {

            int qSize = q.size();
            HashMap<String, Boolean> visited = new HashMap<>();

            for (int t = 0; t < qSize; t++) {
                String curr = q.pop();

                // 각 자리를 스와핑하며 현재 단계에서 수가 중복되지 않게 큐에 삽입
                int length = curr.length();
                currArr = curr.toCharArray();

                for (int i = 0; i < length; i++) {
                    for (int j = i+1; j < length; j++) {
                        
                        // 첫번째 자리가 0이 되면 안됨
                        if (i == 0 && currArr[j] == '0') continue;
        
                        String swapNumber = swap(i, j);
                        if (visited.get(swapNumber) == null) {
                            visited.put(swapNumber, true);
                            q.add(swapNumber);
                        }
                        
                    }
                }
            }
            
            cnt++;
        }

        if (q.isEmpty()) {
            bw.write(-1 + "");
        }

        else if (cnt == k) {
            while (!q.isEmpty()) {
                String curr = q.pop();
                if (Integer.parseInt(curr) > maxNumber) {
                    maxNumber = Integer.parseInt(curr);
                }
            }
            bw.write(maxNumber + "");
        }

        bw.flush();
        bw.close();

    }
}

