import java.util.Scanner;

public class App {

    static int factorial(int m) {
        if (m == 1) {
            return 1;

        }
        return (m * factorial(m - 1));
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = 1;
        float s = 0;
        for (int i = 1; i <= n; i++) {
            if (!((2 * i - 1) % 2 == 0)) {
                m = (2 * i - 1);
                s = s + (1f / factorial(m));
            }
        }
        System.out.println(s);
        input.close();
    }
}
