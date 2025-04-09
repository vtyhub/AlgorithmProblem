package google2004;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;


public class ValidatePrime {
	
	public static BigInteger getFirstPrime(BigDecimal e, int len, int certainty) {
		String es = e.toString();
		String subes = es.substring(2, es.length());
		for (int i = 0; i <= subes.length() - len; i++) {
			String tempsub = subes.substring(i, i + len);
			BigInteger big = new BigInteger(tempsub);
			if (big.isProbablePrime(certainty)) {
				return big;
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String eFromFile = InputE.getEFromFile(new File(InputE.filename));
		BigDecimal decimal = new BigDecimal(eFromFile);
		BigInteger firstPrime = getFirstPrime(decimal, 10, 100);
		System.out.println(firstPrime);
	}
}
