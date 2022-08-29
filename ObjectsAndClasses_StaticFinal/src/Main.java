public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer("Ноутбук MSI Modern 15 ", "B11M-003XRU черный ",
                new CPU(3, 2, "Intel Core i3-1115G4", 45.07),
                new RAM(RAM_type.DDR4, 8, 28),
                new Storage(StorageType.SSD, 256, 53),
                new Screen(15.6, ScreenType.IPS, 1800),
                new Keyboard(KeyboardType.MEMBRANE, Illumination.YES, 100));
        computer.getComputer();
        computer.getTotaWeight();

        Computer computer2 = new Computer("Ноутбук Apple ", "MacBook Pro серый",
                new CPU(4.1, 2, "Intel Core i3-1115G4", 45.07),
                new RAM(RAM_type.DDR4, 8, 20),
                new Storage(StorageType.SSD, 256, 48),
                new Screen(13.3, ScreenType.IPS, 1500),
                new Keyboard(KeyboardType.MEMBRANE, Illumination.YES, 70));
        computer2.getComputer();
        computer2.getTotaWeight();
    }
}
