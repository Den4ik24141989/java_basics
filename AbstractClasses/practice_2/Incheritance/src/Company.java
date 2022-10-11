package Incheritance.src;

import java.util.*;


public class Company implements Employee {

    ArrayList<String> employees = new ArrayList<>();
    ArrayList<String> salaryStaff = new ArrayList<>();

    Random random = new Random();
    double income;


    public void setHireAll(int count, String employee) {               //найм списка сотрудников
        Manager manager = new Manager();
        TopManager topManager = new TopManager();
        Operator operator = new Operator();
        for (int i = 1; i <= count; i++) {
            if (employee.contains("Менеджер")) {
                income = income + random.nextInt(115_000, 140_000);
                salaryStaff.add("" + manager.getMonthSalary());
            }
            if (employee.contains("Топ-Менеджер")) {
                if (getIncome() > 10_000_000) {
                    salaryStaff.add("" + topManager.getMonthSalary());
                } else {
                    salaryStaff.add("" + getMonthSalary());
                }
            }
            if (employee.contains("Оператор")) {
                salaryStaff.add("" + operator.getMonthSalary());
            }
            employees.add(employee);
        }
    }

    public void setHire(String employee) {                       //найм одного сотрудника
        Manager manager = new Manager();
        TopManager topManager = new TopManager();
        Operator operator = new Operator();
        if (employee.contains("Менеджер")) {
            income = income + random.nextInt(115_000, 140_000);
            salaryStaff.add("" + manager.getMonthSalary());
        }
        if (employee.contains("Топ-Менеджер")) {
            if (getIncome() > 10_000_000) {
                salaryStaff.add("" + topManager.getMonthSalary());
            } else {
                salaryStaff.add("" + getMonthSalary());
            }
        }
        if (employee.contains("Оператор")) {
            salaryStaff.add("" + operator.getMonthSalary());
        }
        employees.add(employee);
    }

    public void fire(String employee) {                         //увольнение сотрудника
        if (employee.equals("Менеджер")) {
            income = income - random.nextInt(115_000, 140_000);
        }
        employees.remove(employee);
        salaryStaff.remove(0);
    }

    public double getIncome() {                                   //получение значения дохода компании
        return income;
    }

    public void getListOfEmployee() {                          //список сотрудников
        int manager = 0;
        int topManager = 0;
        int operator = 0;
        for (String list : employees) {
            if (list.equals("Менеджер")) {
                manager += 1;
            }
            if (list.equals("Топ-Менеджер")) {
                topManager += 1;
            }
            if (list.equals("Оператор")) {
                operator += 1;
            }
        }
        System.out.println("Сотрудники компании" +
                "\nМенеджеров - " + manager +
                "\nТоп-менеджеров - " + topManager +
                "\nОператоров - " + operator);
    }

    public ArrayList<String> getSalaryStaff() {
        ArrayList<String> salaryStaff = new ArrayList<>();
        this.salaryStaff = salaryStaff;
        Manager manager = new Manager();
        TopManager topManager = new TopManager();
        Operator operator = new Operator();
        for (String list : employees) {
            if (list.contains("Менеджер")) {
                salaryStaff.add("" + manager.getMonthSalary());
            }
            if (list.contains("Топ-Менеджер")) {
                if (getIncome() > 10_000_000) {
                    salaryStaff.add("" + topManager.getMonthSalary());
                } else {
                    salaryStaff.add("" + getMonthSalary());
                }
            }
            if (list.contains("Оператор")) {
                salaryStaff.add("" + operator.getMonthSalary());
            }
        }
        return salaryStaff;
    }


    public void getTopSalaryStaff(int count) {          //самые высокие зарплаты в компании
        Collections.sort(salaryStaff);
        Collections.reverse(salaryStaff);
        if (count < salaryStaff.size()) {
            for (int i = 0; i < count; i++) {
                System.out.println(salaryStaff.get(i) + "руб.");
            }
        }
    }

    public void getLowestSalaryStaff(int count) {       //самые низкие зарплаты в компании
        Collections.sort(salaryStaff);
        if (count < salaryStaff.size()) {
            for (int i = 0; i < count; i++) {
                System.out.println(salaryStaff.get(i) + "руб.");
            }
        }
    }

    @Override
    public double getMonthSalary() {
        return fixSalary;
    }
}