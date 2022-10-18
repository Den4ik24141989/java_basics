package Incheritance.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Company {
    public static void main(String[] args) {
        String title = """
                1.Добавить сотрудника
                2.Добавить список сотрудников
                3.Самые высокие ЗП
                4.Самые низкие ЗП
                5.Уволить половину сотрудников
                6.Показать доход компании
                7.Выйти из программы""";
        Company company = new Company();
        while (true) {
            System.out.println(title);
            int enter = new Scanner(System.in).nextInt();
            if (enter == 1) {
                hireEmployees(company);
            }
            if (enter == 2) {
                hireAll(company);
            }
            if (enter == 3) {
                printHighestSalaries(company);
            }
            if (enter == 4) {
                printLowestSalaries(company);
            }
            if (enter == 5) {
                fireHalfEmployees(company);
            }

            if (enter == 6) {
                System.out.println(company.getIncome());
                ;
            }
            if (enter == 7) {
                break;
            }
        }
    }

    private static void fireHalfEmployees(Company company) {
        int countEmployees = company.countEmployees();
        for (int i = 0; i < countEmployees / 2; i++) {
            int index = (int) (Math.random() * company.countEmployees());
            Employee loser = company.getEmployees().get(index);
            String job = company.getEmployees().get(index).toString();
            String[] splitJob = job.split("@");
            if (splitJob[0].contains("Incheritance.src.Manager")) {
                Manager.MinusEarningsForCompany();
            }
            company.fire(loser);
        }
        System.out.println("Уволено " + countEmployees / 2 + " сотрудников");
    }

    private static void hireAll(Company company) {
        ArrayList<Employee> list = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            Employee operator = new Operator();
            list.add(operator);
        }
        for (int i = 0; i < 80; i++) {
            Employee manager = new Manager();
            list.add(manager);
        }
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager();
            list.add(topManager);
        }
        company.hireAll(list);
        System.out.println("Общее количество сотрудников " + company.countEmployees());
    }

    private static void hireEmployees(Company company) {
        System.out.println("""
                1.Operator
                2.Manager
                3.TopManager
                """);
        int enter = new Scanner(System.in).nextInt();
        if (enter == 1) {
            Employee operator = new Operator();
            company.hire(operator);
        }
        if (enter == 2) {
            Employee manager = new Manager();
            company.hire(manager);
        }
        if (enter == 3) {
            Employee topManager = new TopManager();
            company.hire(topManager);
        }
        System.out.println("Количество сотрудников в компании " + company.countEmployees());
    }

    private static void printHighestSalaries(Company company) {
        System.out.println("Самые высокие зарплаты: ");
        for (Employee employee : company.getTopSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + " руб");
        }
    }

    private static void printLowestSalaries(Company company) {
        System.out.println("Самые низкие зарплаты: ");
        for (Employee employee : company.getLowestSalaryStaff(5)) {
            System.out.println(employee.getMonthSalary() + " руб");
        }
    }
}