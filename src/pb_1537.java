import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class pb_1537 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 

        // 입력 받기
        String s = br.readLine();
        String input[] = s.split(" ");
        String p = input[0];
        int k = Integer.parseInt(input[1]);

        // 에라토스테네스 채로 k보다 작은 소수들 구하기
        // 배열 초기화가 false로 되기에 소수를 false, 그렇지 않은 수들을 true로 저장
        boolean prime[] = new boolean[k+1];
        prime[0] = prime[1] = true;
        int key = 10000001;

        // 2부터 시작해서 소수의 배수들을 모두 지움 -> 소수를 판별
        for (int i = 2; i*i <= k; i++) {
            if (!prime[i]) {
                for (int j = i*i; j <= k; j+=i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 2; i <= k; i++) {
            if (!prime[i]) {
                // 그 소수를 가지고 p가 나뉘어지는지 확인
                // 여기서 사용하는 것은 유클리드 호제법을 응용한 방법
                int num = 0;
                for (int j = 0; j < p.length(); j++) {
                    num = (num * 10 + (p.charAt(j) - '0')) % i;
                }

                if (num == 0) {
                    key = i;
                    break;
                }
            }
        }

        if (key < k) {
            bw.write(String.format("BAD %d", key));
        } else {
            bw.write(String.format("GOOD"));
        }


        bw.flush();
        bw.close();
    }  
}
