public class Account {
    public int balance;

    public Account(int balance) throws Exception {
        if(balance < 0 )
            throw new Exception("Negative balance not allower");
        this.balance = balance;
    }

    public double getMonthlyInterest() {
        if(this.balance > 0 && this.balance <= 100)
            return this.balance * 3/100;
        if(this.balance > 100 && this.balance <= 1000)
            return this.balance * 5/100;

        return this.balance * 7/100;
    }
}