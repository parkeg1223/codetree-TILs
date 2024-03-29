import java.util.*;
import java.io.*;

public class Main {

    static class Meeting {
        String code;
        char point;
        int time;

        Meeting(String code, char point, int time) {
            this.code = code;
            this.point = point;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Meeting m = new Meeting(
            st.nextToken(),
            st.nextToken().charAt(0),
            Integer.parseInt(st.nextToken())
        );

        System.out.println("secret code : " + m.code);
        System.out.println("meeting point : " + m.point);
        System.out.println("time : " + m.time);
    }
}