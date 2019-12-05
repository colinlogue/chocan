import java.sql.SQLException;

public class StaffManagement extends ManagerTools
{  //Prompts for new staff's (Manager or Provider) name and address information
    //change return type to indicate success or failure
    public static String provider = "Provider";
    public static String manager = "Manager";

    public static void main(String[] args)
    {
       StaffManagement temp = new StaffManagement();
       temp.update_staff(provider);
       //temp.add_staff(provider);
       //temp.remove_staff(provider);
    }

    public int add_staff(String staff_title)
    {
        int repeat;
        PersonData new_staff;

        if(staff_title.equals(provider))
            new_staff = new ProviderData();
        else
            new_staff = new MemberData();

        do
        {
            new_staff.name = prompt_name(staff_title);

            System.out.println("Enter new " + staff_title + "'s address.");
            new_staff.address.street = prompt_street();
            new_staff.address.city = prompt_city();
            new_staff.address.state = prompt_state();
            new_staff.address.ZIP = prompt_zip();

            //display and confirm if the information is correct with the user.
            System.out.println();
            new_staff.display();
            System.out.println();
            repeat = ask_to_repeat();
        }
        while(repeat == 1);

        //Include call to person.write() here after everything is done.
            //Do exception handling and maybe change return type for this method.
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
    //returns -1 when retrieve or delete throws a SQL exception
    public int remove_staff(String staff_title)
    {
        int ident;
        PersonData staff;

        ident = prompt_id();
        if(ident < 0)
            return -1;

        //Use down casting to retrieve ID
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

    private int update_staff(String staff_title)
    {
        int ident;
        int choice;
        int repeat;

        PersonData staff;
        ident = prompt_id();
        if(ident < 0)
            return -1;

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

        System.out.println("\nID: " + ident);
        System.out.println();
        staff.display();

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
            }

            System.out.println();
            System.out.println("\nID: " + ident);
            staff.display();

            repeat = ask_to_repeat();

        }while(repeat == 1);

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

