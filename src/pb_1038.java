import java.io.*;
import java.util.*;

public class pb_1038 {

    static char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static ArrayList<Character> picked = new ArrayList<>();
    static int n;
    static int cnt = 0;
    static String ans = "";
    static boolean findAns = false;

    static void makeDecNum(int len, int startIdx) {

        if (picked.size() == len) {

            if (cnt == n) {

                for (int i = 0; i < picked.size(); i++) {
                    ans += picked.get(i);
                }
                findAns = true;

            } else {
                cnt += 1;
            }

            return;
        }

        for (int i = 0; i <= startIdx; i++) {

            picked.add(nums[i]);
            makeDecNum(len, i - 1);

            if (findAns) {
                return;
            }

            picked.remove(picked.size()-1);
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        
        for (int len = 1; len <= 10; len++) {

            // 각 길이마다 감소하는 수를 찾음
           makeDecNum(len, 9);     
           if (findAns) {
               break;
           }
        }

        if (n == 0) {
            ans = "0";
        } else if (!findAns) {
            ans = "-1";
        }
        System.out.println(ans);
        String url = "www.codechobo.com";
        System.out.printf("[%.8s]%n", url);
        bw.flush();
        bw.close();

    }
}
