package google2004;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class InputE {
	public static String filename = "google2004File/e.txt";

	public static String getEFromFile(File file) throws FileNotFoundException {
		// ���ļ��ﵽ18000�ֽ�ʱ�������ǲ���builder����String�������������쳣
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
