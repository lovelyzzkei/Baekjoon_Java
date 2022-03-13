import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2812 {  




    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String num = br.readLine();
        char[] numToArr = num.toCharArray();
        int cnt = 0;
        Deque<Character> stack = new ArrayDeque<>();

        
        stack = new ArrayDeque<>();
        stack.add(numToArr[0]);

        for (int i = 1; i < num.length(); i++) {

            // 스택의 맨 위에 있는 것이 현재 수보다 작으면 
            // 현재 수보다 커질때까지 pop, 이때 그 횟수를 k까지
            if (stack.getLast() < numToArr[i]) {
                while (cnt < k && !stack.isEmpty() && stack.getLast() < numToArr[i]) {
                    stack.removeLast();
                    cnt++;
                }
            }
            stack.add(numToArr[i]);
        }

        // 아직 더 지워야되면 이번에는 뒤에서부터 지우기
        if (cnt < k) {
            StringBuffer ret = new StringBuffer("");
            while (cnt < k) {
                char top = stack.removeLast();
                if (top > stack.getLast()) {
                    ret.append(top);
                } else {
                    cnt++;
                }
            }

            while (!stack.isEmpty()) {
                ret.append(stack.removeLast());
            }
            bw.write(ret.reverse().toString());
        } else {
            for (Character c : stack) {
                bw.write(c+"");
            }
        }
        

        bw.flush();
        bw.close();

    }
}
