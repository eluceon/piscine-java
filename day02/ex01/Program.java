import java.util.HashMap;
import java.util.HashSet;

public class Program {
    public static void main(String[] args) {
		HashSet<String> dictionary = new HashSet<>();
		HashMap<String, Integer> mapA = new HashMap<>();
		HashMap<String, Integer> mapB = new HashMap<>();

		if (args.length != 2) {
            System.err.println("Usage: java Program file1 file2");
            System.exit(-1);
        }
        Parser.parseFile(mapA, mapB, dictionary, args[0], args[1]);
		if (mapA.size() == 0 && mapB.size() == 0) {
			System.out.printf("%.3f\n", 1.0);
		} else if(mapA.size() == 0 || mapB.size() == 0) {
			System.out.printf("%.3f\n", 0.0);
		} else {
			System.out.printf("%.3f\n", countSimilarity(mapA, mapB, dictionary));
		}
	}

    private static double countSimilarity(HashMap<String, Integer> mapA,
        HashMap<String, Integer> mapB, HashSet<String> dictionary)
    {
        double similarity = 0;
        long numerator = 0;
        double denominatorA = 0;
        double denominatorB = 0;
		Integer countA, countB;

		for (String dic : dictionary) {
			countA = mapA.containsKey(dic) ? mapA.get(dic) : 0;
			countB = mapB.containsKey(dic) ? mapB.get(dic) : 0;
			numerator += countA * countB;
			denominatorA += countA * countA;
			denominatorB += countB * countB;
		}
		try {
			similarity = numerator / 
				(Math.sqrt(denominatorA) * Math.sqrt(denominatorB));
		} catch (ArithmeticException e) {
			e.getMessage();
		}
        return similarity;
    }
}