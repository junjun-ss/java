package exam._12_user_input;

import java.util.Scanner;

public class UserInputExample {

    public static InputData readNameAndNumber(Scanner scanner) {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        
        System.out.print("숫자를 입력하세요: ");
        int numberData = scanner.nextInt();
        scanner.nextLine();

        return new InputData(name, numberData);
    }
    
    public static void readUntilExit(Scanner scanner) {
        while (true) {
            System.out.print("값을 입력하세요 (종료하려면 'exit' 입력): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                return;
            }
            System.out.println("입력된 값: " + input);
        }
    }
	

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputData inputData = readNameAndNumber(scanner);
            System.out.println(inputData);
            readUntilExit(scanner);
        }
    }

    public static class InputData {
        private final String name;
        private final int number;

        public InputData(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "InputData{name='" + name + "', number=" + number + "}";
        }
    }
}
