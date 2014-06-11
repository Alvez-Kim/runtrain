package pac.testcase.basic;

import org.apache.shiro.codec.Base64;

public class TestBase64 {
	public static void main(String[] args) {
		/*
		 *  http://118.85.207.95:8082/dpf/passPort.html?
		 *  PassportLoginResponse=
		 *  3500000000000001%243aymjF0RwuuL3evyBF8W%2blUAg9Y3lPmxxUBNhv0vL9ysP4H18y8%2bGx1RqpTibey9LqH%2bgMS%2b8eCxoMLqP6rBO5af2bbzSjIslZVwYXYGUx%2fhreIiqnVqQRTLmYGWT%2fCVHbr%2f0h34LyQabNMEqNfCD%2fLAtcdvhc%2bT
		 */
		
		//C87A9BF1CAB99D680B268619088BBCFB1C17C6538CE161D8
		System.out.println(new String(Base64.decode("3500000000000001%243aymjF0RwuuL3evyBF8W%2blUAg9Y3lPmxxUBNhv0vL9ysP4H18y8%2bGx1RqpTibey9LqH%2bgMS%2b8eCxoMLqP6rBO5af2bbzSjIslZVwYXYGUx%2fhreIiqnVqQRTLmYGWT%2fCVHbr%2f0h34LyQabNMEqNfCD%2fLAtcdvhc%2bT")));
	}
}
