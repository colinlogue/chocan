import java.util.Scanner;

public class ManagerTerminal {
    public static void main(String[] args) {
        ManagerTerminal temp = new ManagerTerminal();

        temp.main_menu();
    }
    private void main_menu()
    {
        Scanner input = new Scanner(System.in);
        int option;
        do
        {
            display_main_menu();
            System.out.print("Enter Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                //Method directs to add, remove, update based on option value
                    manage_member();

                    //Add methods here
                    break;
                case 2:
                //Method directs to add, remove, update base on option value
                    manage_provider();

                    //Add methods here
                    break;
                case 3:
                    display_report_menu();

                    //Add methods here
                    break;
                default:
                    System.out.print("\nINPUT ERROR: PLEASE ENTER A NUMBER FROM THE MENU\n");
                    break;
            }
        } while (option != 1 && option != 2 && option != 3);
    }

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

    //
    private void manage_member ()
    {
        Scanner input = new Scanner(System.in);
        int option;
        do {
            display_manage_menu("Member");

            System.out.print("Enter Option: ");
            option = input.nextInt();
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
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU\n");
                    break;
            }
        }while(option != 1 && option != 2 && option != 3);
    }

    //
    private void manage_provider ()
    {
        Scanner input = new Scanner(System.in);
        int option;
        do {
            display_manage_menu("Provider");
            System.out.println("Enter Option: ");
            option = input.nextInt();

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
                    System.out.println("\nINPUT ERROR: PLEASE CHOOSE A NUMBER FROM THE MENU");
                    break;
            }
        }while(option != 1 && option != 2 && option != 3);
    }
}
