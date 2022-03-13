import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

public class quadtree {

    static String reverse(CharacterIterator it) {
        char head = it.current();
        it.next();
        if (head == 'b' || head == 'w') {
            return Character.toString(head);
        }
        String upperLeft = reverse(it);
        String upperRight = reverse(it);
        String lowerLeft = reverse(it);
        String lowerRight = reverse(it);
        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String QT = "xxwbxwwxbbwwbwbxwbwwxwwwxbbwb";
        CharacterIterator it = new StringCharacterIterator(QT);
        String reversedQT = reverse(it);

        System.out.println(reversedQT);



        bw.flush();
        bw.close();

    }
}
