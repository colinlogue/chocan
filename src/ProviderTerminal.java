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
    private static Scanner input;
    private static Scanner ID;
    private static Scanner session;
    private static Scanner M_ID;
    private static Scanner sess_charge;

    //Takes input from provider
    private ProviderTerminal(){
        input = new Scanner(System.in);
        ID = new Scanner(System.in);
        session = new Scanner(System.in);
        M_ID = new Scanner(System.in);
        sess_charge = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ProviderTerminal terminal = new ProviderTerminal();
        int task_num;

        //make a loop w/try catch inside so they can enter prov ID again
        System.out.print("Type a Provider ID to proceed to Provider tools. \n");
        int Prov_ID =  ID.nextInt();

        System.out.print("Type a member ID. \n");
        int Mem_ID =  M_ID.nextInt();

         //should only go into menu if provider and member validated
        if(terminal.validate_provider(Prov_ID) && terminal.validate_member(Mem_ID))
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

            } while (task_num != 4); //Loop should continue until user selects quit.
        }

   }
   //END OF MAIN

    /* I can give user fake ids to test that have already been made.
    types in the ID and see that it exists, retrieve. Then validate */
    private boolean validate_provider(int ID){

        ProviderData.status prov_status = null;

        try{
            prov_status = ProviderData.validate(ID);
        }catch (SQLException e){
            System.out.print("Couldn't validate/find provider status\n");
            return false;

        }
        //print the status of provider
        if(prov_status != PersonData.status.INVALID ){

            System.out.print("success, logging into session.\n" + "The provider status is ");
            System.out.println(prov_status);
            System.out.print("\n");
            return true;
        }else{
            System.out.print("Cannot allow access to terminal as provider is invalid. \n");
            return false;
        }
    }

    private boolean validate_member(int ID) {

       PersonData.status mem_stat = null;

//check if the value retrieved is valid. if its valid then we print their status
       try {
           mem_stat = MemberData.validate(ID);
       } catch (SQLException e) {
          System.out.print(" The member's ID is invalid as it does not exist .\n");
          return false;
       }

       System.out.print("The members current status is ");
       System.out.println(mem_stat);
       System.out.print("\n");

       if(mem_stat != PersonData.status.INVALID) {
           return true;
       }else{
         System.out.print(" This member has an invalid membership.Please contact the management. \n");
         return false;
       }
    }

    private boolean service_report(){           //gets info from the provider to write a session to the database

        try {
            SessionData session = new SessionData();
            //session.comments = new String();

            System.out.print("Member ID: \n");
            session.member_id = input.nextInt();
            System.out.print("Provider ID: \n");
            session.provider_id = input.nextInt();
            System.out.print("Service Code: \n");
            session.service_code = input.nextInt();
            session.date = new Date(System.currentTimeMillis());
            System.out.print("Date:"+ session.date+"\n");
            System.out.print("Additional Comments: \n");
            input.nextLine();
            session.comments = input.nextLine();

            session.write();                        //calls the session write fcn to write data to session.csv
            System.out.print("Session successfully written to disk\n\n");
            return true;
        }
        catch (SQLException e) {
            // handle case where loading fails
            System.out.print("Failed to write session.\n");
            return false;     //the report could not be written to database
        }
    }


    private void provider_dir_search(){

        System.out.print(" Enter six digit service code to retrieve it's corresponding fees and information. \n");
        int service_code = session.nextInt();
        Service S = new Service();

        try{
           S = ProviderDirectory.lookup(service_code);
        }catch (SQLException e){
            System.out.print(" The service code does not exist. Please consult a manager. \n");
        }

        System.out.print("Service name: \n");
        System.out.println(S.label);
        System.out.print("\n");
        System.out.print("Service code: \n");
        System.out.println(S.service_code);
        System.out.print("\n");
        System.out.print("Service fee: \n");
        System.out.println(S.fee);
        System.out.print("\n");

    }


//have the service report send the cost here. then the prov can retrieve it if they want.
    private boolean service_billing(){
        char answ = 'y';
        int total = 0;

        while(answ == 'y'){

            System.out.print("What services were provided to member today? Type a service code. \n");;
            int session_charge = sess_charge.nextInt();
            Service S = new Service();
            try{
                S = ProviderDirectory.lookup(session_charge);

            }catch(SQLException e){
                System.out.print("The service code does not exist. Please consult a manager. \n");
                return false;
            }
            System.out.print("Today's session currently cost ");
            System.out.print(total = total + S.fee);
            System.out.print("\n");
            System.out.print("Do you wish to add more charges today? Type y or n. \n");
            answ = sess_charge.next().charAt(0);
        }
        System.out.print("Your service's have been billed and added to your payment plan. \n");
        return true;
    }


 }
