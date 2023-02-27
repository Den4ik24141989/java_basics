import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.List;

import static java.lang.System.out;

public class RedisStorage {
    private RedissonClient redisson;
    private RKeys rKeys;
    private RList<String> list;

    private final static String KEY = "list";

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        list = redisson.getList(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for (String key : keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    void addUser(String user) {
        list.add(user);
    }

    List<String> getAllUsers() {
        return list.range(0, -1);
    }

    String getUser(int position) {
        return list.get(position);
    }

    void removeUser(int position) {
        list.fastRemove(position);
    }

    void addAfter(String after, String user) {
        list.addAfter(after, user);
    }

    void addBefore(String before, String user) {
        list.addBefore(before, user);
    }

    void fastSet(int position, String user) {
        list.fastSet(position, user);
    }

    void remove(String name, int position) {
        list.remove(name, position);
    }
}
