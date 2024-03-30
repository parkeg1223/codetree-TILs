import java.io.*;
import java.util.*;

class Product {
    String name;
    int code;

    public Product() {}
    public Product(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public void getInfo() {
        System.out.println("product " + this.code + " is " + this.name);
    }
};

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Product p1 = new Product("codetree", 50);
        Product p2 = new Product();

        p2.name = st.nextToken();
        p2.code = Integer.parseInt(st.nextToken());

        p1.getInfo();
        p2.getInfo();
    }
}