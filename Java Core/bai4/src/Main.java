import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();
        char[] charData = data.toCharArray();
        int p = 1;
        int s = 0;
        for (int i = 0; i < charData.length; i++) {
            p *= Integer.parseInt(String.valueOf(charData[i]));
            s += Integer.parseInt(String.valueOf(charData[i]));
        }
        System.out.println(p);
        System.out.println(s);
        input.close();
    }
}
