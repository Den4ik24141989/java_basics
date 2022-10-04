import java.util.ArrayList;

public class Company
implements Employee {

    @Override
    public int getMonthSalary() {
        return 0;                                                   //месячная зарплата
    }

    public class Employee {
        ArrayList<String> employees = new ArrayList<>();


        public void setHire(String employee) {                       //найм одного сотрудника
            employees.add(employee);
        }

        public void hireAll(int count, String employee) {            //найм списка сотрудников
            for (int i = 1; i <= count; i++) {
                employees.add(i, employee + i);
            }
        }

        public void fire(String employee) {                         //увольнение сотрудника
            employees.remove(employee);
        }

        public void getIncome() {                                   //получение значения дохода компании

        }

        public ArrayList<Employee> getTopSalaryStaff(int count) {          //самые высокие зарплаты в компании
            return null;
        }

        public ArrayList<Employee> getLowestSalaryStaff(int count) {       //самые низкие зарплаты в компании
            return null;
        }
    }
}
