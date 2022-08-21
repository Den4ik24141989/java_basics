public class Arithmetic {
    public static int x;
    public static int y;

    public void constructor(int x, int y) {
        Arithmetic.x = x;
        Arithmetic.y = y;
    }

    public static void sum() {
        System.out.println(x + y);

    }

    public static void multiplication() {
        System.out.println(x * y);
    }

    public static int max() {
        if (x > y) {
            System.out.println("Большее значение " + x);
            return x;
        } else System.out.println("Большее значение " + y);
        return y;
    }

    public static int min() {
        if (x < y) {
            System.out.println("Меньшее значение " + x);
            return y;
        } else System.out.println("Меньшее значение " + y);
        return x;
    }
}

