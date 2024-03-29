import java.util.*;
import java.io.*;

class User {
    String id;
    int lv;

    public User() {}

    public User(String id, int lv) {
        this.id = id;
        this.lv = lv;
    }

    public void getInfo() {
        System.out.println("user " + this.id + " lv " + this.lv);
    }
};

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        User u1 = new User("codetree", 10);
        User u2 = new User();
        u2.id = st.nextToken();
        u2.lv = Integer.parseInt(st.nextToken());

        u1.getInfo();
        u2.getInfo();
    }
}