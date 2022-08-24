//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Elevator elevator = new Elevator(-3, 26);
//        while (true) {
//            System.out.print("Введите номер этажа: ");
//            int floor = new Scanner(System.in).nextInt();
//            elevator.move(floor);
//        }
//    }
//}
public class Main {
    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(5, 6, 2);
        dimensions.volume();

        Dimensions dimensionsUpdateLength = dimensions.setLength(3);
        Dimensions dimensionsUpdateWidth = dimensions.setWidth(2);
        Dimensions dimensionsUpdateHeight = dimensions.setHeight(1);

        Dimensions copyDimensions = new Dimensions(dimensionsUpdateWidth.getWidth(), dimensionsUpdateHeight.getHeight(), dimensionsUpdateLength.getLength());
        copyDimensions.volume();
    }
}

