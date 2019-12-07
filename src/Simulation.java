// This class is basically the main function. It just lets the user
// pick which version of the program to run (i.e. manager terminal,
// provider terminal, or generate reports)

import java.util.Scanner;

public class Simulation {

    public static void display_options() {
        System.out.println("  1. Provider Terminal");
        System.out.println("  2. Manager Terminal");
        System.out.println("  3. Exit");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the ChocAn system simulation!");
        display_options();
        int choice = 0;
        System.out.print("Please select which terminal you would like to simulate:  ");
        choice = input.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println();
            System.out.print("Invalid choice. Please choose again:  ");
            System.out.println();
            choice = input.nextInt();
        }
        if (choice == 1) {
            ProviderTerminal.main(new String[] {});
        }
        else if (choice == 2) {
            ManagerTerminal terminal = new ManagerTerminal();
            terminal.main_menu();
        }
        else {
            System.out.println("Thank you for using the ChocAn system!");
            System.out.println("Have a wonderful day.");
        }
    }
}
