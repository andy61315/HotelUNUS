package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		while(true) {
			System.out.println("請輸入數字：");
			Scanner scanner = new Scanner(System.in);
			int num = Integer.valueOf(scanner.next());
			String[] arr = new String[10];
			int r = 0;
			while(num > 1000) {
				arr[r++] = String.valueOf(num%1000);
				num /= 1000;
			}
			String output = "";
			for(int i = r-1; i >=0 ; i--) {
				output += "," + arr[i] ;
			}
			System.out.println("$" + num + output);
		}
	}
}
