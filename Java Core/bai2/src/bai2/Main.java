package bai2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		float s = 0;
		for (int i = 1; i <= n; i++) {
			s = s + (1f / i);
		}
		System.out.println(s);
		input.close();
	}
}