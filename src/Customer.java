import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Customer {
    public Account account;
    public CreditCard card;
    public Calendar created;
    public boolean loyaltyCard;
    public boolean coupon;

    public Customer(Account account, Calendar created, boolean loyaltyCard, boolean coupon) {
        this.account = account;
        this.created = created;
        this.loyaltyCard = loyaltyCard;
        this.coupon = coupon;
    }

    public boolean isNewCustomer() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        if(sdf.format(this.created.getTime()).equals(sdf.format(Calendar.getInstance().getTime()))) {
            return true;
        }
        return false;
    }

    public void createCreditCard() {
        this.card = new CreditCard(this.account, this);
    }
}
