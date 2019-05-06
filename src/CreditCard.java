public class CreditCard {
    public Account account;
    public Customer customer;
    public CreditCard(Account account, Customer customer) {
        this.account = account;
        this.customer = customer;
    }

    public int getDiscount() {
        int discount = 0;
        if(this.customer.isNewCustomer())
            return 15;
        else
            if(this.customer.loyaltyCard)
                discount += 10;
            if(this.customer.coupon)
                discount += 20;

        return discount;
    }
}
