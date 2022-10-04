package Incheritance.src;

import java.util.ArrayList;

public class Company
        implements Employee {

    ArrayList<String> employees = new ArrayList<>();

    @Override
    public int getMonthSalary() {
        return 0;                                                   //месячная зарплата
    }

    @Override
    public void setHireAll(int count, String employee) {               //найм списка сотрудников
        for (int i = 1; i <= count; i++) {
            employees.add(employee + i);
        }
    }

    @Override
    public void setHire(String employee) {                       //найм одного сотрудника
        employees.add(employee);
    }

    @Override
    public void fire(String employee) {                         //увольнение сотрудника
        employees.remove(employee);
    }

    @Override
    public void getIncome() {                                   //получение значения дохода компании

    }

    public void getListOfEmployee () {                          //список сотрудников
        for (String list : employees) {
            System.out.println(list);
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {          //самые высокие зарплаты в компании
        return null;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {       //самые низкие зарплаты в компании
        return null;
    }
}

