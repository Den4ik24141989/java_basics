public class CargoInformation {
    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;
    private final boolean turnOver;
    private final String registrationNumber;
    private final boolean fragility;

    public CargoInformation(
            double weight,
            String deliveryAddress,
            boolean turnOver,
            String registrationNumber,
            boolean fragility,
            Dimensions dimensions) {
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.turnOver = turnOver;
        this.registrationNumber = registrationNumber;
        this.fragility = fragility;
        this.dimensions = dimensions;
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

    public Dimensions getDimensions() {
        return dimensions;
    }

    public CargoInformation setDimensions(Dimensions dimensions) {
        return new CargoInformation(weight, deliveryAddress, turnOver, registrationNumber, fragility, dimensions);
    }

    public CargoInformation setWeight(double weight) {
        return new CargoInformation(weight, deliveryAddress, turnOver, registrationNumber, fragility, dimensions);
    }

    public CargoInformation setDeliveryAddress(String deliveryAddress) {
        return new CargoInformation(weight, deliveryAddress, turnOver, registrationNumber, fragility, dimensions);
    }

    public CargoInformation setTurnOver(boolean turnOver) {
        return new CargoInformation(weight, deliveryAddress, turnOver, registrationNumber, fragility, dimensions);
    }

    public CargoInformation setRegistrationNumber(String registrationNumber) {
        return new CargoInformation(weight, deliveryAddress, turnOver, registrationNumber, fragility, dimensions);
    }

    public CargoInformation setFragility(boolean fragility) {
        return new CargoInformation(weight, deliveryAddress, turnOver, registrationNumber, fragility, dimensions);
    }
}
