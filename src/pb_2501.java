import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class pb_2501 {
    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 사용자에게 두 개의 자연수 N과 K를 입력받아 계산을 위해 먼저 정수로 바꿔주고
        String[] input = bf.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);


        // 1부터 N까지 모든 수로 N을 나눠보면서 약수를 확인, 새로운 어레이에 저장을 해놓음
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

            // 만약 n이 i로 나뉘어지면 i는 n의 약수 -> 저장하자
            if (n % i == 0) {
                list.add(i);
            }

        }
        

        // 출력
        // 만약 N의 약수의 개수가 k보다 작으면 0을 출력
        if (list.size() < k){
            bw.write("0" + "\n");
        } else {
            bw.write(list.get(k-1) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
