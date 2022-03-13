import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class pb_1759 {

    // 모음을 딕셔너리로 저장해놓음
    static HashMap<String, Boolean> vowel = new HashMap<String, Boolean>(){{
        put("a", true);
        put("e", true);
        put("i", true);
        put("o", true);
        put("u", true);
    }};



    // L : 뽑아야하는 문자의 개수
    // picked : 지금까지 뽑은 문자를 담아놓은 리스트
    // nextIdx : 증가순으로 뽑을 때 다음에 뽑아야하는 문자
    // S : 입력받은 문자를 담은 array
    static void pick(int L, ArrayList<String> picked, int nextIdx, String[] S){

        if (picked.size() == L) {

            // L개를 모두 뽑았을 경우 해당 리스트 내에 모음이 있는지 확인
            // 모음이 하나도 없으면 그냥 스킵
            int numVowel = 0;
            int numConsonant = 0;
            for (String s : picked) {
                if (vowel.get(s) == null) {
                    numConsonant += 1;
                    continue;
                }
                else {
                    numVowel += 1;
                }
            };

            // 모음이 적어도 하나, 자음이 적어도 둘이 있으면 출력
            if (numVowel != 0 && numConsonant >= 2) {
                for (String s : picked) {
                    System.out.print(s);
                }
                System.out.println();
            }
            return;
        }

        for (int i = nextIdx; i < S.length; i++) {
            String next = S[i];
            picked.add(next);
            pick(L, picked, i+1, S);
            picked.remove(picked.size()-1);
        }

    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // L, C와 S를 입력받음
        String input[] = br.readLine().split(" ");
        int L = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        String[] S = br.readLine().split(" ");
        Arrays.sort(S);
        ArrayList<String> pw = new ArrayList<>();

        // 재귀로 돌리면서 확인
        pick(L, pw, 0, S);

    }
}
