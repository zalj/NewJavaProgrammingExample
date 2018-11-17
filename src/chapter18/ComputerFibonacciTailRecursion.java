package chapter18;

public class ComputerFibonacciTailRecursion {
	public static void main(String[] args) {
		for(int i = 0; i < 20; i++)
			System.out.println(fibonacci(i));
	}
	
	public static long fibonacci(int n) {
		return fibonacci(n, 0, 1);
	}
	
	/** 使用尾递归 */
	private static long fibonacci(int n, int result, int next) {
		if(n == 0)
			return result;
		else 
			return fibonacci(n - 1, result + next, result);
	}
}
