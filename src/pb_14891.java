import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14891 {

    static int k;
    static ArrayList<Integer[]> circles;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        circles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] inputArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            circles.add(Arrays.stream(inputArr).boxed().toArray(Integer[]::new));
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            int numQueue = Integer.parseInt(input[0]) - 1;
            int dir = Integer.parseInt(input[1]);
            
            boolean isRotate = true;

            while (true) {
                if (!isRotate) break;

                Integer[] currCircle = circles.get(numQueue);
                int currLeft = currCircle[2];
                int currRight = currCircle[6];

                if (dir == 1) {
                    int last = currCircle[7];
                    System.arraycopy(currCircle, 0, currCircle, 1, 7);
                    currCircle[0] = last;
                } else {
                    int first = currCircle[0];
                    System.arraycopy(currCircle, 1, currCircle, 0, 7);
                    currCircle[7] = first;
                }

                if (numQueue == 0) {
                    Integer[] nextCircle = circles.get(numQueue + 1);
                    int nextLeft = nextCircle[2];
                    int nextRight = nextCircle[6];

                    if (nextLeft != currRight) {
                        isRotate = false;
                    }
                } else if (numQueue == 3) {
                    Integer[] nextCircle = circles.get(numQueue - 1);
                    int nextLeft = nextCircle[2];
                    int nextRight = nextCircle[6];

                    if (nextRight != currLeft) {
                        isRotate = false;
                    }
                } else {
                    Integer[] leftCircle = circles.get(numQueue - 1);
                    Integer[] rightCircle = circles.get(numQueue + 1);

                    int leftLeft = leftCircle[2];
                    int leftRight = leftCircle[6];

                    int rightLeft = rightCircle[2];
                    int rightRight = rightCircle[6];

                    if (leftRight != currLeft || rightLeft != currRight) {
                        isRotate = false;
                    }
                }
            }
            


        }
       
        bw.flush();
        bw.close();

    }
}
