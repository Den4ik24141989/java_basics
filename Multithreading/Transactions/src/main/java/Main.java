public class Main {
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        for (int i = 0; i < 100; i++) {
            bank.addAccountToBank(new Account("" + i, 1_000_000));
        }
        for (int i = 0; i < 99; i++) {
            new Thread(() -> {
                int random = (int) (Math.random() * 100);
                String randomStr = String.valueOf(random);
                int random2 = (int) (Math.random() * 100);
                String randomStr2 = String.valueOf(random2);
                int random3 = (int) (Math.random() * 100000);


                bank.transfer(randomStr, randomStr2, random3);
                System.out.println("Баланс = " + bank.getBalance(randomStr) + " руб");
            }).start();
        }
    }
}
