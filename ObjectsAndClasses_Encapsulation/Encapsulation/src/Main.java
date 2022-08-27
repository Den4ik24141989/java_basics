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
        CargoInformation cargoInformation = new CargoInformation(5, "Краснодар", true, "777аА", true, new Dimensions(4, 3, 2));
        System.out.println(cargoInformation.getDeliveryAddress() + "\n" + cargoInformation.getRegistrationNumber() + "\n" + cargoInformation.getDimensions().volume());
        CargoInformation cargoInformationAddress = cargoInformation.setDeliveryAddress("Москва");
        CargoInformation cargoInformationRegistrationNumber = cargoInformation.setRegistrationNumber("555Ab");
        CargoInformation cargoInformationDimensions = cargoInformation.setDimensions(new Dimensions(2, 2, 2));
        System.out.println();
        System.out.println(cargoInformationAddress.getDeliveryAddress() + "\n" + cargoInformationRegistrationNumber.getRegistrationNumber() + "\n" + cargoInformationDimensions.getDimensions().volume());
    }
}

