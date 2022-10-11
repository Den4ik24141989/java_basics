package Incheritance.src;

public class TopManager extends Company
        implements Employee {
    @Override
    public double getMonthSalary() {
        return super.getMonthSalary() * 1.5;

    }
}