/*============================================================
* This class provides the functions to run and display reports
* from the ChocAn Simulation system.
* This class contains:
*       member_report()
*       provider_report()
*       accounts_payable()
* Each function runs the associated report by checking local
* text files and created a weekly total file.
============================================================*/
import java.io.*;
import java.sql.*;
import java.util.*;

public class ManagerReports {
    static public Scanner input;
    static public Scanner file_in;

    private Vector<SessionData> member_reports;
    private Vector<SessionData> provider_reports;
    public ManagerReports() {
        input = new Scanner(System.in);
    }

    public static void main(String [] args){
        ManagerReports test = new ManagerReports();
        test.member_report();
    }

    protected void member_report() {
        int member_id;

        System.out.println("Please enter the Member ID: ");
        member_id = input.nextInt();
        MemberData member = null;
        try {
            member = retrieve_member(member_id);
        } catch (SQLException e) {
            System.out.println("MEMBER NOT FOUND");
        }
        if(member != null) {
            try {
                member_reports = SessionData.retrieve_all(member);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(member_id);
            member.display();
            if(member_reports.isEmpty())
                System.out.println("EMPTY");
            else {
                System.out.println(member_reports);
            }
        }

    }

    protected void provider_report() {
        int provider_id;
        System.out.println("Please enter the provider number: ");
        provider_id = input.nextInt();

        ProviderData provider = null;
        try {
            provider = retrieve_provider(provider_id);
        } catch (SQLException e) {
            System.out.println("PROVIDER NOT FOUND");
        }
        if(provider != null) {
            try {
                provider_reports = SessionData.retrieve_all(provider);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(provider_id);
            if(provider_reports.isEmpty())
                System.out.println("EMPTY");
            else
                System.out.println(provider_reports);
        }
    }

    protected void accounts_payable() {

    }

    public MemberData retrieve_member(int ident) throws SQLException
    {
        MemberData person = MemberData.retrieve(ident);
        return person;
    }
    public ProviderData retrieve_provider(int ident) throws SQLException
    {
        ProviderData person = ProviderData.retrieve(ident);
        return person;
    }
}
