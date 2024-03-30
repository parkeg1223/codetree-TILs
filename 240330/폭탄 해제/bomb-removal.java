import java.io.*;
import java.util.*;

class Bomb {
    String code;
    char color;
    int sec;

    public Bomb(String code, char color, int sec) {
        this.code = code;
        this.color = color;
        this.sec = sec;
    }

    public void getInfo() {
        System.out.println("code : " + this.code);
        System.out.println("color : " + this.color);
        System.out.println("second : " + this.sec);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Bomb b = new Bomb(
            st.nextToken(),
            st.nextToken().charAt(0),
            Integer.parseInt(st.nextToken())
        );
        b.getInfo();
    }
}