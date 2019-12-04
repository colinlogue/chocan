import java.sql.SQLException;
import java.util.InputMismatchException;

public class ManagerTerminal extends ManagerTools{

    public static String provider = "Provider";
    public static String manager = "Manager";

    public static void main(String[] args) {
        ManagerTerminal temp = new ManagerTerminal();

        temp.add_staff("Provider");
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

    //Displays main menu for manager terminal
    private void display_main_menu()
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
    private void display_manage_menu(String person)
    {
        System.out.println("\nManage " + person + " Menu\n" +
                "---------------------\n" +
                "PLEASE CHOOSE FROM THE OPTIONS BELOW");
        System.out.println("1. Add " + person);
        System.out.println("2. Remove " + person);
        System.out.println("3. Update " + person);
        System.out.println("4. Return to Main Menu");
    }

    private void display_report_menu()
    {
        System.out.println("\nReport Menu\n" +
                           "-----------\n" +
                           "PLEASE CHOOSE FROM THE OPTIONS BELOW");
        System.out.println("1. Display Individual Report");
        System.out.println("2. Display Weekly Report");
        System.out.println("3. Display Account Payable");
        System.out.println("4. Return to Main Menu");
    }

    //Sub-menu for report menu
    private void display_person_menu()
    {
        System.out.println("1. Member");
        System.out.println("2. Provider");
        System.out.println("3. Return to Main Menu");
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
                    add_staff(provider);
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

    //Prompts for new staff's (Manager or Provider) name and address information
    //change return type to indicate success or failure
    private void add_staff(String staff_title)
    {
        int repeat;
        PersonData new_staff;

        if(staff_title.equals(provider))
            new_staff = new ProviderData();
        else
            new_staff = new MemberData();

        do
        {
            new_staff.name = prompt_name(staff_title);

            System.out.println("Enter new " + staff_title + "'s address.");
            new_staff.address.street = prompt_street();
            new_staff.address.city = prompt_city();
            new_staff.address.state = prompt_state();
            new_staff.address.ZIP = prompt_zip();

            //Add display() and confirm if the information is correct with the user.
            repeat = ask_to_repeat();
        }
        while(repeat == 1);

        //Include call to person.write() here after everything is done.
            //Do exception handling and maybe change return type for this method.
        if(new_staff instanceof ProviderData)
        {
            try{
                ProviderData providerData = (ProviderData) new_staff;
                providerData.write();
            }
            catch (SQLException e){
                //Just a place holder for now.
                System.out.println("Failed to write to database");
            }
        }
        else {
            try {
                MemberData memberData = (MemberData) new_staff;
                memberData.write();
            }
            catch (SQLException e){
                //Just a place holder for now.
                System.out.println("Failed to write to database");
            }
        }
    }

    /*
    private void remove_staff(String staff_title)
    {
        int ident;
        PersonData staff;

        if(staff_title.equals(provider))
            staff = new ProviderData();
        else
            staff = new MemberData();

        System.out.print("Enter Identification number of the " + staff_title);
        ident = input.nextInt();

        if(staff_title.equals(provider)){
            ProviderData pData = (ProviderData) staff;
        }
    }

     */

    /*
    private void update_staff(String staff_title)
    {
        int ident;
        PersonData

        System.out.print("Enter Identification number of the " + staff_title);
        ident = input.nextInt();


        if(staff_title.equals(provider)){
           ProviderData pData = ProviderData.retrieve(ident);
           try {
               pdata.re
           }
           catch (SQLException e) {
               System.out.println(staff_title + " with ID:" + ident + " could not be found.");
           }
        }
        else{

        }
    }
     */
}
