import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_17387 {    

    static final int INF = 1_000_001;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double[] tmp1 = Stream.of(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double[] tmp2 = Stream.of(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        Double[][] l1 = new Double[][] {new Double[]{tmp1[0], tmp1[1]}, new Double[]{tmp1[2], tmp1[3]}};
        Double[][] l2 = new Double[][] {new Double[]{tmp2[0], tmp2[1]}, new Double[]{tmp2[2], tmp2[3]}};

        Arrays.sort(l1, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Double.compare(o1[1], o2[1]);
            } else {
                return Double.compare(o1[0], o2[0]);
            }
        });
        Arrays.sort(l2, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Double.compare(o1[1], o2[1]);
            } else {
                return Double.compare(o1[0], o2[0]);
            }
        });



        double x1 = l1[0][0]; double y1 = l1[0][1];
        double x2 = l1[1][0]; double y2 = l1[1][1];
        double x3 = l2[0][0]; double y3 = l2[0][1];
        double x4 = l2[1][0]; double y4 = l2[1][1];

        
        // 끝점이 같은 선분 -> 1
        boolean isInterSect = false;

        if (x2 < x3 || x4 < x1) {
            isInterSect = false;
        }

        else if (Arrays.deepEquals(l1[0], l2[0]) || Arrays.deepEquals(l1[0], l2[1]) || Arrays.deepEquals(l1[1], l2[0]) || Arrays.deepEquals(l1[1], l2[1])) {
            isInterSect = true;
        }

        // 선분 하나가 x=a 형태의 직선이면 다른 선분 하나가 x=a에서 그 선분 사이의 y값을 갖는지 확인
        // 먼저 둘다 x=a 형태인 경우 x가 같지 않으면 절대 불가 + y범위
        else if (x1 == x2 && x3 == x4) {
            if (x1 == x3 && !(y4 < y1 || y3 > y2)) {
                isInterSect = true;
            }
        }

        // l1이 x=a 형태의 선분인 경우
        else if (x1 == x2) {

            // L2 선분
            double grad = ((double) (y4 - y3)) / (x4 - x3);
            double y = grad * (x1 - x3) + y3;
            if ((double) y1 <= y && y <= (double) y2) {
                isInterSect = true;
            }
        }

        // L2가 x=a 형태의 선분인 경우
        else if (x3 == x4) {

            // L1 선분
            double grad = (double) (y2 - y1) / (x2 - x1);
            double y = grad * (x3 - x1) + y1;
            if ((double) y3 <= y && y <= (double) y4) {
                isInterSect = true;
            } 
        }


        // l1, l2 모두 식으로 나타낼 수 있는 경우
        // 교점이 존재할 수 있는 x 범위를 for문으로 돌리면서
        // 두 식에서 y값의 차이가 부호가 바뀌면 교점이 존재
        else {

            double grad1 = (double) (y2 - y1) / (double) (x2 - x1);
            double grad2 = (double) (y4 - y3) / (double) (x4 - x3);

            int startX = (int) Math.max(x1, x3);
            int endX = (int) Math.min(x2, x4);

            for (int x = startX; x < endX; x++) {
                double valueOfL1 = grad1 * (x-x1) + y1;
                double valueOfL2 = grad2 * (x-x3) + y3;
                double diff = (valueOfL1 - valueOfL2);

                double nextValueOfL1 = grad1 * ((x+1)-x1) + y1;
                double nextValueOfL2 = grad2 * ((x+1)-x3) + y3;
                double nextDiff = (nextValueOfL1 - nextValueOfL2);

                // 차이의 부호가 바뀌면 <0, 차이가 0인경우는 교점인 경우 =0
                if (diff * nextDiff <= (double) 0) {
                    isInterSect = true;
                    break;
                }
            }
        }




        bw.write((isInterSect ? 1 : 0) + "");
        bw.flush();
        bw.close();

    }
}

