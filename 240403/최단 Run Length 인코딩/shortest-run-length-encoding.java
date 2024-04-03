import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = in.readLine();

        char c = s.charAt(s.length() - 1);
        int n = 0;
        if (s.charAt(0) == c) {
            n = 1;
            for (int i = 1; i < s.length() && s.charAt(i) == c; i++) n++;
            s = s.substring(n, s.length()) + s.substring(0, n);
        }

        c = s.charAt(0);
        n = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                n++;
            } else {
                sb.append(c).append(n);
                c = s.charAt(i);
                n = 1;
            }
        }
        sb.append(c).append(n);

        System.out.println(sb.toString().length());
        
    }
}