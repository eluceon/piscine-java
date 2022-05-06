import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SignaturesParser {
    public static void parseFile(ArrayList<String[]> signatures) {
        StringBuilder sb = new StringBuilder();
        try (InputStream input = new FileInputStream("signatures.txt")) {
            while (input.available() > 0) {
                char ch = (char)input.read();
                if (ch == '\n') {
                    int commaIdx = sb.indexOf(",");
                    signatures.add(new String[]{sb.substring(commaIdx + 2).replace(" ", ""), sb.substring(0, commaIdx)});
					sb.setLength(0);
                } else {
                    sb.append(ch);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
