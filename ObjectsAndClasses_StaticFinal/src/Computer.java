public class Computer {
    public final String vendor;
    public final String name;
    public final CPU cpu;
    public final RAM ram;
    public final Storage storage;
    public final Screen screen;
    public final Keyboard keyboard;

    public Computer(String vendor, String name, CPU cpu, RAM ram, Storage storage, Screen screen, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.screen = screen;
        this.keyboard = keyboard;
    }

    public CPU setCpu(double frequency, int cores, String manufacturer, double weight) {
        return new CPU(frequency, cores, manufacturer, weight);
    }

    public RAM setRam(RAM_type type, int memorySize, double weight) {
        return new RAM(type, memorySize, weight);
    }

    public Storage setStorage(StorageType type, int memorySize, double weight) {
        return new Storage(type, memorySize, weight);
    }

    public Screen setScreen(double diagonal, ScreenType type, double weight) {
        return new Screen(diagonal, type, weight);
    }

    public Keyboard setKeyboard(KeyboardType type, Illumination illumination, int weight) {
        return new Keyboard(type, illumination, weight);
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public Screen getScreen() {
        return screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void getComputer() {
        System.out.println("\nКомпьютер: " + getVendor() + getName());
        cpu.getCPU();
        ram.getRAM();
        storage.getStorage();
        screen.getScreen();
        keyboard.getKeyboard();
    }

    public void getTotaWeight() {
        System.out.println("Общий вес компьютера: " + (storage.getWeight() + screen.getWeight() + keyboard.getWeight() + ram.getWeight() + cpu.getWeight()));
    }
}
