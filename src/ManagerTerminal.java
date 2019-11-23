import java.util.Scanner;

public class ManagerTerminal {
    public static void main(String[] args) {
        ManagerTerminal temp = new ManagerTerminal();

        //Menu tree for manager terminal
        temp.main_menu();
    }

    //Main Menu
    //Start of menu tree for manager terminal
    //Provides options to manage members, manage providers, or
    //display reports.
    private void main_menu()
    {
        Scanner input = new Scanner(System.in);
        int option;

        //Continue to display main menu until valid choice is made by user
        do
        {
            //Displays main menu and receive user input
            display_main_menu();
            System.out.print("Enter Option: ");
            option = input.nextInt();

            //Calls appropriate sub-menu based on valid input
            switch (option) {
                case 1:
                //Method directs to manage member menu
                    manage_member();

                    //Add methods here
                    break;
                case 2:
                //Method directs to manage provider menu
                    manage_provider();

                    //Add methods here
                    break;
                case 3:
                    display_report_menu();

                    //Add methods here
                    break;
                default:
                    //If valid input is not received, message will display
                    System.out.print("\nINPUT ERROR: PLEASE ENTER A NUMBER FROM THE MENU\n");
                    break;
            }
            //Input must be number from menu options to exit loop
        } while (option != 1 && option != 2 && option != 3);
    }

    //Displays main menu for manager terminal
    private void display_main_menu()
    {
        System.out.println("\nMain Menu\n" +
                        "----------\n" +
                        "Please Choose From The Options Below");
        System.out.println("1. Manage Members");
        System.out.println("2. Manage Providers");
        System.out.println("3. Print Reports");
    }

    //Used for both Manage Members and Manage Providers Option
    //person should be the string "Member" or "Provider"
    private void display_manage_menu(String person)
    {
        System.out.println("\nManage " + person + " Menu\n" +
                "---------------------\n" +
                "PLEASE CHOOSE FROM THE OPTIONS BELOW");
        System.out.println("1. Add " + person);
        System.out.println("2. Remove " + person);
        System.out.println("3. Update " + person);
    }

    private void display_report_menu()
    {
        System.out.println("1. Display Individual Report");
        System.out.println("2. Display Weekly Report");
        System.out.println("3. Display Account Payable");
    }

    //Sub-menu for report menu
    private void display_person_menu()
    {
        System.out.println("1. Member");
        System.out.println("2. Provider");
    }

    //Manage Member Menu
    //Provides options to add, remove, or update members
    private void manage_member ()
    {
        Scanner input = new Scanner(System.in);
        int option;

        //Continues to display manager member menu until valid user input reveived
        do {

            //Prints manage member menu to terminal
            display_manage_menu("Member");

            System.out.print("Enter Option: ");
            option = input.nextInt();   //holds user response

            //Calls add, remove, or update for member based on
            //valid user input
            switch (option) {
                case 1:
                    //add member
                    System.out.print("Add Member Chosen");
                    break;
                case 2:
                    //remove member
                    System.out.print("Remove Member Chosen");
                    break;
                case 3:
                    //update member
                    System.out.print("Update Member Chosen");
                    break;
                default:
                    //Prints error message to screen if input is invalid
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU\n");
                    break;
            }
            //Input must be a number from menu to exit loop
        }while(option != 1 && option != 2 && option != 3);
    }

    //Manage Provider Menu
    //Provides options to add, remove, or update members
    private void manage_provider ()
    {
        Scanner input = new Scanner(System.in);
        int option;

        //Continues to display menu until valid input is received from user
        do {

            //Prints manage provider menu to screen
            display_manage_menu("Provider");

            System.out.println("Enter Option: ");
            option = input.nextInt();  //holds user response

            //Calls add, remove, or update based on valid user input
            switch (option) {
                case 1:
                    //add provider
                    System.out.print("Add Provider Chosen");
                    break;
                case 2:
                    //remove provider
                    System.out.print("Remove Provider Chosen");
                    break;
                case 3:
                    //update provider
                    System.out.print("Update Provider Chosen");
                    break;
                default:
                    //Error message printed to terminal if input is not valid
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU\n");
                    break;
            }
            //Input must be a number from menu options to exit loop
        }while(option != 1 && option != 2 && option != 3);
    }
}
