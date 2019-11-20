import java.util.Scanner;

public class ManagerTerminal
{
    public static void main(String[] args)
    {
        ManagerTerminal temp = new ManagerTerminal();
        Scanner input = new Scanner(System.in);
        //Shows main manager terminal menus and it's options
        //Should be wrapped in another method in the future
        int option;

        temp.display_main_menu();
        System.out.print("Enter Option: ");
        option = input.nextInt();
        switch(option)
        {
            case 1:
                temp.display_manage_menu("Manager");
                //Add methods here
                break;
            case 2:
                temp.display_manage_menu("Providers");
                //Add methods here
                break;
            case 3:
                temp.display_report_menu();
                //Add methods here
            default:
                break;
        }
    }

    private void display_main_menu()
    {
       System.out.println("1. Manage Members");
       System.out.println("2. Manage Providers");
       System.out.println("3. Print Reports");
    }

    //Used for both Manage Members and Manage Providers Option
    //person should be the string "Member" or "Provider"
    private void display_manage_menu(String person)
    {
        System.out.println("1. Add " + person);
        System.out.println("2. Remove " + person);
        System.out.println("3. Update " + person);
    }

    private void display_report_menu()
    {
        System.out.println("1. Display Indivisual Report");
        System.out.println("2. Display Weekly Report");
        System.out.println("3. Display Account Payable");
    }

    //Sub-menu for report menu
    private void display_person_menu()
    {
        System.out.println("1. Member");
        System.out.println("2. Provider");
    }
}
