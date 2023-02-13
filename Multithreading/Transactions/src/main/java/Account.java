public class Account {

    private long money;
    private final String accNumber;
    private boolean isBlocked;

    public long getMoney() {
        return money;
    }

    public Account(String accNumber, long money) {
        this.money = money;
        this.accNumber = accNumber;
        isBlocked = false;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void blockedAccount() {
        isBlocked = true;
    }

    public boolean getStatus() {
        return isBlocked;
    }

    @Override
    public String toString() {
        String str = null;
        if (getStatus()) {
            str = "Аккаунт - " + getAccNumber() + ": заблокирован";
        }
        if (!getStatus()) {
            str = "Аккаунт - " + getAccNumber() + ": баланс - " + getMoney() + " руб.";
        }
        return str;
    }
}
