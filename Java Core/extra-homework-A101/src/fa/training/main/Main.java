package fa.training.main;

import java.util.TreeSet;

public final class Main {
        static int m(int i) {
                System.out.println(i + ",");
                return 0;
        }

        public static void main(String[] args) {
                String a = "X", b = "Y", c = a + b, d = a + b;
                System.out.println((a + b) == (a + b));
                System.out.println((c == d));
                System.out.println(c.equals(d));
        }

}