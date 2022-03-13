import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3015 { 


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        if (n != 1) {

            Deque<Integer> stack = new ArrayDeque<>();
            stack.add(height[0]);
            stack.add(height[1]);
            
            cnt = 1;
            for (int i = 2; i < n; i++) {
                int top = stack.removeLast();
                int curr = height[i];
                
                // top이 curr보다 크면 top 이전의 수들과 curr은 쌍을 만들 수 없음
                // 따라서 이전의 수들 모두 제거
                 if (top > curr) {
                    while (!stack.isEmpty()) {
                        stack.removeLast();
                    }
                    stack.add(top);
                    stack.add(curr);
                    cnt += 1;
                } 
                
                // 반대로 top이 curr보다 작으면 curr은 top과 스택의 맨 밑의 수와 짝을 만들 수 있음
                // 중간의 수들과는 불가능 
                else if (top < curr) {
                    stack.add(curr);
                    cnt += 2;
                } 
                
                // top과 같은 수이면 계산만 하고 스택에 넣지는 않음
                else {
                    cnt += 2;
                }
    
            }
        }
    

        
        bw.write(cnt + "");
        bw.flush();
        bw.close();

    }
}
