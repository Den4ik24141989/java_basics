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
          return x;
        } else return y;
    }
    public static int min() {
        if (x < y) {
            return x;
        } else return y;
    }
    public void printMax () {
        System.out.println("Большее значение " + max());
    }
        public void printMin () {
            System.out.println("Меньшее значение " + min());
        }
    }

