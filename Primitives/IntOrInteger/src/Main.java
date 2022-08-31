public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());
        for (char i = 'А'; i <= 'Е'; i++) {
            System.out.println( (int) i + " - " + i);
        }
        System.out.println( (int) 'Ё' + " - " + 'Ё');
        for (char i = 'Ж'; i <= 'е'; i++) {
            System.out.println( (int) i + " - " + i);
        }
        System.out.println( (int) 'ё' + " - " + 'ё');
        for (char i = 'ж'; i <= 'я'; i++) {
            System.out.println( (int) i + " - " + i);
        }
    }
}
