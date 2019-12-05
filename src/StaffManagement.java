import java.sql.SQLException;
import java.util.InputMismatchException;

public class StaffManagement extends ManagerTools
{  //Prompts for new staff's (Manager or Provider) name and address information
    //change return type to indicate success or failure
    public static String provider = "Provider";
    public static String manager = "Manager";

    private int add_staff(String staff_title)
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

            //Add display() and confirm if the information is correct with the user.
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

    public int remove_staff(String staff_title)
    {
        int ident;
        PersonData staff;

        if(staff_title.equals(provider))
            staff = new ProviderData();
        else
            staff = new MemberData();

        System.out.print("Enter Identification number of the " + staff_title + " to remove: ");
        ident = input.nextInt();

        //Use down casting to retrieve ID
        if(staff instanceof ProviderData)
        {
            try{
                staff = ProviderData.retrieve(ident) ;
            }
            catch (SQLException e)
            {
                System.out.println(ident + " is an invalid ID number.");
                return -1;
            }

            ProviderData pData = (ProviderData) staff;

            try{
                pData.delete(ident);
            }
            catch (SQLException e){
                System.out.println("Failed to remove" + staff_title +
                        "(ID:" + ident + ").");
                return -1;
            }
        }
        else
        {
            try{
                staff = MemberData.retrieve(ident) ;
            }
            catch (SQLException e)
            {
                System.out.println(ident + " is an invalid ID number.");
                return -1;
            }

            MemberData pData = (MemberData) staff;

            try{
                pData.delete(ident);
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

    /*
    private void update_staff(String staff_title)
    {
        int ident;
        PersonData

        System.out.print("Enter Identification number of the " + staff_title);
        ident = input.nextInt();


        if(staff_title.equals(provider)){
           ProviderData pData = ProviderData.retrieve(ident);
           try {
               pdata.re
           }
           catch (SQLException e) {
               System.out.println(staff_title + " with ID:" + ident + " could not be found.");
           }
        }
        else{

        }
    }
     */
}
