package Incheritance.src;

public class Operator implements Employee {
    private static final int FIX_SALARY = 30_000;

    public Operator() {
    }

    public int getMonthSalary() {
        return FIX_SALARY;
    }
}