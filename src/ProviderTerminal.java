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


public class ProviderTerminal{

    //insert switch statements with corresponding func calls. call text_menu from main
    protected void text_menu(){
    }
   //must pass validation in order to get to main menu
   protected boolean validate_member() {
        return false; //return a message that member reflects member status
    }
    protected boolean validate_provider() {
        return false; //return a message that member reflects provider status
    }
    protected boolean service_report(){
        return false; //the report could not be written to database
    }
    protected String provider_dir_search(){
        return null; //the service code DNE
    }
    // the providers way of documenting how much theyve worked, and cost based on service codes
    protected boolean service_billing(){
        return false;
    }
    public static void main(String[] args){

      int task_num = 4;
      ProviderTerminal terminal = new ProviderTerminal();

     System.out.print("Select from the following menu:\n" +
             " 1. Create member service report.\n" +
             " 2. Search for a service code and it's related info.\n"+
             " 3. Bill ChocAn for the current session.\n" +
             " 4. Quit current session. \n");


      if(terminal.validate_provider())
      {
        if(terminal.validate_member()){

         switch(task_num){  //grant access to main menu
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
       }
      }

    }
}
