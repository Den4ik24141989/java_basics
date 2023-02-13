import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    //    private static String RootUrl = "https://fix-price.com";
    private static String RootUrl = "https://centrkinomir.ru/";

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        NodeUrl nodeUrl = new NodeUrl(RootUrl);
        new ForkJoinPool().invoke(new NodeUrlRecursiveAction(nodeUrl));

        FileOutputStream fileNodeSite = new FileOutputStream("/home/den/Документы/Мой курс/Gitlab/java_basics/Multithreading/SiteMap/src/main/resources/NodeSite.txt");
        String result = createPageUrlString(nodeUrl, 0);
        fileNodeSite.write(result.getBytes());
        fileNodeSite.flush();
        fileNodeSite.close();
        System.out.println("Выполнено за " + (System.currentTimeMillis() - start) + " ms");
    }

    public static String createPageUrlString(NodeUrl node, int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());
        node.getChildren().forEach(child -> {
            result.append("\n").append(createPageUrlString(child, depth + 1));
        });
        return result.toString();
    }
}
