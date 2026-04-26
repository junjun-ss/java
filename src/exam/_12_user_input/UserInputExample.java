package exam._12_user_input;

import java.util.Scanner;

public class UserInputExample {

    public static String userStringInput(Scanner scanner) {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.println(name);
        
        System.out.print("숫자를 입력하세요: ");
        int numberData = scanner.nextInt();
        System.out.println(numberData);
        scanner.nextLine();

        return name;
    }
    
    public static void userInputLoop(Scanner scanner) {
        boolean shouldContinue = true;
        
        while (shouldContinue) {
            System.out.print("값을 입력하세요 (종료하려면 'exit' 입력): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                shouldContinue = false;
            } else {
                System.out.println("입력된 값: " + input);
            }
        }
    }
	

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String inputData = userStringInput(scanner);
            System.out.println("입력된 이름: " + inputData);
            userInputLoop(scanner);
        }
    }
}
