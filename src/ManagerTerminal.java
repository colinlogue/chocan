import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerTerminal{

    static public Scanner input;

    public ManagerTerminal(){
       input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StaffManagement temp = new StaffManagement();
        //temp.remove_staff("Provider");
        //Menu tree for manager terminal
        //temp.main_menu();
    }

    //Main Menu
    //Start of menu tree for manager terminal
    //Provides options to manage members, manage providers, or
    //display reports.
    private void main_menu()
    {
        int option;

        //Continue to display main menu until valid choice is made by user
        do
        {
            //Displays main menu and receive user input
            //Error displayed if input is not valid and
            //user will be prompted for valid input
            try
            {
                display_main_menu();
                System.out.print("Enter Option: ");
                option = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                option = -1;
            }

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
                case 4:
                    //Log the manager out of manager terminal
                    System.out.println("\n LOGGED OUT\n");
                default:
                    //If valid input is not received, message will display
                    System.out.print("\nINPUT ERROR: PLEASE ENTER A NUMBER FROM THE MENU\n");
                    break;
            }
            //Input must be number from menu options to exit loop
        } while (option < 1 || option > 4);
    }


    //Manage Member Menu
    //Provides options to add, remove, or update members
    private void manage_member ()
    {
        int option;

        //Continues to display manager member menu until valid user input reveived
        do {
            try
            {
                display_manage_menu("Member");
                System.out.print("Enter Option: ");
                option = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                option = -1;
            }

            //Calls add, remove, or update for member based on
            //valid user input
            switch (option) {
                case 1:
                    //add member
                    System.out.print("\nADD MEMBER\n" +
                                     "-----------\n");
                    break;
                case 2:
                    //remove member
                    System.out.print("\nREMOVE MEMBER\n" +
                                     "--------------\n");
                    break;
                case 3:
                    //update member
                    System.out.print("\nUPDATE MEMBER\n" +
                                     "-------------\n");
                    break;
                case 4:
                    //Return to Main Menu
                    main_menu();
                default:
                    //Prints error message to screen if input is invalid
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU");
                    break;
            }
            //Input must be a number from menu to exit loop
        }while(option < 1 || option > 4);
    }

    //Manage Provider Menu
    //Provides options to add, remove, or update members
    private void manage_provider ()
    {
        int option;

        //Continues to display menu until valid input is received from user
        do {
            try
            {
                display_manage_menu("Provider");
                System.out.print("Enter Option: ");
                option = input.nextInt();
            } catch (InputMismatchException e)
            {
                input.nextLine();
                option = -1;
            }

            //Calls add, remove, or update based on valid user input
            switch (option) {
                case 1:
                    //add provider
                    System.out.print("ADD PROVIDER" + "-------------\n");
                    break;
                case 2:
                    //remove provider
                    System.out.print("REMOVE PROVIDER" +
                                     "----------------\n");
                    break;
                case 3:
                    //update provider
                    System.out.print("UPDATE PROVIDER" +
                                     "----------------\n");
                    break;
                case 4:
                    //Return user to Main Menu
                    main_menu();
                default:
                    //Error message printed to terminal if input is not valid
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU");
                    break;
            }
            //Input must be a number from menu options to exit loop
        }while(option < 1 || option > 4);
    }

    private void manage_reports(){
        int option;

        do {
            try
            {
               display_report_menu();
                System.out.print("Enter Option: ");
                option = input.nextInt();
            } catch (InputMismatchException e)
            {
                input.nextLine();
                option = -1;
            }

            //Calls add, remove, or update based on valid user input
            switch (option) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    main_menu();
                default:
                    //Error message printed to terminal if input is not valid
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU");
                    break;
            }
            //Input must be a number from menu options to exit loop
        }while(option < 1 || option > 4);
    }

    //Displays main menu for manager terminal
    protected void display_main_menu()
    {
        System.out.println("\nMain Menu\n" +
                "----------\n" +
                "Please Choose From The Options Below");
        System.out.println("1. Manage Members");
        System.out.println("2. Manage Providers");
        System.out.println("3. Print Reports");
        System.out.println("4. Logout");
    }

    //Used for both Manage Members and Manage Providers Option
    //person should be the string "Member" or "Provider"
    protected void display_manage_menu(String person)
    {
        System.out.println("\nManage " + person + " Menu\n" +
                "---------------------\n" +
                "PLEASE CHOOSE FROM THE OPTIONS BELOW");
        System.out.println("1. Add " + person);
        System.out.println("2. Remove " + person);
        System.out.println("3. Update " + person);
        System.out.println("4. Return to Main Menu");
    }

    protected void display_report_menu()
    {
        System.out.println("\nReport Menu\n" +
                "-----------\n" +
                "PLEASE CHOOSE FROM THE OPTIONS BELOW");
        System.out.println("1. Display Member Report");
        System.out.println("2. Display Provider Report");
        System.out.println("3. Display Account Payable");
        System.out.println("4. Return to Main Menu");
    }

}
