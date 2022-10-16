package Incheritance.src;

public class TopManager extends Company
        implements Employee {
    String job;

    public TopManager(String job) {
        this.job = job;
    }

    public static final int FIX_SALARY = 60_000;

    public int getMonthSalary() {
        return getIncome() > 10_000_000 ? (int) (FIX_SALARY * 1.5) : FIX_SALARY;
    }
}