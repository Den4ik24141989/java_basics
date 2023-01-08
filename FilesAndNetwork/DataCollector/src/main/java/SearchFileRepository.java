import java.io.File;
import java.util.ArrayList;

public class SearchFileRepository {
    public ArrayList<String> jsonPathFileList = new ArrayList<>();
    public ArrayList<String> csvFilePathList = new ArrayList<>();

    public void getSortPath(String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        if (list == null) return;
        for (File doc : list) {
            if (doc.isFile()) {
                String pathFile = doc.getAbsolutePath();
                if (pathFile.endsWith("json")) {
                    jsonPathFileList.add(pathFile);
                }
                if (pathFile.endsWith("csv")) {
                    csvFilePathList.add(pathFile);
                }
            }
            else {
                getSortPath(doc.getAbsolutePath());
            }
        }
    }
}
