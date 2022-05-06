import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Parser {
    public static void parseFile(HashMap<String, Integer> mapA, HashMap<String, Integer> mapB,
        HashSet<String> dictionary, String path1, String path2) 
    {
       try (BufferedReader readerA = new BufferedReader(new FileReader(path1));
        	BufferedReader readerB = new BufferedReader(new FileReader(path2)))
		{
			String line;
			while((line = readerA.readLine()) != null)
				fillMapAndDictionary(line.split("\\s+"), mapA, dictionary);
			while((line = readerB.readLine()) != null)
				fillMapAndDictionary(line.split(" "), mapB, dictionary);
		} catch (IOException e) {
			System.err.println(e.toString());
			System.exit(-1);
		}
    }

    private static void fillMapAndDictionary(String[] strings,
        HashMap<String, Integer> map, HashSet<String> dictionary)
    {
        for (String key : strings) {
            map.compute(key, (k, v) -> (v == null) ?  1 : v + 1);
            dictionary.add(key);
        }
    }
}
