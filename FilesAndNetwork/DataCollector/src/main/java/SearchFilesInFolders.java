import java.io.File;

public class SearchFilesInFolders {
    public static void main(String[] args) {
        String folderPath = "src/main/resources/data";
        File file = new File(folderPath);
        getFileFolder(file);
    }


    public static void getFileFolder (File folder) {
        try {
            if (folder.isFile()) {
                return;
            }
            int sum = 0;
            File[] files = folder.listFiles();
            for (File file : files) {
                String[] name = file.getName().split(".");
                if (name[1].equals("json") || name[1].equals("csv")) {
                    sum += 1;
                }
            }
            System.out.println(sum);
        }catch (Exception e) {

        }
    }
}
