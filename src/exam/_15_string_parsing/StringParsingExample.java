package exam._15_string_parsing;

import java.util.Arrays;

public class StringParsingExample {
    public static void main(String[] args) {
        splitExample();
        charLoopExample();
        numberParsingExample();
        stringBuilderExample();
    }

    private static void splitExample() {
        String line = "kim 20 seoul";
        String[] parts = line.split(" ");
        System.out.println(Arrays.toString(parts));
    }

    private static void charLoopExample() {
        String text = "a1b2c3";
        int digitCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isDigit(ch)) {
                digitCount++;
            }
        }

        System.out.println(digitCount);
    }

    private static void numberParsingExample() {
        String numberText = "12345";
        int number = Integer.parseInt(numberText);
        System.out.println(number + 10);
    }

    private static void stringBuilderExample() {
        StringBuilder sb = new StringBuilder();
        sb.append("java");
        sb.append("-");
        sb.append("test");
        System.out.println(sb.toString());
        System.out.println(sb.reverse().toString());
    }
}
