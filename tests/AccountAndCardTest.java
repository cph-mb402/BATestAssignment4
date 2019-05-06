import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountAndCardTest {
    @Test
    void testGetMonthlyInterest() throws Exception {
        Account account1 = new Account(0);
        Account account2 = new Account(50);
        Account account3 = new Account(100);
        Account account4 = new Account(150);
        Account account5 = new Account(900);
        Account account6 = new Account(1000);
        Account account7 = new Account(1001);
        Account account8 = new Account(10000);

        assertEquals(account1.getMonthlyInterest(), 0);
        assertEquals(account2.getMonthlyInterest(), 50 * 3/100);
        assertEquals(account3.getMonthlyInterest(), 100 * 3/100);
        assertEquals(account4.getMonthlyInterest(), 150 * 5/100);
        assertEquals(account5.getMonthlyInterest(), 900 * 5/100);
        assertEquals(account6.getMonthlyInterest(), 1000 * 5/100);
        assertEquals(account7.getMonthlyInterest(), 1001 * 7/100);
        assertEquals(account8.getMonthlyInterest(), 10000 * 7/100);
    }

    @Test
    void testGetCardDiscount() throws Exception {
        Account account = new Account(150);
        Customer newCustomer = new Customer(account, new GregorianCalendar(), false, false);
        newCustomer.createCreditCard();
        Customer oldCustomer = new Customer(account, new GregorianCalendar(2015,2,25), false, false);
        oldCustomer.createCreditCard();
        Customer oldCustomerWithCoupon = new Customer(account, new GregorianCalendar(2015,2,25), false, true);
        oldCustomerWithCoupon.createCreditCard();
        Customer oldLoyalCustomer = new Customer(account, new GregorianCalendar(2015,2,25), true, false);
        oldLoyalCustomer.createCreditCard();
        Customer oldLoyalCustomerWithCoupon = new Customer(account, new GregorianCalendar(2015,2,25), true, true);
        oldLoyalCustomerWithCoupon.createCreditCard();

        assertEquals(newCustomer.card.getDiscount(), 15);
        assertEquals(oldCustomer.card.getDiscount(), 0);
        assertEquals(oldCustomerWithCoupon.card.getDiscount(), 20);
        assertEquals(oldLoyalCustomer.card.getDiscount(), 10);
        assertEquals(oldLoyalCustomerWithCoupon.card.getDiscount(), 30);
    }

    @RepeatedTest(10)
    void testGetCardDiscountRepeated() throws Exception {
        Account account = new Account(150);
        Customer newCustomer = new Customer(account, new GregorianCalendar(), false, false);
        newCustomer.createCreditCard();
        Customer oldCustomer = new Customer(account, new GregorianCalendar(2015,2,25), false, false);
        oldCustomer.createCreditCard();
        Customer oldCustomerWithCoupon = new Customer(account, new GregorianCalendar(2015,2,25), false, true);
        oldCustomerWithCoupon.createCreditCard();
        Customer oldLoyalCustomer = new Customer(account, new GregorianCalendar(2015,2,25), true, false);
        oldLoyalCustomer.createCreditCard();
        Customer oldLoyalCustomerWithCoupon = new Customer(account, new GregorianCalendar(2015,2,25), true, true);
        oldLoyalCustomerWithCoupon.createCreditCard();

        assertEquals(newCustomer.card.getDiscount(), 15);
        assertEquals(oldCustomer.card.getDiscount(), 0);
        assertEquals(oldCustomerWithCoupon.card.getDiscount(), 20);
        assertEquals(oldLoyalCustomer.card.getDiscount(), 10);
        assertEquals(oldLoyalCustomerWithCoupon.card.getDiscount(), 30);
    }

    @ParameterizedTest
    @ValueSource(
                    ints = {50, 150, 250, 500, 900, 1500}
                 )
    void testGetCardDiscountParameterized(Integer balance) throws Exception {
        Account account = new Account(balance);
        Customer newCustomer = new Customer(account, new GregorianCalendar(), false, false);
        newCustomer.createCreditCard();
        Customer oldCustomer = new Customer(account, new GregorianCalendar(2015,2,25), false, false);
        oldCustomer.createCreditCard();
        Customer oldCustomerWithCoupon = new Customer(account, new GregorianCalendar(2015,2,25), false, true);
        oldCustomerWithCoupon.createCreditCard();
        Customer oldLoyalCustomer = new Customer(account, new GregorianCalendar(2015,2,25), true, false);
        oldLoyalCustomer.createCreditCard();
        Customer oldLoyalCustomerWithCoupon = new Customer(account, new GregorianCalendar(2015,2,25), true, true);
        oldLoyalCustomerWithCoupon.createCreditCard();

        assertEquals(newCustomer.card.getDiscount(), 15);
        assertEquals(oldCustomer.card.getDiscount(), 0);
        assertEquals(oldCustomerWithCoupon.card.getDiscount(), 20);
        assertEquals(oldLoyalCustomer.card.getDiscount(), 10);
        assertEquals(oldLoyalCustomerWithCoupon.card.getDiscount(), 30);
    }

    @ParameterizedTest
    @CsvSource({
            "true, false, false, 15",
            "false, false, false, 0",
            "false, false, true, 20",
            "false, true, false, 10",
            "false, true, true, 30"
    })
    void testGetCardDiscountParameterizedCSV(Boolean newCustomer, Boolean loyaltyCard, Boolean coupon, Integer expectedResult) throws Exception {
        Account account = new Account(150);
        Calendar calendar = new GregorianCalendar(2015,2,25);
        if(newCustomer)
            calendar = new GregorianCalendar();
        Customer customer = new Customer(account, calendar, loyaltyCard, coupon);
        customer.createCreditCard();

        assertEquals(customer.card.getDiscount(), expectedResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/promotions-test-data.csv")
    void testGetCardDiscountParameterizedCSVFile(Boolean newCustomer, Boolean loyaltyCard, Boolean coupon, Integer expectedResult) throws Exception {
        Account account = new Account(150);
        Calendar calendar = new GregorianCalendar(2015,2,25);
        if(newCustomer)
            calendar = new GregorianCalendar();
        Customer customer = new Customer(account, calendar, loyaltyCard, coupon);
        customer.createCreditCard();

        assertEquals(customer.card.getDiscount(), expectedResult);
    }
}