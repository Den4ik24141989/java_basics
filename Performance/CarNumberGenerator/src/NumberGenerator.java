import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class NumberGenerator extends Thread {
    private final long start;
    private int firstRegionCode;
    private int lastRegionCode;
    char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    FileOutputStream writer;
//    PrintWriter writer;


    public NumberGenerator(long start, int firstRegionCode, int lastRegionCode, FileOutputStream writer) throws FileNotFoundException {
        this.start = start;
        this.firstRegionCode = firstRegionCode;
        this.lastRegionCode = lastRegionCode;
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            for (int regionCode = firstRegionCode; regionCode <= lastRegionCode; regionCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter).append(padNumber(number, 3))
                                        .append(secondLetter).append(thirdLetter).append(padNumber(regionCode, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
//                writer.write(builder.toString().getBytes());
                writer.write(builder.toString().getBytes());
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Поток завершен за " + (System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
//        String numberStr = Integer.toString(number);
        String numberStr = "" + number;
        if (numberStr.length() < numberLength) {
            int padSize = numberLength - numberStr.length();
            for (int i = 0; i < padSize; i++) {
                numberStr = "0" + numberStr;
            }
        }
        return numberStr;
    }
}
