package chapter17;

import java.io.*;
import java.util.Scanner;

public class DetectedEndOfFile {

	public static void main(String[] args) throws IOException{
		try {
			try(
				DataOutputStream output = new DataOutputStream(new FileOutputStream("test.dat"));	
			){
				output.writeDouble(4.5);
				output.writeDouble(43.25);
				output.writeDouble(3.2);
			}
			
			try(
				DataInputStream input = new DataInputStream(new FileInputStream("test.dat"));
			){
				while(true)
					System.out.println(input.readDouble());
			}
		}catch (EOFException e){
			System.out.println("All data were read");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try(
		Scanner input = new Scanner(System.in);
		){
			System.out.print("Enter a string: ");
			String str;
			while(!((str = input.nextLine()).equals("") || str.equalsIgnoreCase("exit"))) {
				System.out.println(str + " is palindrome? " + isPalindrome(str));
				System.out.print("Enter a string: ");
			}
		}
		
	}

	public static boolean isPalindrome(String string) {
		return isPalindrome(string, 0, string.length() - 1);
	}
	
	public static boolean isPalindrome(String string, int low, int high) {
		if (high <= low)
			return true;
		else if (string.charAt(low++) != string.charAt(high--))
			return false;
		else
			return isPalindrome(string, low, high);
	}
}
