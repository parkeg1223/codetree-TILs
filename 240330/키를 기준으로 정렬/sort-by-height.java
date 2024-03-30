import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int height, weight;

    public Person() {}
    public Person(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public void getInfo() {
        System.out.println(this.name + " " + this.height + " " + this.weight);
    }

    @Override
    public int compareTo(Person p) {
        return this.height - p.height;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        Person[] ps = new Person[n];
        StringTokenizer st;
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