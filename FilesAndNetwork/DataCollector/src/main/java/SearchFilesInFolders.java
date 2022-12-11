import java.io.File;
import java.util.ArrayList;

public class SearchFilesInFolders {
    public static ArrayList<String> pathJSON = new ArrayList<>();
    public static ArrayList<String> pathCSV = new ArrayList<>();
    static int numJSON = 0;
    static int numCSV = 0;

    public SearchFilesInFolders() {
    }

    public static void main(String[] args) {
        String folderPath = "src/main/resources/data";
        getListFiles(folderPath);
    }
    public static void getListFiles(String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        if (list == null) return;
        for (File f : list ) {
            if (f.isFile() ) {
                String pathFile = f.getAbsolutePath();
                if (pathFile.endsWith("json")) {
                    numJSON += 1;
                    pathJSON.add(pathFile);
                    System.out.println( "JSON-file " + numJSON + " - " + f.getAbsolutePath());
                }
                if (pathFile.endsWith("csv")) {
                    numCSV += 1;
                    pathCSV.add(pathFile);
                    System.out.println( "CSV-file " + numCSV + " - " + f.getAbsolutePath());
                }
            }
            else {
                getListFiles(f.getAbsolutePath());
            }
        }
    }
}
