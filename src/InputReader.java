import java.util.Scanner;

public class InputReader {
    public static boolean startsWithSpace(String input) {
        if (input.startsWith(" ")) {
            System.out.println("Your input starts with a space. This is not allowed. Try again.");

            return true;
        }

        return false;
    }

    public static String requestTextInput(String request) {
        System.out.println(request);

        String input = readUserInput();

        if (startsWithSpace(input)) {
            input = requestTextInput(request);
        }

        return input;
    }

    public static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);

        return keyboard.nextLine();
    }
}
