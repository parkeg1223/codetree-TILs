import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        char[] c1 = in.readLine().toCharArray();
        char[] c2 = in.readLine().toCharArray();

        if (c1.length != c2.length) {
            System.out.println("No");
            return;
        }

        Arrays.sort(c1);
        Arrays.sort(c2);

        String s1 = new String(c1);
        String s2 = new String(c2);

        if (s1.equals(s2)) System.out.println("Yes");
        else System.out.println("No");
    }
}