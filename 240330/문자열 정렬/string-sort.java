import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] c = in.readLine().toCharArray();
        Arrays.sort(c);
        System.out.println(new String(c));
    }
}