public class CPU {
    private final double frequency;
    private final int cores;
    private final String manufacturer;
    private final double weight;

    public CPU(double frequency, int cores, String manufacturer, double weight) {
        this.frequency = frequency;
        this.cores = cores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCores() {
        return cores;
    }

    public String getNanufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public void getCPU() {
        System.out.println("Частота процессора: " + getFrequency() + " ГГц," + " Количество ядер: " + getCores());
    }
}
