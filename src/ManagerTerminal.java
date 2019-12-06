import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerTerminal{
    static public Scanner input;
    private ManagerReports reports = new ManagerReports();

    public ManagerTerminal(){
       input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ManagerTerminal menu_tree = new ManagerTerminal();
        StaffManagement temp = new StaffManagement();
        //temp.remove_staff("Provider");

        //Menu tree for manager terminal
        menu_tree.main_menu();
    }

    //Main Menu
    //Provides options to manage members, manage providers, or
    //display reports.
    private void main_menu()
    {
        int option;
        do //Continue to display main menu until valid choice is made by user
        {
            //Displays main menu
            display_main_menu();

            //Prompts user for int, sets to -1 if not int
            option = enter_an_int();


            switch (option) //Calls appropriate sub-menu based on valid input
            {
                case 1: //manage member
                    manage_member();
                    break;
                case 2: //manage provider menu
                    manage_provider();
                    break;
                case 3: //display reports
                    manage_reports();
                    break;
                case 4: //Log the manager out of manager terminal
                    System.out.println("\n LOGGED OUT\n");
                    break;
                default: //If valid input is not received, message will display
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

        do {    //Continues to display manager member menu until valid user input reveived

            display_manage_menu("Member");

            //Prompts user for int, sets to -1 if not int
            option = enter_an_int();

            switch (option) {   //Add, remove, or update member based on valid user input
                case 1:     //add member
                    System.out.print("\nADD MEMBER\n" +
                                     "-----------\n");
                    break;
                case 2:     //remove member
                    System.out.print("\nREMOVE MEMBER\n" +
                                     "--------------\n");
                    break;
                case 3:     //update member
                    System.out.print("\nUPDATE MEMBER\n" +
                                     "-------------\n");
                    break;
                case 4:     //Return to Main Menu
                    main_menu();
                    break;
                default:    //Prints error message to screen if input is invalid
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
            display_manage_menu("Provider");

            //Prompts user for int, sets to -1 if not int
            option = enter_an_int();

            switch (option) {   //Add, remove, or update based on valid user input
                case 1:     //add provider
                    System.out.print("ADD PROVIDER" + "-------------\n");
                    break;
                case 2:     //remove provider
                    System.out.print("REMOVE PROVIDER" +
                                     "----------------\n");
                    break;
                case 3:     //update provider
                    System.out.print("UPDATE PROVIDER" +
                                     "----------------\n");
                    break;
                case 4:     //Return user to Main Menu
                    main_menu();
                    break;
                default:        //Error message printed to terminal if input is not valid
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU");
                    break;
            }
            //Input must be a number from menu options to exit loop
        }while(option < 1 || option > 4);
    }

    private void manage_reports(){
        int option;     //holds user input

        do {    //Displays report menu until valid input
            display_report_menu();

            //Prompts user for int, sets to -1 if not int
            option = enter_an_int();

            switch (option) {   //Add, remove, or update based on valid user input
                case 1:     //Display member report
                    reports.member_report();
                    option = -2;    //flag to return to menu w/o error message
                    break;
                case 2:     //Display provider report
                    reports.provider_report();
                    option = -2;    //flag to return to menu w/o error message
                    break;
                case 3:     //Display accounts payable totals
                    reports.accounts_payable();
                    option = -2;    //flag to return to menu w/o error message
                    break;
                case 4:     //Return to main menu
                    main_menu();
                    break;
                default:    //Return to reports menua
                    if(option == -2) {  //return without error message
                        System.out.println("\nRETURNING TO REPORTS MENU");
                    }
                    else    //return with error message for invalid input
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

    //Displays report menu
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

    //Prompts user for int, returns -1 if not int
    private int enter_an_int(){
        int option;
        try
        {
            System.out.print("Enter Option: ");
            option = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            option = -1;
        }
        return option;
    }

}
