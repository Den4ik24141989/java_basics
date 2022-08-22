public class Main {

    public static void main(String[] args) {
        Basket denis = new Basket();
        denis.add("Milk", 40);
        denis.add("Хлеб", 30, 2, 0.5);
        denis.add("Колбаса", 300, 1, 1.7);
        denis.add("Лопата", 250, 1, 2);
        denis.print("Корзина Дениса");
        denis.getTotalPrice();
        denis.getTotalWeight();
        Basket nastja = new Basket();
        nastja.add("Хлебцы", 40, 1,0.15);
        nastja.add("Йогурт", 38, 3, 0.1);
        nastja.print("\nКорзина Анастасии");
        nastja.getTotalPrice();
        nastja.getTotalWeight();
    }
}
