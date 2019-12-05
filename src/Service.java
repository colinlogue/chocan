// This class is basically just a struct to return data

public class Service {

    public int service_code;
    public int fee;
    public String label;

    public String format_fee() {
        String fee_str = Integer.toString(fee);
        String start = fee_str.substring(0, fee_str.length() - 2);
        String end = fee_str.substring(fee_str.length() - 2);
        return '$' + start + "." + end;
    }

    public static void main(String[] args) {
        Service s = new Service();
        s.fee = 1325;
        System.out.println(s.format_fee());
    }

}
