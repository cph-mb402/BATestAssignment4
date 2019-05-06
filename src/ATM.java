public class ATM {
    public CreditCard card;

    public ATM() {
        this.card = null;
    }

    public void insertCard(CreditCard card) {
        this.card = card;
    }

    public void removeCard() {
        this.card = null;
    }

    public void depositAmount(int amount) throws Exception {
        if(this.card != null)
            this.card.account.balance += amount;
        else
            throw new Exception("No card inserted");
    }

    public void withdrawAmount(int amount) throws Exception {
        if(this.card != null)
            if(this.card.account.balance >= amount)
                this.card.account.balance -= amount;
            else
                throw new Exception("Balance too low");
        else
            throw new Exception("No card inserted");
    }

    public int showBalance() throws Exception {
        if(this.card != null)
            return this.card.account.balance;
        else
            throw new Exception("No card inserted");
    }

    public double showMonthlyInterestRate() {
        return this.card.account.getMonthlyInterest();
    }
}
