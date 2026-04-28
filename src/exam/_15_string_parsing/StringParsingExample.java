package exam._15_string_parsing;

import java.util.Arrays;

public class StringParsingExample {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(splitColumns("kim 20 seoul")));
        System.out.println(countDigits("a1b2c3"));
        System.out.println(parseInt("12345") + 10);
        System.out.println(joinWithDash("java", "test"));
    }

    public static String[] splitColumns(String line) {
        return line.trim().split("\\s+");
    }

    public static int countDigits(String text) {
        int digitCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isDigit(ch)) {
                digitCount++;
            }
        }

        return digitCount;
    }

    public static int parseInt(String numberText) {
        return Integer.parseInt(numberText);
    }

    public static String joinWithDash(String first, String second) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        sb.append("-");
        sb.append(second);
        return sb.toString();
    }

    public static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }
}
