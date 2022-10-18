package Incheritance.src;

public class TopManager implements Employee {
    public static int FIX_SALARY = 80_000;

    public int getMonthSalary() {
        return Company.getIncome() > 10_000_000 ? (int) (FIX_SALARY * 1.5) : FIX_SALARY;
    }
}