import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int h, w;

    public Person() {}
    public Person(String name, int h, int w) {
        this.name = name;
        this.h = h;
        this.w = w;
    }

    public void getInfo() {
        System.out.println(this.name + " " + this.h + " " + this.w);
    }

    @Override
    public int compareTo(Person p) {
        if (this.h == p.h) {
            return p.w - this.w;
        }
        return this.h - p.h;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        StringTokenizer st;
        Person[] ps = new Person[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            ps[i] = new Person(
                st.nextToken(),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );

        }

        Arrays.sort(ps);
        for (int i = 0; i < n; i++) {
            ps[i].getInfo();
        }
    }
}