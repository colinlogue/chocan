/*
Kelsey: provider terminal will take in a provider id,then a member ID (see n1) and then progress to the
terminal where the following options will be displayed:

1.provider directory access (search for a service)
2. make a service report (write to database, write to the file in the designated
reports directory)
3.quit current provider/ member session ( log out of currently used provider and member id, go back to first menu)

n1: terminal will need to validate provider and member ID and display a message
of members current member status before progressing to main text page

ALSO everything protected for the moment
add some way of automating a weekly report for the manager?
 */
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ProviderTerminal{
    static public Scanner input;

    public ProviderTerminal(){
        input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ProviderTerminal terminal = new ProviderTerminal();
        int task_num;

//MENU OUTPUT//
        do{
        try
        {
            System.out.print("Select from the following menu:\n" +
                    " 1. Create member service report.\n" +
                    " 2. Search for a service code and it's related info.\n" +
                    " 3. Bill ChocAn for the current session.\n" +
                    " 4. Quit current session. \n");

            task_num = input.nextInt();
        }catch (InputMismatchException e) {
            input.nextLine();
            task_num = -1;
        }
        terminal.validate_provider();

        switch (task_num) {  //grant access to main menu
            case 1:
                terminal.service_report();
                break;
            case 2:
                terminal.provider_dir_search();
                break;
            case 3:
                terminal.service_billing();
                break;
            case 4:
                System.out.print(" The provider has terminated the session.\n");
                break;
            default:
                System.out.print(" Invalid input. Try again.\n");
                break;
        }

    } while (task_num >= 1 && task_num <= 4);


   }

    private boolean validate_provider(){

        ProviderData provider = new ProviderData();
        try{
            provider.retrieve(provider.ident);
            System.out.print("success, logging into session.");

        }catch(SQLException e){

            System.out.print("This provider DNE in the system.");
            return false;
        }
        return true;
    }
    private boolean validate_member() {
        return false; //return a message that member reflects provider status
    }
    private boolean service_report(){
        System.out.print("Please enter the Member ID");
        return false; //the report could not be written to database
    }
    private String provider_dir_search(){
        System.out.print("Please enter the six-digit service code.\n");
        return null; //the service code DNE
    }
    // the providers way of documenting how much theyve worked, and cost based on service codes
    private boolean service_billing(){
        System.out.print("Please enter your nine-digit Provider number\n");
        return false;
    }


}
