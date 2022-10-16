package Incheritance.src;

public class Operator implements Employee {
    String job;
    private static final int FIX_SALARY = 30000;

    public Operator(String job) {
        this.job = job;
    }

    public int getMonthSalary() {
        return FIX_SALARY;
    }
}