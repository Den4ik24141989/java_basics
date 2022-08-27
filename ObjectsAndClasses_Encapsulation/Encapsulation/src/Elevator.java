public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }
    private int getCurrentFloor() {
        return currentFloor;
    }
    private void moveDown() {
        currentFloor = getCurrentFloor() - 1;
    }
    private void moveUp() {
        currentFloor = getCurrentFloor() + 1;
    }
    void move(int floor) {
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
