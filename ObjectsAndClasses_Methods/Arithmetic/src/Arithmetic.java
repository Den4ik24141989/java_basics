public class Arithmetic {
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