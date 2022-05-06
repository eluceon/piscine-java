import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		ArrayList<String[]> signatures = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		String filePath;
		
		SignaturesParser.parseFile(signatures);
		while (!(filePath = scanner.next()).equals("42")) {
			System.out.println(FileChecker.parseSignature(filePath, signatures));
		}
		scanner.close();
	}
}
