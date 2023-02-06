import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "/home/den/Рабочий стол/src";
        String dstFolder = "/home/den/Рабочий стол/dst";

        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();
        int quarter = files.length / 4;

        for (int i = 0; i < 4; i++) {
            File[] file;
            if (files.length - (quarter * i) < quarter * 2) {
                file = new File[files.length - (quarter * i)];
            } else {
                file = new File[quarter];
            }
            System.arraycopy(files, quarter * i, file, 0, file.length);
            ImageResizer imageResizer = new ImageResizer(newWidth, dstFolder, file, System.currentTimeMillis());
            imageResizer.start();
        }
    }
}