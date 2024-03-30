import java.io.*;
import java.util.*;

class User {
    String name, addr, city;

    public User() {}
    public User(String name, String addr, String city) {
        this.name = name;
        this.addr = addr;
        this.city = city;
    }

    public void getInfo() {
        System.out.println("name " + this.name);
        System.out.println("addr " + this.addr);
        System.out.println("city " + this.city);
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        StringTokenizer st;
        User[] users = new User[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            users[i] = new User(
                st.nextToken(), st.nextToken(), st.nextToken()
            );
        }

        User latest = users[0];
        for (int i = 1; i < n; i++) {
            if (latest.name.compareTo(users[i].name) < 0) latest = users[i];
        }

        latest.getInfo();
    }
}