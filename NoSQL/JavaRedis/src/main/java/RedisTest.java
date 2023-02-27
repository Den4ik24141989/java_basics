public class RedisTest {

    private static RedisStorage redisStorage = new RedisStorage();
    private static final int SLEEP = 1000;
    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException {

        redisStorage.init();
        for (int i = 1; i <= 20; i++) {
             redisStorage.addUser("user " + i);
        }
        while (true) {
            String viewUser;
            int user;
            counter++;
            if (counter == 10) {
                user = (int) (redisStorage.getAllUsers().size() * Math.random());
                viewUser = redisStorage.getUser(user);
                System.out.println("Пользователь " + viewUser + " оплатил платную услугу" );
                counter = 0;
            } else {
                viewUser = redisStorage.getUser(0);
                user = 0;
            }
            queueView(user, viewUser);
            Thread.sleep(SLEEP);
        }
    }
    public static void queueView(int user, String viewUser) {
        System.out.println("На главной странице показываем " + viewUser);
        redisStorage.removeUser(user);
        redisStorage.addUser(viewUser);
    }
}
