import java.util.Scanner;

public class App {

    static int findGCF(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCF(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap a: ");
        int a = input.nextInt();
        System.out.println("Nhap b: ");
        int b = input.nextInt();
        System.out.println("UCLN: " + String.valueOf(findGCF(a, b)));
        System.out.println("BCNN: " + String.valueOf((a * b) / findGCF(a, b)));
        input.close();
    }
}
