import java.io.File;

public class SearchFilesInFolders {
    public static void main(String[] args) {
        String folderPath = "src/main/resources/data";
        getListFiles(folderPath);
    }
    public static void getListFiles (String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        if (list == null) return;
        for (File f : list ) {
            if (f.isFile() ) {
                System.out.println( "File: " + f.getName());
            }
            else {
                getListFiles(f.getAbsolutePath());
            }
        }
    }
}
