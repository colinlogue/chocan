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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ProviderTerminal{
    static public Scanner input;
    static public Scanner ID;

    //Takes input from provider
    public ProviderTerminal(){
        input = new Scanner(System.in);
        ID = new Scanner(System.in);
    }

    public static void main(String[] args) {

        ProviderTerminal terminal = new ProviderTerminal();
        int task_num;

        //make a loop w/try catch inside so they can enter prov ID again
        System.out.print("Type a Provider ID to proceed to Provider tools. \n");
        int Prov_ID =  ID.nextInt();
        if(terminal.validate_provider(Prov_ID))  //should only go into menu if provider is validated
        {
            //MENU OUTPUT//
            do {
                try {
                    System.out.print("Type a number to select from the following menu:\n" +
                            " 1. Create member service report.\n" +
                            " 2. Search for a service code and it's related info.\n" +
                            " 3. Bill ChocAn for the current session.\n" +
                            " 4. Quit current session. \n");

                    task_num = input.nextInt();
                } catch (InputMismatchException e) {
                    input.nextLine();
                    task_num = -1;
                }

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

            } while (task_num >= 1 && task_num < 4); //fixed this loop
        }

   }
   //END OF MAIN

    /* I can give user fake ids to test that have already been made.
    types in the ID and see that it exists, retrieve. Then validate */
    private boolean validate_provider(int ID){

        ProviderData provider = new ProviderData();
        ProviderData.status prov_status = null;

        try{
            //retrieve function takes in the ID and returns all the data associated w/provider.
             provider = ProviderData.retrieve(ID);

        }catch(SQLException e){

            System.out.print("This provider DNE in the system.\n");
            return false;
        }
        try{
            prov_status = ProviderData.validate(ID);
        }catch (SQLException e){
            System.out.print("Couldn't validate/find provider status\n");

        }
        //print the status of provider
        System.out.print("success, logging into session.\n" + "The provider status is \n");
        System.out.println(prov_status);
        return true;
    }

    private boolean validate_member(int ID) {

       MemberData member = new MemberData();
       PersonData.status mem_stat = null;

       try{
           member = MemberData.retrieve(ID);
       }catch (SQLException e){
           System.out.print("Member status cannot be reached as member DNE\n");
           return false;
       }
        //check if the value retrieved is valid. if its valid then we print their status
       try {
           mem_stat = MemberData.validate(ID);
       } catch (SQLException e) {
          System.out.print(" The member's ID is invalid as it does not exist .\n");
       }

       System.out.print("The members current status is");
       System.out.println(mem_stat);

       if(mem_stat != PersonData.status.INVALID){
           return true;
       }
       return false;
    }

    private boolean service_report(){
        System.out.print("Please enter the Member ID:\n");
        System.out.print("Please enter the Provider ID\n:");
        System.out.print("Please enter the Service Code:\n");
        System.out.print("Please enter the Member ID:\n");
        System.out.print("Please enter the Date: (MM/DD/YYYY)");
        System.out.print("Please enter any comments about the session:\n");
        //int member_id;
        //int provider_id;
        //int service_code;
        //Date date;
        //String comments;
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
