public class Screen {
    private final double diagonal;
    private final ScreenType type;
    private final double weight;

    public Screen(double diagonal, ScreenType type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public ScreenType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public void getScreen() {
        System.out.println("Экран: диагональ " + getDiagonal() + " дюймов," + " тип экрана " + getType());
    }
}
