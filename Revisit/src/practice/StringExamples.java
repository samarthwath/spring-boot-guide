package practice;

public class StringExamples {
	public static void main(String[] args) {
		StringBuffer stringBuffer = new StringBuffer("Samarth Wath");
		String stringLiteral = "Samarth Wath";
		System.out.println("Checking string with equals method: " + stringLiteral.equals("Samarth Wath"));
		String stringLiteralSecond = "Samarth Wath";
		System.out.println("Check for content match between String and StringBuffer: ");
		System.out.println(stringBuffer.toString().equals(stringLiteral));
		System.out.println("Check for content match between String literal and String literal");
		System.out.println(stringLiteral == stringLiteralSecond);
		String concatenatedString = stringLiteral.concat("Hello");
		System.out.println("Logging Concatenated String: ");
		System.out.println(concatenatedString);
		System.out.println("Check for match between String and Concatenated String: ");
		System.out.println(stringLiteral == concatenatedString);
		StringBuffer sbAppend = stringBuffer.append("Demo");
		System.out.println(stringBuffer == sbAppend);

	}
}
