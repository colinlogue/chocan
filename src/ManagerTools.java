import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerTools
{
    static public Scanner input = new Scanner(System.in);

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
            if(is_string_name(name))
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
            if(is_string_alphanumeric(street))
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
            if(is_string_name(city))
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
            state = input.nextLine().toUpperCase();
            if(is_string_alphabet(state))
                success = true;
            else
                System.out.println("Invalid state name. Use only alphabets.");
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

    //Prompt PersonData fields for a switch statement
    protected int prompt_person_datafields()
    {
        int choice;

        do{
            try{
                System.out.println("\nWhich field would you like to update?");
                System.out.println("1. Name");
                System.out.println("2. Street");
                System.out.println("3. City");
                System.out.println("4. State");
                System.out.println("5. Zip Code\n");

                System.out.print("Enter your choice 1 - 5: ");
                choice = input.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid Input");
                choice = 0;
            }
        }while (choice > 5 || choice < 1);

        return choice;
    }

    //Prompt user to enter ID number and read input
    //Return -1 if inputMismatchException is caught
    //Otherwise return id number entered by user
    protected int prompt_id()
    {
        int ident;

        try{
            System.out.print("Enter Identification number: ");
            ident = input.nextInt();
        }
        catch (InputMismatchException e){
            return -1;
        }

        return ident;
    }

    //Ask the user if they would like to re-enter the data they just entered.
    //Return 1 if yes, -1 on No or any invalid input
    protected int ask_to_repeat()
    {
        int answer = 0;
        int repeat;

        System.out.println("\nWould you like to repeat this process?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        do
        {
            try{
                System.out.print("Enter 1 or 2: ");
                answer = input.nextInt();
                repeat = 0;
            }catch (InputMismatchException e) {
               repeat = 1;
            }
        }
        while(repeat == 1);

        if(answer == 1)
            return answer;
        else
            return -1;
    }

    //Checks if string contains only alphabets and whitespace
    public static boolean is_string_name(String str)
    {
       return ((!str.equals("")))
               && (str.matches("^[a-zA-Z.'\\s]*$"));
    }

    //Checks if string contains only alphabets
    public static boolean is_string_alphabet(String str)
    {
        return ((!str.equals("")))
                && (str.matches("^[a-zA-Z]*$"));
    }

    //Checks if string contains alphanumeric letters and whitespace.
    public static boolean is_string_alphanumeric(String str)
    {
        return ((!str.equals(""))) && (str.matches("^[a-zA-Z0-9.'\\s]*$"));
    }
}
