import java.sql.SQLException;
import java.util.InputMismatchException;

//Class to add, remove, and update Member and Provider Data.

public class StaffManagement extends ManagerTools
{
    public static String provider = "Provider";
    public static String member = "Member";

    //Take out later
    public static void main(String[] args)
    {
       StaffManagement temp = new StaffManagement();
       //temp.update_staff(provider);
       temp.add_staff(provider);
       temp.remove_staff(provider);
    }

    //Prompt user for PersonData fields then downcast to ProviderData or MemberData
    //depending on the string passed in and write to database.
    public int add_staff(String staff_title)
    {
        int rc;
        PersonData new_staff;

        //Instantiate PersonData as sub-class object
        if(staff_title.equals(provider))
            new_staff = new ProviderData();
        else if(staff_title.equals(member))
            new_staff = new MemberData();
        else
            return -1;

        //Prompt user for PersonData fields
        new_staff.name = prompt_name(staff_title);
        System.out.println("Enter new " + staff_title + "'s address.");
        new_staff.address.street = prompt_street();
        new_staff.address.city = prompt_city();
        new_staff.address.state = prompt_state();
        new_staff.address.ZIP = prompt_zip();

        System.out.println();
        new_staff.display();

        rc = add_staff(new_staff);
        if(rc < 0) {
            System.out.println("Failed to write to database");
            return -1;
        }

        return 0;
    }

    //Downcast PersonData object and write to database
    //Return -1 if write() throws an exception.
    //Otherwise return 0 on success.
    public int add_staff(PersonData new_staff)
    {
        try{
            if(new_staff instanceof ProviderData)
            {
                ProviderData providerData = (ProviderData) new_staff;
                providerData.write();
            }
            else if(new_staff instanceof MemberData){
                MemberData memberData = (MemberData) new_staff;
                memberData.write();
            }
            else
                return -1;
        } catch (SQLException e){
                return -1;
        }

        return 0;
    }

    //Removed Member or Provider Data dependant on the String passed in (Member or Provider)
    //returns -1 when retrieve or delete throws a SQL exception or when prompt_id fails;
    public int remove_staff(String staff_title)
    {
        int ident;
        int rc;

        ident = prompt_id();
        if(ident < 0)
            return -1;

        rc = remove_staff(staff_title, ident);
        if(rc < 0)
            System.out.println(staff_title + " " + ident + " could not be found and/or deleted.");


        System.out.println(staff_title + "ID: " + ident
                + " was removed successfully.");

        return 0;
    }

    //Checks staff_title and removed appropriate staff from ProviderData or MemberData
    //depending on their id number (ident).
    //Returns -1 if retrieve or delete throws exception or staff_title is not
    //"Provider" or "Member"
    //Returns 0 on success
    public int remove_staff(String staff_title, int ident){

        //Use down casting to retrieve ID. Then delete the data corresponding to ID.
        try{
            if(staff_title.equals(provider)) {
                ProviderData.retrieve(ident);
                ProviderData.delete(ident);
            }
            else if(staff_title.equals(member)) {
                MemberData.retrieve(ident);
                MemberData.delete(ident);
            }
            else
                return -1;
        }
        catch (SQLException e)
        {
                return -1;
        }

        return 0;
    }

    //Retrieve MemberData or Provider onto a local PersonData object
    // denping on the strin passed-in and return the local PersonData object.
    public PersonData retrieve_staff(String staff_title, int ident) throws SQLException
    {
        PersonData staff;

        try{
            if(staff_title.equals(provider))
                staff = ProviderData.retrieve(ident);
            else if(staff_title.equals(member))
                staff = MemberData.retrieve(ident);
            else
                return null;
        }
        catch (SQLException e)
        {
            System.out.println(staff_title + " " + ident + " could not be found.");
            throw e;
        }

        return staff;
    }

    //Update ProviderData or MemberData according to the String passed in.
    //returns -1 when retrieve or delete throws a SQL exception or when prompt_id fails;
    public int update_staff(String staff_title)
    {
        int ident;
        int choice;
        int repeat;

        PersonData staff;
        ident = prompt_id();
        if(ident < 0)
            return -1;

        try {
            staff = retrieve_staff(staff_title, ident);
        }
        catch (SQLException e){
            System.out.println(staff_title + " with ID:" + ident + " could not be found.");
            return -1;
        }

        //Print the data of retrieved object
        System.out.println("\nID: " + ident);
        System.out.println();
        staff.display();

        //Prompt PersonData fields and have the user choose what field they want to change.
        do
        {
            choice = prompt_person_datafields();
            input.nextLine();

            switch(choice)
            {
                case 1:
                    staff.name = prompt_name(staff_title);
                    break;
                case 2:
                    staff.address.street = prompt_street();
                    break;
                case 3:
                    staff.address.city = prompt_city();
                    break;
                case 4:
                    staff.address.state = prompt_state();
                    break;
                case 5:
                    staff.address.ZIP = prompt_zip();
                    break;
                default:
                    return -1;
            }

            System.out.println();
            System.out.println("\nID: " + ident);
            staff.display();

            repeat = ask_to_repeat();

        }while(repeat == 1);

        int rc = add_staff(staff);
        if(rc < 0) {
            System.out.println("Failed to write to database");
            return -1;
        }

        return 0;
    }
}

