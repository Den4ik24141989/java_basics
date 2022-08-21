public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.add("Хлеб", 30, 2, 0.5);
        basket.add("Колбаса", 300, 1, 1.7);
        basket.add("Лопата", 250, 1, 2);
        basket.print("Milk");
        basket.getTotalPrice();
        basket.getTotalWeight();
    }
}
