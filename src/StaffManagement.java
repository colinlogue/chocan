import java.sql.SQLException;

//Class to add, remove, and update Member and Provider Data.

public class StaffManagement extends ManagerTools
{
    //Take out later
    public static void main(String[] args)
    {
       StaffManagement temp = new StaffManagement();
       //temp.update_staff(provider);
       //temp.add_staff(provider);
       //temp.remove_staff(provider);
    }

    //Prompt user for PersonData fields then downcast to ProviderData or MemberData
    //depending on the string passed in and write to database.
    public int add_staff(String staff_title)
    {
        int repeat;
        PersonData new_staff;

        //Instantiate PersonData as sub-class object
        if(staff_title.equals(provider))
            new_staff = new ProviderData();
        else
            new_staff = new MemberData();

        //Prompt user for PersonData fields
        new_staff.name = prompt_name(staff_title);
        System.out.println("Enter new " + staff_title + "'s address.");
        new_staff.address.street = prompt_street();
        new_staff.address.city = prompt_city();
        new_staff.address.state = prompt_state();
        new_staff.address.ZIP = prompt_zip();

        System.out.println();
        new_staff.display();

        //Downcast PersonData object and write to database
        //Return error code if write() throws an exception.
        if(new_staff instanceof ProviderData)
        {
            try{
                ProviderData providerData = (ProviderData) new_staff;
                providerData.write();
            }
            catch (SQLException e){
                //Just a place holder for now.
                System.out.println("Failed to write to database");
                return -1;
            }
        }
        else {
            try {
                MemberData memberData = (MemberData) new_staff;
                memberData.write();
            }
            catch (SQLException e){
                //Just a place holder for now.
                System.out.println("Failed to write to database");
                return -1;
            }
        }

        return 0;
    }

    //Removed Member or Provider Data dependant on the String passed in (Member or Provider)
    //returns -1 when retrieve or delete throws a SQL exception or when prompt_id fails;
    public int remove_staff(String staff_title)
    {
        int ident;
        PersonData staff;

        ident = prompt_id();
        if(ident < 0)
            return -1;

        //Use down casting to retrieve ID. Then delete the data corresponding to ID.

        if(staff_title.equals(provider))
        {
            try{
                ProviderData.retrieve(ident) ;
            }
            catch (SQLException e)
            {
                System.out.println(staff_title + " " + ident + " could not be found.");
                return -1;
            }

            try
            {
                ProviderData.delete(ident);
            } catch (SQLException e)
            {
                System.out.println("Failed to remove" + staff_title +
                        "(ID:" + ident + ").");
                return -1;
            }
        }
        else
        {
            try{
                MemberData.retrieve(ident) ;
            }
            catch (SQLException e)
            {
                System.out.println(staff_title + " " + ident + " could not be found.");
                return -1;
            }

            try{
                MemberData.delete(ident);
            }
            catch (SQLException e){
                System.out.println("Failed to remove" + staff_title +
                        "(ID:" + ident + ").");
                return -1;
            }
        }

        System.out.println(staff_title + "ID: " + ident
                + " was removed successfully.");

        return 0;
    }

    //Update ProviderData or MemberData according to the String passed in.
    //returns -1 when retrieve or delete throws a SQL exception or when prompt_id fails;
    private int update_staff(String staff_title)
    {
        int ident;
        int choice;
        int repeat;

        PersonData staff;
        ident = prompt_id();
        if(ident < 0)
            return -1;

        //Retrieve to PersonData object depending on the String argument passed in.
        if(staff_title.equals(provider)){
            try{
                staff = ProviderData.retrieve(ident);
            }
            catch (SQLException e)
            {
                System.out.println(staff_title + " " + ident + " could not be found.");
                return -1;
            }
        }
        else{
            try{
                staff = MemberData.retrieve(ident);
            }
            catch (SQLException e)
            {
                System.out.println(staff_title + " " + ident + " could not be found.");
                return -1;
            }
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
                    staff.name = prompt_street();
                    break;
                case 3:
                    staff.name = prompt_city();
                    break;
                case 4:
                    staff.name = prompt_state();
                    break;
                case 5:
                    staff.name = prompt_zip();
                    break;
                default:
                    return -1;
            }

            System.out.println();
            System.out.println("\nID: " + ident);
            staff.display();

            repeat = ask_to_repeat();

        }while(repeat == 1);

        //Throw exception and return error if write fails
        try{
            if(staff instanceof ProviderData)
            {
                ProviderData pData = (ProviderData) staff;
                pData.write();
            }
            else
            {
                MemberData mData = (MemberData) staff;
                mData.write();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to write to database");
            return -1;
        }

        return 0;
    }
}
