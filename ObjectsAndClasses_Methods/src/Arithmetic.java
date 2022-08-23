public class Arithmetic {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.values(6,5);
        System.out.println("Сумма значений: " + arithmetic.sum());
        System.out.println("Произведение значений: " + arithmetic.multiplication());
        System.out.println("Минимальное значение: " + arithmetic.min());
        System.out.println("Максимальное значение: " + arithmetic.max());
    }
    public int x;
    public int y;

    public void values(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int sum() {
        int sum = x + y;
        return sum;
    }

    public int multiplication() {
        int sum = x * y;
        return sum;
    }

    public int max() {
        if (x > y) {
            return x;
        }
        return y;
    }

    public int min() {
        if (x < y) {
            return x;
        }
        return y;
    }
}