public class Elevator {
    public int currentFloor = 1;
    public int minFloor;
    public int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
    public void moveDown() {
        currentFloor = getCurrentFloor() - 1;
    }
    public void moveUp() {
        currentFloor = getCurrentFloor() + 1;
    }
    public void move(int floor) {
        if (floor == getCurrentFloor()) {
            return;
        }
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Нет такого этажа, введите номер от " + minFloor + " до " + maxFloor);
            return;
        }
        if (floor < getCurrentFloor()) {
            while (getCurrentFloor() > floor) {
                if (getCurrentFloor() != floor) {
                    moveDown();
                    System.out.println("Текущий этаж " + getCurrentFloor());
                }
            }
        }
        if (floor > getCurrentFloor()) {
            while (getCurrentFloor() < floor) {
                if (getCurrentFloor() != floor) {
                    moveUp();
                    System.out.println("Текущий этаж " + getCurrentFloor());
                }
            }
        }
    }
}
