import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap so luong phan tu:");
        int n = in.nextInt();
        int s = 0;
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            System.out.println("Nhap phan tu thu " + i);
            a[i] = in.nextInt();
        }
        System.out.println("Mang da nhap: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                s += a[i];
            }
        }
        System.out.println("Tong so le: " + s);
        System.out.println("Nhap phan tu k: ");
        int k = in.nextInt();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                System.out.println(k + " xuat hien tai vi tri: " + (i + 1));
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    a[i] = a[i] * a[j];
                    a[j] = a[i] / a[j];
                    a[i] = a[i] / a[j];
                }
            }
        }
        System.out.println("Mang da duoc sap xep: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("Nhap so can chen");
        int p = in.nextInt();
        int[] temp = new int[n + 1];
        System.arraycopy(a, 0, temp, 0, a.length);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                temp[i] = p;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[i] > temp[j]) {
                    temp[i] = temp[i] * temp[j];
                    temp[j] = temp[i] / temp[j];
                    temp[i] = temp[i] / temp[j];
                }
            }
        }
        System.out.println("Mang da duoc sap xep: ");
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + " ");
        }
        System.out.println();
        in.close();
    }
}
