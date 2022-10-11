package Incheritance.src;
import java.util.Scanner;

public class Main extends Company {
    public static String getEmployee() {
        int enter;
        String employee;
        while (true) {

            enter = new Scanner(System.in).nextInt();
            if (enter == 1) {
                employee = "Менеджер";
                break;
            }
            if (enter == 2) {
                employee = "Топ-Менеджер";
                break;
            }

            if (enter == 3) {
                employee = "Оператор";
                break;
            }

            if (enter > 3) {
                System.out.println("Неверный выбор, выберите возможную должность");
            }
        }
        return employee;
    }


    public static void main(String[] args) {
        String start = """
                Выберите действие:
                1.Добавить сотрудника
                2.Добавить список сотрудников
                3.Уволить сотрудника
                4.Показать всех сотрудников
                5.Показать доход компании
                6.Показать доход сотрудников
                7.Показать самые высокие зарплаты
                8.Показать самые низкие зарплаты
                9.Выйти из программы""";
        String choice = """
                Выберите должность
                1.Менеджер
                2.Топ-Менеджер
                3.Оператор""";
        Company company = new Company();

        while (true) {
            System.out.println(start);
            int enter = new Scanner(System.in).nextInt();

            if (enter == 1) {
                System.out.println(choice);
                String employee = getEmployee();
                company.setHire(employee);
            }

            if (enter == 2) {
                System.out.println(choice);
                String employee = getEmployee();
                System.out.println("Введите количество");
                int count = new Scanner(System.in).nextInt();
                company.setHireAll(count, employee);
            }

            if (enter == 3) {
                System.out.println(choice);
                String employee = getEmployee();
                company.fire(employee);
            }

            if (enter == 4 ) {
                company.getListOfEmployee();
            }

            if (enter == 5) {
                System.out.println(company.getIncome());
            }

            if (enter == 6) {
                for (String list : company.getSalaryStaff()) {
                    System.out.println(list + "руб.");
                }
            }

            if (enter == 7) {
                company.getTopSalaryStaff(5);
            }

            if (enter == 8) {
                company.getLowestSalaryStaff(5);
            }

            if (enter == 9) {
                break;
            }

            if (enter > 9 || enter <= 0) {
                System.out.println("Выберите из предложенных вариантов!");
            }
        }
    }
}