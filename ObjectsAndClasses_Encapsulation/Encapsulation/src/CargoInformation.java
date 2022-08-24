public class CargoInformation {
    private final double weight;
    private final String deliveryAddress;
    private final boolean turnOver;
    private final String registrationNumber;
    private final boolean fragility;

    public CargoInformation(double weight, String deliveryAddress, boolean turnOver, String registrationNumber, boolean fragility) {
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.turnOver = turnOver;
        this.registrationNumber = registrationNumber;
        this.fragility = fragility;
    }

    public double getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isTurnOver() {
        return turnOver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragility() {
        return fragility;
    }
}
