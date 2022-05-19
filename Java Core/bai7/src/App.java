import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    static Map<Character, Integer> countChar(char[] input) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (charMap.containsKey(input[i])) {
                int value = charMap.get(input[i]);
                charMap.remove(input[i]);
                charMap.put(input[i], value + 1);
            } else {
                charMap.put(input[i], 1);
            }
        }
        return charMap;
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap chuoi: ");
        String a = input.nextLine();
        char[] data = a.toCharArray();
        int i = data.length;
        System.out.print("Dao nguoc: ");
        while (i > 0) {
            System.out.print(data[i - 1]);
            i--;
        }
        System.out.println();
        System.out.println("Doi chu hoa: " + a.toUpperCase());
        System.out.println("Doi chu thuong: " + a.toLowerCase());
        Map<Character, Integer> cMap = countChar(data);
        for (char c : cMap.keySet()) {
            System.out.print("So lan xuat hien cua " + c + " la: " + cMap.get(c));
            System.out.println();
        }
        System.out.println("Nhap n: ");
        int n = input.nextInt();
        System.out.println("Nhap m: ");
        int m = input.nextInt();
        System.out.print("Chuoi con cua " + a + " la: " + a.substring(n, m));
        System.out.println();
        input.close();
    }
}