import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap a: ");
        Stack<Integer> stack = new Stack<Integer>();
        int num = scanner.nextInt();
        while (num != 0) {
            int d = num % 2;
            stack.push(d);
            num /= 2;
        }

        while (!(stack.isEmpty())) {
            System.out.print(stack.pop());
        }
        System.out.println();
        scanner.close();
    }
}
