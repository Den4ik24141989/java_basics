package Incheritance.src;

import java.util.*;

public class Company {
    private final List<Employee> employees = new ArrayList<>();
    protected static int income;

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void hireAll(Collection<Employee> employeeList) {
        this.employees.addAll(employeeList);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public static int getIncome() {
        return income;
    }

    private List<Employee> getFilteredLimitedList(int count, Comparator<Employee> comparator) {
        List<Employee> copyList = new ArrayList<>(employees);
        Collections.sort(copyList, comparator);
        List<Employee> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(copyList.get(i));
        }
        return result;
    }

    public int countEmployees() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getFilteredLimitedList(count, (o1, o2) -> o2.getMonthSalary() - o1.getMonthSalary());
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getFilteredLimitedList(count, (o1, o2) -> o1.getMonthSalary() - o2.getMonthSalary());
    }
}