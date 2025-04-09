package google2004;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class InputE {
	public static String filename = "google2004File/e.txt";

	public static String getEFromFile(File file) throws FileNotFoundException {
		// 当文件达到18000字节时，无论是采用builder还是String，结果都会出现异常
		try (Scanner scanner = new Scanner(file)) {
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine().trim();
				System.out.println(nextLine);
				builder.append(nextLine);
			}
			System.out.println("-------------------------------");
			return builder.toString();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String e = getEFromFile(new File(filename));
		BigDecimal big = new BigDecimal(e);
		System.out.println(e);
		System.out.println(big);
	}
}
