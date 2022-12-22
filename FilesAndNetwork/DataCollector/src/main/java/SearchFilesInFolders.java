import java.io.File;
import java.util.ArrayList;

public class SearchFilesInFolders {
    public ArrayList<String> jsonFilesPath = new ArrayList<>();
    public ArrayList<String> csvFilesPath = new ArrayList<>();

    public void addListFiles(String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        if (list == null) return;
        for (File f : list ) {
            if (f.isFile() ) {
                String pathFile = f.getAbsolutePath();
                if (pathFile.endsWith("json")) {
                    jsonFilesPath.add(pathFile);
                }
                if (pathFile.endsWith("csv")) {
                    csvFilesPath.add(pathFile);
                }
            }
            else {
                addListFiles(f.getAbsolutePath());
            }
        }
    }
}
