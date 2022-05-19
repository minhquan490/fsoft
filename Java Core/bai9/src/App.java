
/**
 * Java doc o day
 * @author QuanHM9
*/

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap so cot: ");
        int n = in.nextInt();
        System.out.println("Nhap so dong: ");
        int m = in.nextInt();
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print("Nhap phan tu thu [" + i + ", " + j + "]: ");
                a[i][j] = in.nextInt();
            }
        }
        int multiples = 1;
        for (int i = 0; i < m; i++) {
            if (a[0][i] % 3 == 0) {
                multiples *= a[0][i];
            }
        }
        System.out.println("Tich cac boi so cua 3 nam tren dong dau tien: " + multiples);
        int[] b = new int[n];
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp = a[0][0];
                if (a[i][j] >= temp) {
                    temp = a[i][j];
                }
            }
            b[i] = temp;
        }
        System.out.println("Mang X la: ");// Comment tren 1 dong day ne
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
        in.close();
    }
    /*
     * Day la
     * comment
     * tren nhieu dong
     */
}
