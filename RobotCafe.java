import java.util.InputMismatchException;
import java.util.Scanner;

public class RobotCafe {

    private static final int EXIT_CHOICE = 0;
    private static final int MIN_MENU_CHOICE = 1;
    private static final int MIN_ROBOT_CODE = 1000;
    private static final int MAX_ROBOT_CODE = 9999;

    private static final String[] PRODUCTS = {
        "Kaffe", "Te", "Bulle", "Energidryck"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();

            int choice = readInteger(
                scanner, "Välj en produkt (0 för att avsluta): "
            );

            if (choice == EXIT_CHOICE) {
                running = false;
                System.out.println("Robotcaféet stänger. Hej då!");

            } else if (!isValidMenuChoice(choice)) {
                System.out.println(
                    "Ogiltigt val! Välj 0 eller ett nummer mellan 1 och "
                    + PRODUCTS.length + "."
                );

            } else {
                String product = getProduct(choice);
                System.out.println("Du har beställt: " + product);

                int robotCode = readInteger(
                    scanner, "Ange din fyrsiffriga robotkod: "
                );

                try {
                    validateRobotCode(robotCode);
                    System.out.println("Robotkoden är godkänd!");

                } catch (IllegalArgumentException e) {
                    System.out.println("Fel: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n=== Robotcafé ===");

        for (int i = 0; i < PRODUCTS.length; i++) {
            System.out.println((i + 1) + ". " + PRODUCTS[i]);
        }

        System.out.println("0. Avsluta");
    }

    public static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;

            } catch (InputMismatchException e) {
                System.out.println("Fel! Du måste skriva ett heltal.");
                scanner.nextLine();
            }
        }
    }

    public static boolean isValidMenuChoice(int choice) {
        return choice >= MIN_MENU_CHOICE
                && choice <= PRODUCTS.length;
    }

    public static String getProduct(int choice) {
        return PRODUCTS[choice - 1];
    }

    public static void validateRobotCode(int code) {
        if (code < MIN_ROBOT_CODE || code > MAX_ROBOT_CODE) {
            throw new IllegalArgumentException(
                "Robotkoden måste bestå av fyra siffror."
            );
        }
    }
}
