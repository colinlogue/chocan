import java.util.Scanner;

public class ManagerTools
{
    static public Scanner input;

    public ManagerTools()
    {
        input = new Scanner(System.in);
    }

    //Prompt user for person (Manager or Provider) Zip Code and return
    //zip code as a String.
    protected String prompt_zip()
    {
        boolean success;
        String zip = null;

        do
        {
            System.out.print("ZIP Code: ");
            try
            {
                zip = input.nextLine();
                Integer.parseInt(zip);
                success = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid ZIP Code");
                success = false;
            }
        }
        while(!success);

        return zip;
    }

    //Ask the user if they would like to re-enter the data they just entered.
    //Return 1 if yes, -1 on No or any invalid input
    protected int ask_to_repeat()
    {
        int answer;

        System.out.println("Would you like to re-enter the data?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        answer = input.nextInt();

        if(answer == 1)
            return answer;
        else
            return -1;
    }
}
