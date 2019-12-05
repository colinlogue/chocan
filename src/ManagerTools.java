import java.util.Scanner;

public class ManagerTools
{
    static public Scanner input;

    public ManagerTools()
    {
        input = new Scanner(System.in);
    }

    //Prompt user for person (Manager or Provider) name and return
    //their name as a string.
    protected String prompt_name(String person)
    {
        boolean success = false;
        String name;

        do
        {
            System.out.print("Enter new " + person + "'s name: ");
            name = input.nextLine();
            if(isStringAlphabet(name))
                success = true;
            else
                System.out.println("Invalid name.");
        }
        while(!success);

        return name;
    }

    //Prompt user for person (Manager or Provider) street name and return
    //street as a string.
    protected String prompt_street()
    {
        boolean success = false;
        String street;

        do
        {
            System.out.print("Street: ");
            street = input.nextLine();
            if(isStringAlphanumeric(street))
                success = true;
            else
                System.out.println("Invalid street name. Use only alphabet, numbers , (.), or (').");
        }
        while(!success);

        return street;
    }

    //Prompt user for person (Manager or Provider) city name and return
    //city as a string.
    protected String prompt_city()
    {
        boolean success = false;
        String city;

        do
        {
            System.out.print("City: ");
            city = input.nextLine();
            if(isStringAlphabet(city))
                success = true;
            else
                System.out.println("Invalid city name. Use only alphabets.");
        }
        while(!success);

        return city;
    }

    //Prompt user for person (Manager or Provider) state name and return
    //state as a string.
    protected String prompt_state()
    {
        boolean success = false;
        String state;

        do
        {
            System.out.print("State: ");
            state = input.nextLine();
            if(isStringOnlyCaps(state))
                success = true;
            else
                System.out.println("Invalid state name. Use only capital letters.");
        }
        while(!success);

        return state;
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
                System.out.println("Invalid ZIP Code. Use only numbers");
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

    //Checks if string contains only alphabets and whitespace
    private static boolean isStringAlphabet(String str)
    {
       return ((!str.equals("")))
               && (str.matches("^[a-zA-Z.'\\s]*$"));
    }

    //Checks if strings contains only capital letters. No whitespace.
    private static boolean isStringOnlyCaps(String str)
    {
        return ((!str.equals(""))) && (str.matches("^[A-Z]*$"));
    }

    //Checks if string contains alphanumeric letters and whitespace.
    private static boolean isStringAlphanumeric(String str)
    {
        return ((!str.equals(""))) && (str.matches("^[a-zA-Z0-9.'\\s]*$"));
    }
}
