public class Main {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.values(6,5);
        System.out.println("Сумма значений: " + arithmetic.sum());
        System.out.println("Произведение значений: " + arithmetic.multiplication());
        System.out.println("Минимальное значение: " + arithmetic.min());
        System.out.println("Максимальное значение: " + arithmetic.max());
    }
}
