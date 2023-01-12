import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;

public class MainJDBC {
    public static void main(String[] args) {
        TreeSet<String> courseNameList = new TreeSet<>();
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "secret";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PurchaseList");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                courseNameList.add(courseName);
            }
            for (String course : courseNameList) {
                String select = "SELECT * FROM PurchaseList WHERE course_name = '" + course + "'";
                resultSet = statement.executeQuery(select);
                TreeSet <Integer> monthList = new TreeSet<>();
                double count = 0;
                while (resultSet.next()) {
                    String courseName = resultSet.getString("subscription_date");
                    String[] split = courseName.split("-");
                    int month = Integer.parseInt(split[1]);
                    monthList.add(month);
                    count += 1;
                }
                int countMonthSale = (monthList.last() - monthList.first()) + 1;
                double averageValueSalesCourse = count / countMonthSale;
                System.out.println(course + ": среднее значение продаж для курса " + averageValueSalesCourse +  " в месяц ");
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
