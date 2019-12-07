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
import java.util.concurrent.atomic.AtomicInteger;

public class ManagerReports {
    static public Scanner input;

    private Vector<SessionData> member_reports;
    private Vector<SessionData> provider_reports;

    public ManagerReports() {
        input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ManagerReports test = new ManagerReports();
        test.accounts_payable();
    }

    //Creates a report based on member ID input
    //Displays report and prints to <memberID>weekly.txt
    protected void member_report() {
        int member_id;  //holds user input for search

        System.out.println("Please enter the Member ID: ");
        member_id = input.nextInt();

        MemberData member = null;   //holds member data fro DB
        try {
            member = retrieve_member(member_id);    //retrieve from DB
        } catch (SQLException e) {
            System.out.println("MEMBER NOT FOUND"); //if member doesn't exist
        }
        if (member != null) {    //if member info retrieved from DB
            try {
                //load sessions that member has recieved
                member_reports = SessionData.retrieve_all(member);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println(member_id);  //display member ID
            member.display();       //display member info
            if (member_reports.isEmpty())
                System.out.println("NO VISITS THIS WEEK");
            else {
                try {
                    create_member_report(member, member_reports);
                } catch (IOException e) {
                    System.out.println(member_id + "weekly.txt FAILED TO WRITE");
                }
            }
        }
        System.out.println("\nCOPY OF THIS DOCUMENT ALSO SENT TO EMAIL");
    }

    //Creates a provider report based on provider id input
    //Displays report and writes to <providerID>weekly.txt
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
        if (provider != null) {
            try {
                provider_reports = SessionData.retrieve_all(provider);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(provider_id);
            provider.display();
            if (provider_reports.isEmpty())
                System.out.println("NO VISITS THIS WEEK");
            else {
                try {
                    create_provider_report(provider, provider_reports);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("\nCOPY OF THIS DOCUMENT ALSO SENT TO EMAIL");
    }

    //Creates a report of all Providers visited
    //Displays report to screen and saves to accounts_payable.txt
    protected void accounts_payable() {
        int total_visits = 0;
        int all_fees = 0;
        int providers = 0;
        ProviderData provider = null;
        MemberData member = null;
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("accounts_payable.txt"));
            for (int i = 900001; i < 900006; ++i) {
                try {
                    provider = retrieve_provider(i);
                } catch (SQLException e) {
                }
                try {
                    provider_reports = SessionData.retrieve_all(provider);
                } catch (SQLException e) {
                }
                if (!provider_reports.isEmpty()) {
                    ++providers;
                    int total_fee;
                    write.write("Provider ID: " + i + "\n");
                    System.out.println("Provider ID: " + i);
                    total_visits += provider_reports.size();
                    write.write("Visits: " + provider_reports.size() + "\n");
                    System.out.println("Visits: " + provider_reports.size());
                    total_fee = provider_reports.stream().mapToInt(this::calculate_fees).sum();
                    all_fees += total_fee;
                    write.write("Provider Fees: " + total_fee + "\n");
                    System.out.println("Provider Fees: " + total_fee);
                }
            }
            write.write("Total Providers: " + providers + "\n");
            System.out.println("\nTotal Providers: " + providers);
            write.write("Total Visits: " + total_visits + "\n");
            System.out.println("Total Visits: " + total_visits);
            write.write("Total Fees: " + all_fees + "\n");
            System.out.println("Total Fees: " + all_fees);
            write.close();
        }catch(IOException e){
            System.out.println("accounts_payable.txt WRITE FAILED");
        }


        System.out.println("\nCOPY OF THIS DOCUMENT ALSO SENT TO EMAIL");
    }

    private int calculate_fees(SessionData session){
        Service service = new Service();
        try {
            service = ProviderDirectory.lookup(session.service_code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service.fee;
    }

    private MemberData retrieve_member(int ident) throws SQLException {
        return MemberData.retrieve(ident);
    }

    private ProviderData retrieve_provider(int ident) throws SQLException {
        return ProviderData.retrieve(ident);
    }

    private void create_member_report(MemberData person, Vector<SessionData> reports) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter(person.ident + "weekly.txt"));
        write.write("Name: " + person.name + "\n");
        System.out.println("Name: " + person.name);
        write.write("ID: " + person.ident + "\n");
        System.out.println("ID: " + person.ident + "\n");
        write.write("Address: " + person.address.street + " " + person.address.city +
                ", " + person.address.state + " " + person.address.ZIP + "\n");
        System.out.println("Address: " + person.address.street + " " + person.address.city +
                ", " + person.address.state + " " + person.address.ZIP);
        reports.forEach((n) -> {
            try {
                write_member_report(write, n);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
        write.close();
    }

    //Writes and displays provider report
    //creates eft file for provider <providerid>eft.txt
    private void create_provider_report(ProviderData person, Vector<SessionData> reports) throws IOException {
        AtomicInteger fee_total = new AtomicInteger();
        BufferedWriter write = new BufferedWriter(new FileWriter(person.ident + "weekly.txt"));
        write.write("Name: " + person.name + "\n");
        write.write("ID: " + person.ident + "\n");
        write.write("Address: " + person.address.street + " " + person.address.city +
                ", " + person.address.state + " " + person.address.ZIP + "\n");
        reports.forEach((n) -> {
            try {
                fee_total.addAndGet(write_provider_report(write, n));
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
        write.write("Total Visits: " + provider_reports.size() + "\n");
        System.out.println("Total Visits: " + provider_reports.size());
        write.write("Fee Total: " + fee_total + "\n");
        System.out.println("Fee Total: " + fee_total);
        write.close();

        //Creates EFT file
        BufferedWriter write_again = new BufferedWriter(new FileWriter(person.ident + "eft.txt"));
        write_again.write(String.valueOf(fee_total));
        write_again.close();
    }

    private void write_member_report(BufferedWriter write, SessionData session) throws IOException, SQLException {
        ProviderData provider;
        write.append("Date of Service: ").append(String.valueOf(session.date));
        System.out.println("Data of Service: " + session.date);
        write.append("Provider ID: " + session.provider_id + "\n");
        System.out.println("Provider ID: " + session.provider_id);
        provider = ProviderData.retrieve(session.provider_id);
        write.append("Provider Name: " + provider.name + "\n");
        System.out.println("Provider Name: " + provider.name);
        write.append("Service Code: " + session.service_code + "\n");
        System.out.println("Service Code: " + session.service_code);
    }

    private int write_provider_report(BufferedWriter write, SessionData session) throws IOException, SQLException {
        MemberData member;
        write.append("Date of Service: " + session.date + "\n");
        System.out.println("Date of Service: " + session.date);
        write.append("Provider ID: " + session.provider_id + "\n");
        System.out.println("Provider ID: " + session.provider_id);
        write.append("Member ID: " + session.member_id + "\n");
        System.out.println("Member ID: " + session.member_id);
        member = MemberData.retrieve(session.member_id);
        write.append("Member Name: " + member.name + "\n");
        System.out.println("Member Name: " + member.name);
        write.append("Service Code: " + session.service_code + "\n");
        System.out.println("Service Code: " + session.service_code);
        Service service = new Service();
        try {
            service = ProviderDirectory.lookup(session.service_code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        write.append("Service Fee: " + service.fee + "\n");
        System.out.println(service.fee);
        return service.fee;

    }
}
