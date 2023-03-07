import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        FileOutputStream writer1 = new FileOutputStream("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers1.txt");
        FileOutputStream writer2 = new FileOutputStream("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers2.txt");
        FileOutputStream writer3 = new FileOutputStream("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers3.txt");
        FileOutputStream writer4 = new FileOutputStream("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers4.txt");

//        PrintWriter writer1 = new PrintWriter("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers1.txt");
//        PrintWriter writer2 = new PrintWriter("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers2.txt");
//        PrintWriter writer3 = new PrintWriter("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers3.txt");
//        PrintWriter writer4 = new PrintWriter("/home/den/Документы/Мой курс/Gitlab/java_basics/Performance/CarNumberGenerator/res/numbers4.txt");


        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        NumberGenerator numberGenerator1 = new NumberGenerator(start, 1, 25, writer1);
        numberGenerator1.start();

        NumberGenerator numberGenerator2 = new NumberGenerator(start, 26, 50, writer2);
        numberGenerator2.start();

        NumberGenerator numberGenerator3 = new NumberGenerator(start, 51, 75, writer3);
        numberGenerator3.start();

        NumberGenerator numberGenerator4 = new NumberGenerator(start, 76, 99, writer4);
        numberGenerator4.start();
    }
}
