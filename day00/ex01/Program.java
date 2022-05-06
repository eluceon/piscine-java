import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		int ctr = 1;

		Scanner sc = new Scanner(System.in);
    	System.out.println("Enter your number:");

    	int number = sc.nextInt();
		if (number <= 1) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		for (int i = 2; i * i <= number; i++)
		{
			if (number % i == 0) {
				System.out.print("false ");
				System.out.println(ctr);
				return;
			}
			++ctr;
		}
		System.out.print("true ");
		System.out.println(ctr);
	}
}
