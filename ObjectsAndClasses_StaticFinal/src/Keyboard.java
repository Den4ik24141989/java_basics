public class Keyboard {
    private final KeyboardType type;
    private final Illumination illumination;
    private final int weight;

    public Keyboard(KeyboardType type, Illumination illumination, int weight) {
        this.type = type;
        this.illumination = illumination;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public Illumination getIllumination() {
        return illumination;
    }

    public int getWeight() {
        return weight;
    }

    public void getKeyboard() {
        System.out.println("Клавиатура: тип " + getType() + ", наличие подсветки " + getIllumination());
    }
}
