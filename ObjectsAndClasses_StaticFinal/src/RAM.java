public class RAM {
    private final RAM_type type;
    private final int memorySize;
    private final double weight;

    public RAM(RAM_type type, int memorySize, double weight) {
        this.type = type;
        this.memorySize = memorySize;
        this.weight = weight;
    }

    public RAM_type getType() {
        return type;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getWeight() {
        return weight;
    }

    public void getRAM() {
        System.out.println("Оперативная память: " + "тип " + getType() + " объём " + getMemorySize() + " ГБ");
    }
}
