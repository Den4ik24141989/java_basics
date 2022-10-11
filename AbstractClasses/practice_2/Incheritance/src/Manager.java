package Incheritance.src;

import java.util.Random;

public class Manager extends Company
implements Employee {
    Random random = new Random();
    public double getMonthSalary() {
        return super.getMonthSalary() + random.nextInt(115_000, 140_000) * 0.05;
    }
}