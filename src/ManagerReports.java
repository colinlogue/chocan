import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ManagerReports {
    static public Scanner input;
    static public Scanner file_in;

    public ManagerReports() {
        input = new Scanner(System.in);
    }

    public static void main(String args[]){
        ManagerReports test = new ManagerReports();
        test.member_report();
    }

    protected void member_report() {
        try {
            file_in = new Scanner(new FileReader("README.md"));
            StringBuilder sb = new StringBuilder();

            while (file_in.hasNext()) {
                sb.append(file_in.nextLine()).append("\n");
            }
            System.out.println(sb.toString());
        }

        catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }

        //Notify user that an email has been sent
        System.out.println("A COPY OF THIS DOCUMENT HAS BEEN SENT TO YOUR EMAIL");
    }

    protected void provider_report() {
        try {
            file_in = new Scanner(new FileReader("README.md"));
            StringBuilder sb = new StringBuilder();

            while (file_in.hasNext()) {
                sb.append(file_in.nextLine()).append("\n");
            }
            System.out.println(sb.toString());
        }

        catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }

        //Notify user that an email has been sent
        System.out.println("A COPY OF THIS DOCUMENT HAS BEEN SENT TO YOUR EMAIL");
    }

    protected void accounts_payable() {
        try {
            file_in = new Scanner(new FileReader("README.md"));
            StringBuilder sb = new StringBuilder();

            while (file_in.hasNext()) {
                sb.append(file_in.nextLine()).append("\n");
            }
            System.out.println(sb.toString());
        }

        catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }

        //Notify user that an email has been sent
        System.out.println("A COPY OF THIS DOCUMENT HAS BEEN SENT TO YOUR EMAIL");
    }


}
