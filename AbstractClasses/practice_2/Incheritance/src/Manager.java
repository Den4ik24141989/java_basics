package Incheritance.src;

public class Manager extends Company
        implements Employee {
    String job;
    private static final int FIX_SALARY = 100_000;
    private static int incomeForCompany = 0;

    public int getMonthSalary() {
        return getIncome() > 10_000_000 ? (int) (FIX_SALARY + (incomeForCompany * 0.05)) : FIX_SALARY;
    }

    public void getMinusMonthSalary() {
        income = getIncome() - (int) (Math.random() * 25000) + 115000;
    }

    public Manager(String job) {
        this.job = job;
        int result = (int) (Math.random() * 25000) + 115000;
        this.incomeForCompany = result;
        income = getIncome() + result;
    }
}