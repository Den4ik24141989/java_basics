import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    private static final Hashtable<String, Account> accounts = new Hashtable<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }
    public void addAccountToBank(Account account) {
        accounts.put(account.getAccNumber(), account);
    }
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        long verificationLimit = 50000;
        boolean check = false;
        if (accounts.get(fromAccountNum).getStatus()) {
            System.out.println("Аккаунт заблокирован");
            return;
        }
        if (getBalance(fromAccountNum) < amount) {
            System.out.println("Недостаточно средств для выполнения операции");
            return;
        }
        if (getBalance(fromAccountNum) >= amount && amount > verificationLimit) {
            System.out.println("Транзакция передана на проверку Службе Безопасности");
            try {
                check = isFraud(fromAccountNum, toAccountNum, amount);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            if (check) {
                accounts.get(fromAccountNum).blockedAccount();
                accounts.get(toAccountNum).blockedAccount();
                System.out.println("Служба Безопасности:\nаккаунты "
                        + fromAccountNum + " и " + toAccountNum + " заблокированы");
                return;
            }else System.out.println("Служба Безопасности - проверка пройдена успешно");
            transferMethod(fromAccountNum, toAccountNum, amount);
            }
        if (getBalance(fromAccountNum) >= amount && amount < verificationLimit) {
            transferMethod(fromAccountNum, toAccountNum, amount);
        }
    }
    public synchronized void getAccounts() {
        System.out.println(accounts.keySet());
    }
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getBankBalanceSheet() {
        return getSumAllAccounts();
    }

    public long getSumAllAccounts() {
        AtomicLong sumAllAccounts = new AtomicLong();
        accounts.forEach((s, account) -> {
            sumAllAccounts.addAndGet(account.getMoney());
        });
        return sumAllAccounts.get();
    }
    public synchronized void transferMethod (String fromAccountNum, String toAccountNum, long amount) {
        accounts.get(fromAccountNum).setMoney(getBalance(fromAccountNum) - amount);
        accounts.get(toAccountNum).setMoney(getBalance(toAccountNum) + amount);
        System.out.println("Перевод на сумму " + amount + " руб. с аккаунта " + fromAccountNum + " на аккаунт "
                + toAccountNum + " выполнен успешно");
    }
}
