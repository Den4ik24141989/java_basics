public class Storage {
    private final StorageType type;
    private final int memorySize;
    private final double weight;

    public Storage(StorageType type, int memorySize, double weight) {
        this.type = type;
        this.memorySize = memorySize;
        this.weight = weight;
    }

    public StorageType getType() {
        return type;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getWeight() {
        return weight;
    }

    public void getStorage() {
        System.out.println("Накопитель информации: " + getType() + " " + getMemorySize() + " ГБ");
    }
}
