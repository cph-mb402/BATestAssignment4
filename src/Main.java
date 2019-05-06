import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    public static void main(String [] args) throws Exception {
        Account account = new Account(0);
        Calendar createdDate = Calendar.getInstance();
        Customer customer = new Customer(account, createdDate, false, false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
//        System.out.println(sdf.format(Calendar.getInstance().getTime()));
        System.out.println(customer.isNewCustomer());

    }
}
