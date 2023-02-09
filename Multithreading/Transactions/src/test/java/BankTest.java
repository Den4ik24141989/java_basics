import junit.framework.TestCase;
import org.junit.Test;

public class BankTest extends TestCase {
    Bank bank;

    @Override
    protected void setUp() throws Exception {
        bank = new Bank();
        bank.addAccountToBank(new Account("1", 5_000));
        bank.addAccountToBank(new Account("2", 10_000));
        bank.addAccountToBank(new Account("3", 100_000));
    }

    @Test
    public void testInsufficientFundsTransfer() {
        bank.transfer("1", "2", 10_000);
        long actual = 10_000;
        long expected = bank.getBalance("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testMoneyTransfer() {
        bank.transfer("3", "2", 40_000);
        long actual = 50_000;
        long expected = bank.getBalance("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAllAccounts() {
        long actual = bank.getSumAllAccounts();
        long expected = 115_000;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBalance() {
        long actual = bank.getBalance("3");
        long expected = 100_000;
        assertEquals(expected, actual);
    }
}
