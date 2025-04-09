package subStringsFromLoopString;

import java.util.ArrayList;
import java.util.ListIterator;

public class LoopString {

	public static final String DIGIT = "0123456789";

	public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	public static final String DIGITLOWERCASE = DIGIT + LOWERCASE;
	public static final String LOWERCASEDIGIT = LOWERCASE + DIGIT;

	public static final String UPPERCASE = LOWERCASE.toUpperCase();
	public static final String DIGITUPPERCAST = DIGIT + UPPERCASE;
	public static final String UPPERCASTDIGIT = UPPERCASE + DIGIT;

	public static final String LOWERUPPERCASE = LOWERCASE + UPPERCASE;
	public static final String UPPERLOWERCASE = UPPERCASE + LOWERCASE;

	public static String duplicateRemoval(String mould) {
		ArrayList<Character> list = new ArrayList<>();
		for (int i = 0; i < mould.length(); i++) {
			if (!list.contains(mould.charAt(i))) {
				list.add(mould.charAt(i));
			}
		}
		StringBuilder builder = new StringBuilder(list.size());
		ListIterator<Character> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			builder.append(listIterator.next().charValue());
		}
		return builder.toString();
	}

	public static int correctStartIndex(int startindex, int len) {
		return startindex < 0 ? 0 : startindex > len - 1 ? len - 1 : startindex;
	}

	public static String ringShiftLeft(String mould, int n) {
		int m = Math.abs(n % mould.length());
		String left = mould.substring(0, m), right = mould.substring(m, mould.length());
		return right + left;
	}

	public static String ringShiftRight(String mould, int n) {
		int len = mould.length(), m = Math.abs(n % len);
		String left = mould.substring(0, len - m), right = mould.substring(len - m, len);
		return right + left;
	}

	// ����������ǰ�mould�ظ�������Ϊn���п������������Ҫ�ظ�������û��Ҫȥ��
	// startindexָ��mould�ĵڼ���������ʼ
	// �ѽ��ѭ������startλ�Ͱ�mouldѭ��������startλ֮���ٴ���ò�ƽ��һ����,��һ���������������ٴ���
	public static String genLoopString(int n, int startindex, String mould) {
		int len = mould.length(), group = n / len, module = n % len;
		n = n < 0 ? 0 : n;
		startindex = correctStartIndex(startindex, len);
		String left = ringShiftLeft(mould, startindex);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < group; i++) {
			sb.append(left);
		}
		if (module != 0) {
			return sb.append(left.substring(0, module)).toString();
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		String loopString = genLoopString(1, 1, LOWERCASE);
		System.out.println(loopString);
	}
}
