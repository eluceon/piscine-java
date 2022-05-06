import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileChecker {
	public static String parseSignature(String filePath, ArrayList<String[]> signatures) {
		try (OutputStream fout = new FileOutputStream("result.txt", true))
		{
			for (String[] str : signatures) {
				String fileSignature = getFileSignature(filePath, str[0].length() / 2);
				if (str[0].equals(fileSignature)) {
					for (int i = 0; i < str[1].length(); ++i) {
						fout.write(str[1].charAt(i));
					}
					fout.write((int)'\n');
					return "PROCESSED";
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return "UNDEFINED";
	}

	private static String getFileSignature(String filePath, int countBytes) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (InputStream fin = new FileInputStream(filePath)) {
			for (int i = 0; i < countBytes; ++i) {
				int b = fin.read();
				sb.append(Character.forDigit((b >> 4) & 0xF, 16));
				sb.append(Character.forDigit((b) & 0xF, 16));
			}
		} catch (IOException e) {
			throw e;
		}
		return sb.toString().toUpperCase();
	}

}
