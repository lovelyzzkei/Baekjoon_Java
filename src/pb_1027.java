import java.io.*;
import java.util.stream.Stream;


public class pb_1027 {

    static int ans = 0;
    static int N;
    static int cache[];
    static double inc[];
    static double build[];


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        build = Stream.of(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        // 2. 각 막대에 대해 왼쪽과 오른쪽을 확인
        // 해당 막대와 각 막대 사이의 일차함수 식을 세우고, 그 사이의 있는 빌딩들이 모두 그 아래에 있다면 값을 증가
        for (int rod = 0; rod < N; rod++) {

            int max_l = 0;
            int max_r = 0;
            
            // 왼쪽 확인
            for (int left = 0; left < rod; left++) {
                double inc = (build[rod] - build[left]) / (rod - left);
                boolean possible = true;

                // 사이에 있는 빌딩들의 높이 확인
                for (int btw = left + 1; btw < rod; btw++) {
                    double maxHeight = inc * (btw-rod) + build[rod];
                    if (build[btw] >= maxHeight){
                        possible = false;
                        break;
                    }
                }

                if (possible) {
                    max_l += 1;
                }
            }

            // 오른쪽 확인
            for (int right = rod + 1; right < N; right++) {
                double inc = (build[rod] - build[right]) / (rod - right);
                boolean possible = true;

                // 사이에 있는 빌딩들 확인
                for (int btw = rod + 1; btw < right; btw++) {
                    double maxHeight = inc * (btw-rod) + build[rod];
                    if (build[btw] >= maxHeight){
                        possible = false;
                        break;
                    }
                }

                if (possible) {
                    max_r += 1;
                }
            }
            ans = Math.max(ans, max_l + max_r);
        }
        
        bw.write(String.format("%d", ans));
        bw.flush();
        bw.close();

       
    }
}
