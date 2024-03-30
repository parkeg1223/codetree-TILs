import java.io.*;
import java.util.*;

class Person {
    String name;
    int height;
    double weight;

    public Person() {}
    public Person(String name, int height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public void getInfo() {
        System.out.printf("%s %d %.1f\n", this.name, this.height, this.weight);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Person[] ps = new Person[5];

        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(in.readLine());
            ps[i] = new Person(
                st.nextToken(),
                Integer.parseInt(st.nextToken()),
                Double.parseDouble(st.nextToken())
            );
        }

        Arrays.sort(ps, (o1, o2) -> o1.name.compareTo(o2.name));
        System.out.println("name");
        for (int i = 0; i < 5; i++) {
            ps[i].getInfo();
        }

        System.out.println();

        Arrays.sort(ps, (o1, o2) -> o2.height - o1.height);
        System.out.println("height");
        for (int i = 0; i < 5; i++) {
            ps[i].getInfo();
        }
    }
}